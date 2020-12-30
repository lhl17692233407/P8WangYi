package com.example.p8wangyi.ui.login;

import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.p8wangyi.R;
import com.example.p8wangyi.base.BaseActivity;
import com.example.p8wangyi.interfaces.login.Ilogin;
import com.example.p8wangyi.model.login.LoginBean;
import com.example.p8wangyi.presenter.Login.LoginPresenter;
import com.example.p8wangyi.utils.SpUtils;

public class DengLuActivity extends BaseActivity<LoginPresenter> implements Ilogin.View, View.OnClickListener {
    private EditText mInputUsername;
    private EditText mInputPw;
    private ImageView mImgPw;
    private FrameLayout mLayoutPw;
    private Button mBtnLogin;
    private Button mBtnRegin;
    private String username;

    @Override
    protected int getLayout() {
        return R.layout.activity_deng_lu;
    }

    @Override
    protected LoginPresenter createPrenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initView() {

        mInputUsername = findViewById(R.id.input_username);
        mInputPw = findViewById(R.id.input_pw);
        mImgPw = findViewById(R.id.img_pw);
        mLayoutPw = findViewById(R.id.layout_pw);
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnRegin = findViewById(R.id.btn_regin);

        mBtnRegin.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mImgPw.setOnClickListener(this);
        mImgPw.setTag(1);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onEnter(LoginBean loginBean) {
        Log.e("token",loginBean.getData().getToken()+"aaaaaaaaaaaaaaa");
        if (loginBean.getData().getCode()!=200){
            Toast.makeText(this, "账号或密码不对", Toast.LENGTH_SHORT).show();
        }else {
            if (!TextUtils.isEmpty(loginBean.getData().getToken())) {
                SpUtils.getInstance().setValue("token", loginBean.getData().getToken());
                SpUtils.getInstance().setValue("uid", loginBean.getData().getUserInfo().getUid());
                SpUtils.getInstance().setValue("username",username);
                Intent intent = getIntent();
                setResult(3,intent);
                finish();
                Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                login();
                break;
            case R.id.img_pw:
                int tag = (int) mImgPw.getTag();
                if(tag == 1){
                    mImgPw.setImageResource(R.mipmap.ic_pw_open);
                    mImgPw.setTag(2);
                    mInputPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    mImgPw.setImageResource(R.mipmap.ic_pw_close);
                    mImgPw.setTag(1);
                    mInputPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.btn_regin:
                startActivity(new Intent(DengLuActivity.this,ZhuCeActivity.class));
                break;
        }
    }
    private void login(){
        username = mInputUsername.getText().toString();
        String pw = mInputPw.getText().toString();
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pw)){
            presenter.OnGet(username,pw);
        }else{
            Toast.makeText(this, "账号或密码为空", Toast.LENGTH_SHORT).show();
        }
    }
}