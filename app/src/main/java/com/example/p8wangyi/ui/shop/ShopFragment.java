package com.example.p8wangyi.ui.shop;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p8wangyi.R;
import com.example.p8wangyi.base.BaseAdapter;
import com.example.p8wangyi.base.BaseFragment;
import com.example.p8wangyi.interfaces.shop.IShop;
import com.example.p8wangyi.model.shop.DeleteCarBean;
import com.example.p8wangyi.model.shop.ShopBean;
import com.example.p8wangyi.model.shop.UpdateCarBean;
import com.example.p8wangyi.presenter.shop.ShopPresenter;
import com.example.p8wangyi.utils.SpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopFragment extends BaseFragment<ShopPresenter> implements IShop.View, View.OnClickListener {
    private RecyclerView mRecyGood;
    private NestedScrollView mScrollView;
    private CheckBox checkBoxAll;
    private TextView txtTotalPrice;
    private TextView txtEdit;
    private TextView txtSubmit;
    private ConstraintLayout mLayoutCommon;

    private ShopListAdapter carListAdapter;
    private List<ShopBean.DataBean.CartListBean> list;
    private ShopBean shopBean;

    private boolean isEdit; //是否是编辑状态

    @Override
    protected int getLayout() {
        return R.layout.fragment_shop;
    }

    @Override
    protected ShopPresenter createPrenter() {
        return new ShopPresenter();
    }

    @Override
    protected void initView(View view) {

        mRecyGood = view.findViewById(R.id.recy_good);
        mScrollView =view. findViewById(R.id.scrollView);
        checkBoxAll = view.findViewById(R.id.checkbox_all);
        txtTotalPrice =view. findViewById(R.id.txt_totalPrice);
        txtEdit =view. findViewById(R.id.txt_edit);
        txtSubmit = view.findViewById(R.id.txt_submit);
        mLayoutCommon =view. findViewById(R.id.layout_common);


        checkBoxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG","checkboxall");
                boolean bool = checkBoxAll.isChecked();
                if(isEdit){
                    updateGoodSelectStateEdit(!bool);
                }else{
                    updateGoodSelectStateOrder(!bool);
                }
            }
        });

        txtEdit.setOnClickListener(this);
        txtSubmit.setOnClickListener(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            String token = SpUtils.getInstance().getString("token");
            if (token==""){
                gotoLogin();
            }else{
                if (presenter!=null){
                    presenter.OnGet();
                }
            }
        }
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        mRecyGood.setLayoutManager(new LinearLayoutManager(getActivity()));
        carListAdapter = new ShopListAdapter(mContext,list);
        mRecyGood.setAdapter(carListAdapter);
        String token = SpUtils.getInstance().getString("token");
        if(token !=""){
            presenter.OnGet();
        }else{
            gotoLogin();
        }

        //条目点击接口回调
        carListAdapter.addItemViewClick(new BaseAdapter.IItemViewClick() {
            @Override
            public void itemViewClick(int id, Object data) {
                for(ShopBean.DataBean.CartListBean item:list){
                    if(item.getId() == id){
                        if(!isEdit){
                            item.selectOrder = (boolean) data;
                        }else{
                            item.selectEdit = (boolean) data;
                        }
                        break;
                    }
                }
                boolean isSelectAll;
                if(!isEdit){
                    isSelectAll = totalSelectOrder();
                }else{
                    isSelectAll = totalSelectEdit();
                }
                checkBoxAll.setChecked(isSelectAll);
            }
        });

        // 监听编辑状态下的数据变化
        carListAdapter.setUpdateItem(new ShopListAdapter.UpdateItem() {
            @Override
            public void updateItemDate(ShopBean.DataBean.CartListBean data) {
                Map<String,String> map = new HashMap<>();
                map.put("goodsId",String.valueOf(data.getGoods_id()));
                map.put("productId",String.valueOf(data.getProduct_id()));
                map.put("id",String.valueOf(data.getId()));
                map.put("number",String.valueOf(data.getNumber()));
                presenter.OnGet(map);
                totalSelectEdit();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("token",SpUtils.getInstance().getString("token")+"11111111111111111111");

        presenter.OnGet();
    }

    @Override
    public void OnEnter(ShopBean shopBean) {
        this.shopBean = shopBean;
        list.clear();
        list.addAll(shopBean.getData().getCartList());
        carListAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnEnter(UpdateCarBean updateCarBean) {

        for(UpdateCarBean.DataBean.CartListBean item:updateCarBean.getData().getCartList()){
            updateCartListBeanNumberById(item.getId(),item.getNumber());
        }
        //更新商品的总数和总价
        shopBean.getData().getCartTotal().setGoodsCount(updateCarBean.getData().getCartTotal().getGoodsCount());
        shopBean.getData().getCartTotal().setGoodsAmount(updateCarBean.getData().getCartTotal().getGoodsAmount());
        shopBean.getData().getCartTotal().setCheckedGoodsAmount(updateCarBean.getData().getCartTotal().getCheckedGoodsAmount());
        shopBean.getData().getCartTotal().setCheckedGoodsCount(updateCarBean.getData().getCartTotal().getCheckedGoodsCount());
        carListAdapter.notifyDataSetChanged();
        totalSelectEdit();
    }

    //刷新购物车列表的数据
    private void updateCartListBeanNumberById(int carId,int number){
        for(ShopBean.DataBean.CartListBean item:list){
            if(item.getId() == carId){
                item.setNumber(number);
                break;
            }
        }
    }



    @Override
    public void OnEnter(DeleteCarBean deleteCarBean) {
        //通过购物车返回的最新数据，同步本地列表中的数据
        int index,lg=list.size();
        for(index=0;index<lg; index++){
            ShopBean.DataBean.CartListBean item = list.get(index);
            boolean bool = deleteCarListById(deleteCarBean.getData().getCartList(),item.getId());
            Log.i("TAG","delete bool:"+bool +" item:"+item.getId());
            if(bool){
                list.remove(index);
                index--;
                lg--;
            }

        }
        carListAdapter.notifyDataSetChanged();
        totalSelectEdit();
    }


    //判断当前的本地列表的购物车列表数据是否在返回的最新列表中存在
    private boolean deleteCarListById(List<DeleteCarBean.DataBean.CartListBean> list ,int carId){
        for(DeleteCarBean.DataBean.CartListBean item:list){
            if(item.getId() == carId){
                return false;
            }
        }
        return true;
    }

    //下单状态的数据刷新
    private void updateGoodSelectStateOrder(boolean bool){
        for(ShopBean.DataBean.CartListBean item:list){
            item.selectOrder = bool;
        }
        totalSelectOrder();
        // 更新列表条目的选中状态
        carListAdapter.notifyDataSetChanged();
    }

    //编辑状态下的数据刷新
    private void updateGoodSelectStateEdit(boolean bool){
        for(ShopBean.DataBean.CartListBean item:list){
            item.selectEdit = bool;
        }
        totalSelectOrder();
        carListAdapter.notifyDataSetChanged();
    }

    //下单状态下的总数和价格的计算
    private boolean totalSelectOrder(){
        int num = 0;
        int totalPrice = 0;
        boolean isSelectAll = true;
        for(ShopBean.DataBean.CartListBean item:list){
            if(item.selectOrder){
                num += item.getNumber();
                totalPrice += item.getNumber()*item.getRetail_price();
            }else{
                if(isSelectAll){
                    isSelectAll = false;
                }
            }
        }
        String strAll = "全选($)";
        strAll = strAll.replace("$",String.valueOf(num));
        checkBoxAll.setText(strAll);
        txtTotalPrice.setText("￥"+totalPrice);
        Log.i("TAG","num: "+num+"price："+totalPrice);
        return isSelectAll;
    }

    // 编辑状态下的
    private boolean totalSelectEdit(){
        int num = 0;
        boolean isSelectAll = true;
        for(ShopBean.DataBean.CartListBean item:list){
            if(item.selectEdit){
                num += item.getNumber();
            }else{
                if(isSelectAll){
                    isSelectAll = false;
                }
            }
        }
        String strAll = "全选($)";
        strAll = strAll.replace("$",String.valueOf(num));
        checkBoxAll.setText(strAll);
        return isSelectAll;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_edit:
                changeEdit();
                break;
            case R.id.txt_submit:
                submit();
                break;
        }
    }

    // 修改编辑和完成的状态
    private void changeEdit(){
        if("编辑".equals(txtEdit.getText().toString())){
            txtEdit.setText("完成");
            txtSubmit.setText("删除所选");
            isEdit = true;
            txtTotalPrice.setVisibility(View.GONE);
        }else if("完成".equals(txtEdit.getText().toString())){
            txtEdit.setText("编辑");
            txtSubmit.setText("下单");
            isEdit = false;
            updateGoodSelectStateEdit(false);
            txtTotalPrice.setVisibility(View.VISIBLE);
        }
        carListAdapter.setEditState(isEdit);
        carListAdapter.notifyDataSetChanged();
    }

    //提交
    private void submit(){
        if("下单".equals(txtSubmit.getText().toString())){
            startActivity(new Intent(getActivity(), XiaDanActivity.class));
        }else if("删除所选".equals(txtSubmit.getText().toString())){
            //删除购物车所选数据
            deleteCar();
        }
    }

    //删除所有选中的商品数据
    private void deleteCar(){
        StringBuilder sb = new StringBuilder();
        for(ShopBean.DataBean.CartListBean item:list){
            if(item.selectEdit){
                sb.append(item.getProduct_id());
                sb.append(",");
            }
        }
        if(sb.length() > 0){
            sb.deleteCharAt(sb.length()-1);
        }
        Log.i("TAG",sb.toString());
        presenter.OnGet(sb.toString());
    }
}
