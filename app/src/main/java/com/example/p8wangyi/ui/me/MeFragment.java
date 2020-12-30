package com.example.p8wangyi.ui.me;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.example.p8wangyi.R;
import com.example.p8wangyi.base.BaseFragment;
import com.example.p8wangyi.interfaces.IBasePresenter;
import com.example.p8wangyi.ui.login.DengLuActivity;
import com.example.p8wangyi.utils.GlideEngine;
import com.example.p8wangyi.utils.SpUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

public class MeFragment extends BaseFragment{

    private TextView textView;
    private LinearLayout linearLayout;
    private ImageView img2;
    private TextView mTextTuiChu;
    private ImageView mMeImg1;

    @Override
    protected int getLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView(View view) {
        linearLayout = view.findViewById(R.id.me_lin_shoucang);
        textView = view.findViewById(R.id.me_text);
        img2 = view.findViewById(R.id.me_img2);
        mTextTuiChu = view.findViewById(R.id.me_tuichu);
        mMeImg1 = view.findViewById(R.id.me_img1);
    }

    @Override
    protected void initData() {
        /**
         * 收藏界面
         */
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),ShouCangActivity.class));
            }
        });
        /**
         * 登录
         */
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), DengLuActivity.class),1);
            }
        });
        /**
         * 个人信息
         */
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivityForResult(new Intent(getActivity(),GeRenActivity.class),1);
            }
        });

        /**
         * 退出登录
         */
        mTextTuiChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("点击登录");
                SpUtils.getInstance().setValue("token","");
                SpUtils.getInstance().setValue("img","");

                String img = SpUtils.getInstance().getString("img");
                Glide.with(getActivity()).load(img).apply(new RequestOptions().centerCrop()).into(mMeImg1);
            }
        });
        /**
         * 放大图
         */
        mMeImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
//    /**
//     * 打开相册
//     */
//    private void openPhoto(){
//        PictureSelector.create(this)
//                .openGallery(PictureMimeType.ofImage())
//                .loadImageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
//                .maxSelectNum(1)
//                .imageSpanCount(4)
//                .selectionMode(PictureConfig.MULTIPLE)
//                .forResult(PictureConfig.CHOOSE_REQUEST);
//    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode==3){
//            String username = SpUtils.getInstance().getString("username");
//            textView.setText(username);
//        }
//        switch (requestCode) {
//            case PictureConfig.CHOOSE_REQUEST:
//                // onResult Callback
//                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
//                if (selectList.size() == 0) return;
//                //获取本地图片的选择地址，上传到服务器
//                //头像的压缩和二次采样
//                //把选中的图片插入到列表
//                for (LocalMedia item : selectList) {
//                    sendMsgByImage(item.getPath());
//                    Log.e("tag", "1111111111111" + item.getPath());
//                }
//                break;
//            default:
//                break;
//        }
//    }

//    private void sendMsgByImage(String path) {
//        Glide.with(getActivity()).load(path).apply(new RequestOptions().centerCrop()).into(mMeImg1);
//    }

    @Override
    public void onResume() {
        super.onResume();
        String username = SpUtils.getInstance().getString("username");
        if (username!=""){
            textView.setText(username);
        }
        String img = SpUtils.getInstance().getString("img");
        if (img!=""){
            Glide.with(getActivity()).load(img).apply(new RequestOptions().centerCrop()).into(mMeImg1);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            String username = SpUtils.getInstance().getString("username");
            String img = SpUtils.getInstance().getString("img");
            if (textView!=null){
                if (username!=""){
                    textView.setText(username);
                }
                if (img!=""){
                    if (mMeImg1!=null){
                        if (getActivity()!=null){
                            Glide.with(getActivity()).load(img).apply(new RequestOptions().centerCrop()).into(mMeImg1);
                        }

                    }
                }
            }
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        if (requestCode==1){
            String img = SpUtils.getInstance().getString("img");
            if (img!=""){
                Glide.with(getActivity()).load(img).apply(new RequestOptions().centerCrop()).into(mMeImg1);
            }
        }
    }
}
