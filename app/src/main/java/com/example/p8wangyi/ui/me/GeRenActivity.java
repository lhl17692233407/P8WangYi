package com.example.p8wangyi.ui.me;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.bumptech.glide.Glide;
import com.example.p8wangyi.R;
import com.example.p8wangyi.app.Constants;
import com.example.p8wangyi.base.BaseActivity;
import com.example.p8wangyi.interfaces.me.IMe;
import com.example.p8wangyi.model.me.UserInfoBean;
import com.example.p8wangyi.presenter.me.MePresenter;
import com.example.p8wangyi.utils.BitmapUtils;
import com.example.p8wangyi.utils.GlideEngine;
import com.example.p8wangyi.utils.SpUtils;
import com.example.p8wangyi.utils.SystemUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeRenActivity extends BaseActivity<MePresenter> implements IMe.View, View.OnClickListener {
    String bucketName = "2002a";
    String ossPoint = "http://oss-cn-beijing.aliyuncs.com";

    String key = "LTAI4G1JvHB2FsXvDYMfY56i";  //appkey
    String secret = "gIwhFC9Sk4JEkfFR2mkcOz2Uwr6Vid";  //密码

    private OSS ossClient;
    private ImageView mImgArrowLt;
    private TextView mTxtTitle;
    private ImageView mImgAvatar;
    private ConstraintLayout mLayoutAvatar;
    private TextView mTxtUsername;
    private TextView mTxtNickname;
    private ConstraintLayout mLayoutNickname;
    private EditText mTxtInput;
    private Button mBtnSave;
    private ConstraintLayout mLayoutInput;

    @Override
    protected int getLayout() {
        return R.layout.activity_ge_ren;
    }

    @Override
    protected MePresenter createPrenter() {
        return new MePresenter();
    }

    @Override
    protected void initView() {

        mImgArrowLt = findViewById(R.id.img_arrow_lt);
        mTxtTitle = findViewById(R.id.txt_title);
        mImgAvatar = findViewById(R.id.img_avatar);
        mLayoutAvatar = findViewById(R.id.layout_avatar);
        mTxtUsername = findViewById(R.id.txt_username);
        mTxtNickname = findViewById(R.id.txt_nickname);
        mLayoutNickname = findViewById(R.id.layout_nickname);
        mTxtInput = findViewById(R.id.txt_input);
        mBtnSave = findViewById(R.id.btn_save);
        mLayoutInput = findViewById(R.id.layout_input);

        String img = SpUtils.getInstance().getString("img");
        if (img!=""){
            Glide.with(GeRenActivity.this).load(img).into(mImgAvatar);
        }
        mImgAvatar.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
        mLayoutNickname.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        initOss();
    }

    private void initOss() {
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(key, secret, "");
        // 配置类如果不设置，会有默认配置。
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒。
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒。
        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个。
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次。
        ossClient = new OSSClient(getApplicationContext(), ossPoint, credentialProvider);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_avatar:
                openPhoto();
                break;
            case R.id.layout_nickname:
                //打开输入的状态
                showInput();
                break;
            case R.id.btn_save:
                String nickname = mTxtInput.getText().toString();
                if(!TextUtils.isEmpty(nickname)){
                    Map<String,String> map = new HashMap<>();
                    map.put("nickname",nickname);
                    presenter.OnGet(map);
                }
                break;
        }
    }
    /**
     * 打开相册
     */
    private void openPhoto(){
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .maxSelectNum(1)
                .imageSpanCount(4)
                .selectionMode(PictureConfig.MULTIPLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                // onResult Callback
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                if (selectList.size() == 0) {
                    Log.e("tag","图片大小为0"+selectList.get(0).getPath());
                    return;
                }
                //获取本地图片的选择地址，上传到服务器
                //头像的压缩和二次采样
                //把选中的图片插入到列表
                try {
                    for (LocalMedia item : selectList){
                        Bitmap scaleBitmp = BitmapUtils.getScaleBitmap(item.getPath(), Constants.HEAD_WIDTH,Constants.HEAD_HEIGHT);
                        Log.e("tag", "222222222222222" + item.getPath());
                        // 上传图片
                        byte[] bytes = BitmapUtils.getBytesByBitmap(scaleBitmp);
                        uploadHead(bytes);
                        Glide.with(GeRenActivity.this).load(item.getPath()).into(mImgAvatar);
                        SpUtils.getInstance().setValue("img",item.getPath());
                    }
                    //Bitmap scaleBitmp = BitmapUtils.getScaleBitmap(selectList.get(0).getPath(), Constants.HEAD_WIDTH,Constants.HEAD_HEIGHT);

                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
    /**
     * oss上传
     * @param headBts
     */
    private void uploadHead(byte[] headBts){
        String uid = SpUtils.getInstance().getString("uid");
        String fileName = uid+"/"+System.currentTimeMillis()+Math.random()*10000+".png";
        PutObjectRequest put = new PutObjectRequest(bucketName, fileName,headBts);
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                //上次进度
                Log.e("oss_upload",currentSize+"/"+totalSize);
                // 进度百分比的计算
                // int p = (int) (currentSize/totalSize*100);
                if(currentSize == totalSize){
                    //完成
                    Log.e("headurl","完成了");
                    String headUrl = request.getUploadFilePath();
                    //
                    Log.e("HeadUrl","哈哈哈"+headUrl);
                    //request.getUploadFilePath()
                }

            }
        });
        OSSAsyncTask task = ossClient.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("PutObject", "UploadSuccess");
                Log.d("ETag", result.getETag());
                Log.d("RequestId", result.getRequestId());
                //成功的回调中读取相关的上传文件的信息  生成一个url地址
                String url = ossClient.presignPublicObjectURL(request.getBucketName(),request.getObjectKey());
                //调用服务器接口 把url上传到服务器的接口
                Map<String,String> map = new HashMap<>();
                map.put("avatar",url);
                presenter.OnGet(map);
                //updateHead(url);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常。
                if (clientExcepion != null) {
                    // 本地异常，如网络异常等。
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常。
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }
    /**
     * 更新用户信息返回
     * @param
     */
    @Override
    public void OnEnter(UserInfoBean userInfoBean) {
        if(userInfoBean.getErrno() == 0){
            SystemUtils.closeSoftKeyBoard(this);
            mLayoutInput.setVisibility(View.GONE);
        }
    }

    private void showInput(){
        mLayoutInput.setVisibility(View.VISIBLE);
        mTxtInput.setFocusable(true);
        SystemUtils.openSoftKeyBoard(this);
    }
}