package com.example.p8wangyi.model.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.p8wangyi.R;
import com.example.p8wangyi.base.BaseActivity;
import com.example.p8wangyi.interfaces.home.IGouMai;
import com.example.p8wangyi.presenter.home.GouMaiPresenter;
import com.example.p8wangyi.ui.home.PingLunActivity;
import com.example.p8wangyi.ui.login.DengLuActivity;
import com.example.p8wangyi.utils.RealmUser;
import com.example.p8wangyi.utils.SpUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.realm.Realm;
import io.realm.RealmResults;

public class GouMaiActivity extends BaseActivity<IGouMai.Presenter> implements IGouMai.View, View.OnClickListener {
    private Banner mBanner;
    private TextView mTxtAssess;
    private WebView mWebView;
    private NestedScrollView mScrollView;
    private ImageView mImgCollect;
    private FrameLayout mLayoutCollect;
    private ImageView mImgCar;
    private TextView mTxtNumber;
    private FrameLayout mLayoutCar;
    private TextView mTxtBuy;
    private TextView mTxtAddCar;
    private ConstraintLayout mLayoutShop;
    private String h5 = "<html>\n" +
            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
            "            <head>\n" +
            "                <style>\n" +
            "                    p{\n" +
            "                        margin:0px;\n" +
            "                    }\n" +
            "                    img{\n" +
            "                        width:100%;\n" +
            "                        height:auto;\n" +
            "                    }\n" +
            "                </style>\n" +
            "            </head>\n" +
            "            <body>\n" +
            "                word\n" +
            "            </body>\n" +
            "        </html>";
    private TextView mRishiText;
    private TextView mYouzhiText;
    private TextView mPriceText;
    private TextView mTextRiqi;
    private TextView mWenziText;
    private ImageView mPingjiaImg;
    private RecyclerView mGoumaiRecv;
    private ArrayList<GouMaiBean.DataBeanX.AttributeBean> goumailist;
    private RecyclerView mWentiRecv;
    private ArrayList<GouMaiBean.DataBeanX.IssueBean> listBeans;
    private ArrayList<String> listUrl;
    private ConstraintLayout mCon;
    int buyNumber = 1;
    private GouMaiBean good;
    private ArrayList<String> bannerlist;
    private int retail_price;
    private PopupWindow popupWindow;
    private View view1;
    private Realm mRealm;
    private int retail_price1;
    private String name;
    private String img;
    private TextView mGoumaiText;

    @Override
    protected int getLayout() {
        return R.layout.activity_goumai;
    }

    @Override
    protected GouMaiPresenter createPrenter() {
        return new GouMaiPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mRealm = Realm.getDefaultInstance();
    }

    @Override
    protected void initView() {
        mBanner = findViewById(R.id.banner);
        mTxtAssess = findViewById(R.id.txt_assess);
        mWebView = findViewById(R.id.webView);
        mScrollView = findViewById(R.id.scrollView);
        mImgCollect = findViewById(R.id.img_collect);
        mLayoutCollect = findViewById(R.id.layout_collect);
        mImgCar = findViewById(R.id.img_car);
        mTxtNumber = findViewById(R.id.txt_number);
        mLayoutCar = findViewById(R.id.layout_car);
        mTxtBuy = findViewById(R.id.txt_buy);
        mTxtAddCar = findViewById(R.id.txt_addCar);
        mLayoutShop = findViewById(R.id.layout_shop);
        mRishiText = findViewById(R.id.rishi_text);
        mYouzhiText = findViewById(R.id.youzhi_text);
        mPriceText = findViewById(R.id.price_text);
        mTextRiqi = findViewById(R.id.text_riqi);
        mWenziText = findViewById(R.id.wenzi_text);
        mPingjiaImg = findViewById(R.id.pingjia_img);
        mGoumaiRecv = findViewById(R.id.goumai_recv);
        mWentiRecv = findViewById(R.id.wenti_recv);
        mGoumaiText = findViewById(R.id.goumai_pinglun_text);

        mImgCollect.setOnClickListener(this);
        mImgCar.setOnClickListener(this);
        mTxtBuy.setOnClickListener(this);
        mTxtAddCar.setOnClickListener(this);
        mGoumaiText.setOnClickListener(this);

        //mWebView.setLayoutManager(new LinearLayoutManager(this));

        mTxtAddCar.setTag(1);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        img = intent.getStringExtra("img");
        presenter.OnGet(id);
    }

    @Override
    public void OnEnter(GouMaiBean gouMaiBean) {
        good = gouMaiBean;
        initBann(gouMaiBean.getData().getGallery());
        initImg(gouMaiBean.getData().getInfo().getGoods_desc());
        initPrice(gouMaiBean.getData().getInfo());
        initPingLun(gouMaiBean.getData().getComment());
        initGuige(gouMaiBean.getData().getAttribute());
        initWenTi(gouMaiBean.getData().getIssue());
        //mTxtNumber.setText();
    }

    @Override
    public void OnEnter(AddShopCarBean addShopCarBean) {
        int number = addShopCarBean.getData().getCartTotal().getGoodsCount();
        mTxtNumber.setText(String.valueOf(number));
        mTxtNumber.setVisibility(View.VISIBLE);
    }

    //问题区域
    private void initWenTi(List<GouMaiBean.DataBeanX.IssueBean> productList) {
        mWentiRecv.setLayoutManager(new LinearLayoutManager(GouMaiActivity.this));
        listBeans = new ArrayList<>();
        listBeans.addAll(productList);

        WenTiAdapter wenTiAdapter = new WenTiAdapter();
        mWentiRecv.setAdapter(wenTiAdapter);
        wenTiAdapter.notifyDataSetChanged();
    }

    //商品规格
    private void initGuige(List<GouMaiBean.DataBeanX.AttributeBean> attribute) {
        mGoumaiRecv.setLayoutManager(new LinearLayoutManager(GouMaiActivity.this));
        goumailist = new ArrayList<>();
        goumailist.addAll(attribute);
        Adapter adapter = new Adapter();
        mGoumaiRecv.setAdapter(adapter);
    }

    //商品评论
    private void initPingLun(GouMaiBean.DataBeanX.CommentBean comment) {

        mTxtAssess.setText("评价" + "(" + comment.getCount() + ")");
        mWenziText.setText(comment.getData().getContent());
        if (comment.getData().getAdd_time() != null) {
            mTextRiqi.setText(comment.getData().getAdd_time());
        }
        if (comment.getData().getPic_list() != null) {
            Glide.with(GouMaiActivity.this).load(comment.getData().getPic_list().get(0).getPic_url()).into(mPingjiaImg);
        }

    }

    //优质，价格等等
    private void initPrice(GouMaiBean.DataBeanX.InfoBean info) {
        name = info.getName();
        retail_price1 = info.getRetail_price();
        this.retail_price = info.getRetail_price();
        mYouzhiText.setText(info.getGoods_brief());
        mRishiText.setText(info.getName());
        mPriceText.setText("$" + info.getRetail_price());
    }


    //h5底部图片
    private void initImg(String goods_desc) {
        getQie(goods_desc);
        String hh5 = h5.replace("word", goods_desc);
        mWebView.loadDataWithBaseURL("about:blank", hh5, "text/html", "utf-8", null);

    }

    private void getQie(String goods_desc) {
        listUrl = new ArrayList<>();
        String img = "<img[\\s\\S]*?>";
        Pattern pattern = Pattern.compile(img);
        Matcher matcher = pattern.matcher(goods_desc);

        while (matcher.find()) {
            String word = matcher.group();
            int start = word.indexOf("\"") + 1;
            int end = word.indexOf(".jpg");
            if (end > 0) {//如果是jpg格式的就截取jpg
                String url = word.substring(start, end);
                if (url != null) {
                    url = url + ".jpg";
                    listUrl.add(url);
                } else {
                    return;
                }
            } else {
                int end1 = word.indexOf(".png");//如果是png格式的就截取png
                String url = word.substring(start, end1);
                if (url != null) {
                    url = url + ".png";
                    listUrl.add(url);
                } else {
                    return;
                }
            }
        }
    }

    //轮播图数据
    private void initBann(List<GouMaiBean.DataBeanX.GalleryBean> gallery) {
        bannerlist = new ArrayList<>();
        for (int i = 0; i < gallery.size(); i++) {
            bannerlist.add(gallery.get(i).getImg_url());
        }
        mBanner.setImages(bannerlist).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(GouMaiActivity.this).load(path).into(imageView);
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goumai_pinglun_text:
                Intent intent1 = new Intent(GouMaiActivity.this, PingLunActivity.class);
                startActivity(intent1);
                break;
            //收藏
            case R.id.img_collect:
                String token2 = SpUtils.getInstance().getString("token");
                if (token2 == null) {
                    startActivity(new Intent(GouMaiActivity.this, DengLuActivity.class));
                } else {
                    RealmResults<RealmUser> name1 = mRealm.where(RealmUser.class)
                            .equalTo("name", name)
                            .findAll();
                    int i=1;
                    for (RealmUser realmUser : name1) {
                        if (realmUser.getName()!=null){
                            if (realmUser.getName().equals(name)){
                                i=0;
                            }
                        }
                    }


                    if (i== 0) {
                        RealmResults<RealmUser> results = mRealm.where(RealmUser.class).findAll();
                        mRealm.beginTransaction();
                        for (int j = 0; j < results.size(); j++) {
                            if (results.get(j).getName()!=null){
                                if (results.get(j).getName().equals(name)){
                                    results.get(j).deleteFromRealm();
                                }
                            }
                        }
                        mRealm.commitTransaction();
                        Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
                    } else {

                        mRealm.beginTransaction();
                        RealmUser realmUser = mRealm.createObject(RealmUser.class);
                        realmUser.setName(name);
                        realmUser.setPrice(retail_price1);
                        realmUser.setImg(img);
                        mRealm.copyToRealm(realmUser);
                        mRealm.commitTransaction();
                        Toast.makeText(GouMaiActivity.this, "收藏", Toast.LENGTH_SHORT).show();


                    }

                }
                break;
            //购物车
            case R.id.img_car:
                String token = SpUtils.getInstance().getString("token");
                if (token != null) {
                    Intent intent = getIntent();
                    setResult(2, intent);
                    finish();

                } else {
                    startActivity(new Intent(GouMaiActivity.this, DengLuActivity.class));
                }
                break;
            //立即购买
            case R.id.txt_buy:

                break;
            //加入购物车:
            case R.id.txt_addCar:
                int tag = (int) mTxtAddCar.getTag();
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
                if (tag == 2) {
                    String token1 = SpUtils.getInstance().getString("token");
                    if (token1 == null) {
                        startActivity(new Intent(GouMaiActivity.this, DengLuActivity.class));
                    } else {
                        if (buyNumber <= 0) {
                            Toast.makeText(this, R.string.tips_buynumber, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (good.getData().getProductList().size() > 0) {
                            int goodsId = good.getData().getProductList().get(0).getGoods_id();
                            int productid = good.getData().getProductList().get(0).getId();
                            Map<String, String> map = new HashMap<>();
                            Log.e("goods", goodsId + "");
                            Log.e("goods", buyNumber + "");
                            Log.e("goods", productid + "");
                            map.put("goodsId", String.valueOf(goodsId));
                            map.put("number", String.valueOf(buyNumber));
                            map.put("productId", String.valueOf(productid));
                            presenter.OnGet(map);
                        }
                    }
                    mTxtAddCar.setTag(1);
                } else if (tag == 1) {
                    mTxtAddCar.setTag(2);
                    view1 = View.inflate(GouMaiActivity.this, R.layout.activity_pop_goumai, null);
                    TextView mPopGouMaI1 = view1.findViewById(R.id.pop_goumai_1);
                    TextView mPopGouMaI2 = view1.findViewById(R.id.pop_goumai_2);
                    TextView mPopGouMaI3 = view1.findViewById(R.id.pop_goumai_3);
                    ImageView img = view1.findViewById(R.id.pop_goumai_img);
                    TextView textView1 = view1.findViewById(R.id.pop_goumai_text1);
                    TextView text2 = view1.findViewById(R.id.pop_goumai_text2);

                    popupWindow = new PopupWindow(view1, ViewGroup.LayoutParams.MATCH_PARENT, 700);
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 0.7f;
                    getWindow().setAttributes(lp);
                    text2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            popupWindow.dismiss();
                        }
                    });
                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            WindowManager.LayoutParams lp = getWindow().getAttributes();
                            lp.alpha = 1f;
                            getWindow().setAttributes(lp);
                        }
                    });
                    popupWindow.showAsDropDown(view1, 0, 1000);
                    Glide.with(GouMaiActivity.this).load(bannerlist.get(0)).into(img);
                    textView1.setText("价格：￥" + retail_price);
                    mPopGouMaI1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(GouMaiActivity.this, "点击了-", Toast.LENGTH_SHORT).show();
                            String s = mPopGouMaI2.getText().toString();
                            int a = Integer.parseInt(s);
                            a--;
                            if (a != 0) {
                                mPopGouMaI2.setText(a + "");
                            }
                        }
                    });

                    mPopGouMaI3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String s = mPopGouMaI2.getText().toString();
                            int a = Integer.parseInt(s);
                            a++;
                            Toast.makeText(GouMaiActivity.this, a + "", Toast.LENGTH_SHORT).show();
                            if (a != 0) {
                                mPopGouMaI2.setText(a + "");
                            }
                        }
                    });
                }

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(GouMaiActivity.this, R.layout.activity_canshu_item, null);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.text1.setText(goumailist.get(position).getName());
            holder.text2.setText(goumailist.get(position).getValue());
        }

        @Override
        public int getItemCount() {
            return goumailist.size();
        }

        public class Holder extends RecyclerView.ViewHolder {
            TextView text1;
            TextView text2;

            public Holder(@NonNull View itemView) {
                super(itemView);
                text1 = itemView.findViewById(R.id.canshu_item_text1);
                text2 = itemView.findViewById(R.id.canshu_item_text2);
            }
        }
    }

    class WenTiAdapter extends RecyclerView.Adapter<WenTiAdapter.Holder> {

        @NonNull
        @Override
        public WenTiAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(GouMaiActivity.this, R.layout.activity_wenti_item, null);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull WenTiAdapter.Holder holder, int position) {
            holder.text1.setText(listBeans.get(position).getQuestion());
            holder.text2.setText(listBeans.get(position).getAnswer());
        }

        @Override
        public int getItemCount() {
            return listBeans.size();
        }

        public class Holder extends RecyclerView.ViewHolder {
            TextView text1;
            TextView text2;

            public Holder(@NonNull View itemView) {
                super(itemView);
                text1 = itemView.findViewById(R.id.wenti_text1);
                text2 = itemView.findViewById(R.id.wenti_text2);
            }
        }
    }

}
