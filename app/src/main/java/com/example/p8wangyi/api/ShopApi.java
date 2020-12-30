package com.example.p8wangyi.api;

import com.example.p8wangyi.model.home.AddShopCarBean;
import com.example.p8wangyi.model.home.DiBuBean;
import com.example.p8wangyi.model.home.GouMaiBean;
import com.example.p8wangyi.model.home.HomeBean;
import com.example.p8wangyi.model.home.NewXiangQingBean;
import com.example.p8wangyi.model.home.PinPaiBean;
import com.example.p8wangyi.model.home.TiaoZhuanBean;
import com.example.p8wangyi.model.home.TuPianBean;
import com.example.p8wangyi.model.home.VpBean;
import com.example.p8wangyi.model.home.XiangQingShangBean;
import com.example.p8wangyi.model.home.ZhouYiBean;
import com.example.p8wangyi.model.login.LoginBean;
import com.example.p8wangyi.model.login.ZhuCeBean;
import com.example.p8wangyi.model.me.UserInfoBean;
import com.example.p8wangyi.model.shop.DeleteCarBean;
import com.example.p8wangyi.model.shop.ShopBean;
import com.example.p8wangyi.model.shop.UpdateCarBean;
import com.example.p8wangyi.model.sort.IDaoNeiBean;
import com.example.p8wangyi.model.sort.ShuTabBean;
import com.example.p8wangyi.model.sort.SortNeiBean;
import com.example.p8wangyi.model.sort.XiangDaoBean;
import com.example.p8wangyi.ui.topic.TopBean;
import com.example.p8wangyi.ui.topic.TopBean2;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ShopApi {

    String BASE_URL = "http://cdplay.cn/";

    @GET("api/index")
    Flowable<HomeBean> getHome();

    @GET("goods/category")
    Flowable<TiaoZhuanBean> getData(@Query("id") int id);

    @GET("api/goods/list")
    Flowable<VpBean> getData1(@Query("categoryId") int id,@Query("page") int page,@Query("size") String size);

    @GET("api/goods/list")
    Flowable<ZhouYiBean> getZhouyi(@QueryMap HashMap<String,String> map);
    @GET("api/goods/hot")
    Flowable<TuPianBean> getTuPian();

    //新品制作商点击品牌
    @GET("api/brand/list?page=1&size=1000")
    Flowable<PinPaiBean> getPinPai();

    //点击品牌详情
    @GET("api/brand/detail")
    Flowable<NewXiangQingBean> getXiangQing(@Query("id") int id);

    //详情商品列表
    @GET("api/goods/list")
    Flowable<XiangQingShangBean> getXiangQingShang(@Query("brandId") int id,@Query("page") int page,@Query("size") int size);

    //购买商品列表
    @GET("api/goods/detail")
    Flowable<GouMaiBean> getGouMai(@Query("id") int id);

    //购买商品底部数据
    @GET("api/goods/related?id=1155000")
    Flowable<DiBuBean> getDiBu();


    //分类左边竖着的Tablayout
    @GET("api/catalog/index")
    Flowable<ShuTabBean> getTab(@Query("id") int id);

    //分类右边的数据
    @GET("api/catalog/current")
    Flowable<SortNeiBean> getNei(@Query("id") int id);

    //点进去的tab名
    @GET("goods/category")
    Flowable<XiangDaoBean> getDao(@Query("id") int id);

    //右边点进去详情数据
    @GET("api/goods/list")
    Flowable<IDaoNeiBean> getDaoNei(@Query("categoryId") int id,@Query("page") int page,@Query("size") int size);

    //登录
    @POST("api/auth/login")
    @FormUrlEncoded
    Flowable<LoginBean> getLogin(@Field("username") String username,@Field("password")String pw);

    //购物车
    @GET("api/cart/index")
    Flowable<ShopBean> getShop();

    //注册
    @POST("api/auth/register")
    @FormUrlEncoded
    Flowable<ZhuCeBean> getZhuCe(@Field("username") String username,@Field("password")String pw);

    //更新购物车的数据
    @POST("api/cart/update")
    @FormUrlEncoded
    Flowable<UpdateCarBean> updateCar(@FieldMap Map<String,String> map);


    //删除购物车数据
    @POST("api/cart/delete")
    @FormUrlEncoded
    Flowable<DeleteCarBean> deleteCar(@Field("productIds") String productIds);

    //加入购物车
    @POST("api/cart/add")
    @FormUrlEncoded
    Flowable<AddShopCarBean> addShopCar(@FieldMap Map<String,String> map);

    //专题页面
    @GET("api/topic/list")
    Flowable<TopBean> getTwo();

    //专题第二页
    @GET("api/topic/list?page=2")
    Flowable<TopBean2> getTwoo();

    //用户信息更新
    @POST("api/user/updateUserInfo")
    @FormUrlEncoded
    Flowable<UserInfoBean> updateUserInfo(@FieldMap Map<String,String> map);
}
