package com.example.p8wangyi.ui.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.p8wangyi.R;
import com.example.p8wangyi.base.BaseActivity;
import com.example.p8wangyi.interfaces.login.IZhuCe;
import com.example.p8wangyi.model.login.ZhuCeBean;
import com.example.p8wangyi.presenter.Login.ZhuCePresenter;
import com.example.p8wangyi.utils.SpUtils;

public class ZhuCeActivity extends BaseActivity<ZhuCePresenter> implements IZhuCe.View {
    private EditText mZhuceZhang;
    private EditText mZhucePass;
    private Button mZhuceBtn;

    @Override
    protected int getLayout() {
        return R.layout.activity_zhu_ce;
    }

    @Override
    protected ZhuCePresenter createPrenter() {
        return new ZhuCePresenter();
    }

    @Override
    protected void initView() {

        mZhuceZhang = findViewById(R.id.zhuce_zhang);
        mZhucePass = findViewById(R.id.zhuce_pass);
        mZhuceBtn = findViewById(R.id.zhuce_btn);
        mZhuceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s = mZhuceZhang.getText().toString();
                String s1 = mZhucePass.getText().toString();

                presenter.OnGet(s,s1);
            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    public void OnEnter(ZhuCeBean zhuCeBean) {
        Toast.makeText(this, zhuCeBean.getErrmsg(), Toast.LENGTH_SHORT).show();
        if (zhuCeBean.getErrno()==1000){
            Toast.makeText(this, "已经有了", Toast.LENGTH_SHORT).show();
        }else{
            finish();
            SpUtils.getInstance().setValue("token",zhuCeBean.getData().getToken());
        }
    }
}
