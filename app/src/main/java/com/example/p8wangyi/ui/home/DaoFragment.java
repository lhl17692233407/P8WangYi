package com.example.p8wangyi.ui.home;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.p8wangyi.R;
import com.example.p8wangyi.base.BaseFragment;
import com.example.p8wangyi.interfaces.IBasePresenter;
import com.example.p8wangyi.interfaces.home.IFragment;
import com.example.p8wangyi.model.home.VpBean;
import com.example.p8wangyi.presenter.home.FragmentPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoFragment extends BaseFragment<FragmentPresenter> implements IFragment.View {
    private RecyclerView mFragmentFragmentRecv;
    private List<VpBean.DataBeanX.DataBean> list;
    private Map<String, Integer> map;
    private int id;
    private TextView textView1;
    private TextView textView2;

    @Override
    protected int getLayout() {
        return R.layout.fragment_fragment;
    }

    @Override
    protected FragmentPresenter createPrenter() {
        return new FragmentPresenter();
    }


    @Override
    protected void initView(View view) {

        mFragmentFragmentRecv = view.findViewById(R.id.fragment_fragment_recv);
        textView1 = view.findViewById(R.id.fragment_text1);
        textView2 = view.findViewById(R.id.fragment_text2);

        mFragmentFragmentRecv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    @Override
    protected void initData() {

        Bundle arguments = getArguments();
        id = arguments.getInt("id");
        String name = arguments.getString("name");
        String name1 = arguments.getString("name1");
        textView1.setText(name);
        textView2.setText(name1);
        presenter.OnGet(id);
    }

    @Override
    public void OnEnter(VpBean vpBean) {
        list = vpBean.getData().getData();
        Adapter adapter = new Adapter();
        mFragmentFragmentRecv.setAdapter(adapter);

    }

    class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        @NonNull
        @Override
        public Adapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(getActivity(), R.layout.fragment_daofragment_item, null);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Adapter.Holder holder, int position) {
            holder.textView1.setText(list.get(position).getName());
            holder.textView2.setText("$"+list.get(position).getRetail_price()+"元");
            Glide.with(getActivity()).load(list.get(position).getList_pic_url()).into(holder.img);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class Holder extends RecyclerView.ViewHolder {
            ImageView img;
            TextView textView1;
            TextView textView2;
            public Holder(@NonNull View itemView) {
                super(itemView);
                img = itemView.findViewById(R.id.dao_img);
                textView1 = itemView.findViewById(R.id.dao_text1);
                textView2 = itemView.findViewById(R.id.dao_text2);
            }
        }
    }
}
