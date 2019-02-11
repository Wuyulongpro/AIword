package com.example.aiword.activities;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.Call;
import okhttp3.Response;

import com.example.aiword.webService.HttpUtil;
import com.example.aiword.R;
import com.example.aiword.webService.UserBean;
import com.google.gson.Gson;

import java.io.IOException;

public class RegistActivity extends AppCompatActivity {
    private EditText name;
    private EditText pwd;
    private EditText phone;
    private TextView nav;
    private String name1,pwd1,phone1;
    private Button reg;
    private ImageView iv_see_password;


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            String ReturnMessage = (String) msg.obj;
            Log.i("获取的返回信息",ReturnMessage);
            UserBean userBean = new Gson().fromJson(ReturnMessage, UserBean.class);
            if(userBean.getResultCode() == 0){
                Toast.makeText(getApplicationContext(), "注册成功",
                        Toast.LENGTH_SHORT).show();
            }
            else if(userBean.getResultCode() == -1){
                Toast.makeText(getApplicationContext(), "用户名重复",
                        Toast.LENGTH_SHORT).show();
            }
            else if(userBean.getResultCode() == -2){
                Toast.makeText(getApplicationContext(), "此手机号已被注册",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
/**
 * 初始化
 */
        name = (EditText) findViewById(R.id.name);
        pwd = (EditText) findViewById(R.id.pwd);
        phone = (EditText) findViewById(R.id.phone);
        reg = (Button) findViewById(R.id.regist);
        iv_see_password = (ImageView) findViewById(R.id.iv_see_password);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取相关参数
                name1=name.getText().toString().trim();
                pwd1=pwd.getText().toString().trim();
                phone1=phone.getText().toString().trim();
                //通过okhttp发起post请求
                HttpUtil.sendOkHttpRequest("http://129.211.113.93:8080/user/regist?username=" + name1 + "&password=" + pwd1 + "&phonenumber=" + phone1, new okhttp3.Callback(){
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        mHandler.obtainMessage(1, response.body().string()).sendToTarget();
                    }
                    @Override
                    public void onFailure(Call call, IOException e){
                        e.printStackTrace();
                    }
                });

            }
        });
        iv_see_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iv_see_password.isSelected()) {
                    iv_see_password.setSelected(false);
                    //密码不可见
                    pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                } else {
                    iv_see_password.setSelected(true);
                    //密码可见
                    pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }

            }
        });

    }
}
