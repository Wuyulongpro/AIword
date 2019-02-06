package com.example.aiword;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.example.aiword.UserBean;
import com.google.gson.Gson;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    private EditText name;
    private TextView nav;
    private EditText pwd;
    private String name1,pwd1;
    private Button login;
    private CheckBox checkBox_password;
    private CheckBox checkBox_login;
    private ImageView iv_see_password;

    private Handler mHandler = new Handler(){
    @Override
    public void handleMessage(Message msg){
        String ReturnMessage = (String) msg.obj;
        Log.i("获取的返回信息",ReturnMessage);
        UserBean userBean = new Gson().fromJson(ReturnMessage, UserBean.class);
        if(userBean.getResultCode() == 0){
            Intent intent = new Intent(LoginActivity.this,WordSet.class);
            intent.putExtra("token",userBean.getToken());
            intent.putExtra("ID",userBean.getUserID());
            startActivity(intent);
        }
        else if(userBean.getResultCode() == -1){
            Toast.makeText(getApplicationContext(), "用户名不存在",
                    Toast.LENGTH_SHORT).show();
        }
        else if(userBean.getResultCode() == -2){
            Toast.makeText(getApplicationContext(), "密码错误",
                    Toast.LENGTH_SHORT).show();
        }
    }
};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
/**
 * 初始化
 */
        name = (EditText) findViewById(R.id.name);
        nav = (TextView) findViewById(R.id.nav_to_regist);
        pwd = (EditText) findViewById(R.id.pwd);
        login = (Button) findViewById(R.id.login);
        checkBox_password = (CheckBox) findViewById(R.id.checkBox_password);
        checkBox_login = (CheckBox) findViewById(R.id.checkBox_login);
        iv_see_password = (ImageView) findViewById(R.id.iv_see_password);



        nav.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //获取相关参数
                name1=name.getText().toString().trim();
                pwd1=pwd.getText().toString().trim();
                //通过okhttp发起post请求
                HttpUtil.sendOkHttpRequest("http://129.211.113.93:8080/user/login?username=" + name1 + "&password=" +pwd1, new okhttp3.Callback(){
                    @Override
                    public void onResponse(Call call, Response response) throws IOException{
                        mHandler.obtainMessage(1, response.body().string()).sendToTarget();
                    }
                    @Override
                    public void onFailure(Call call, IOException e){
                        e.printStackTrace();
                    }
                });
            }
        });

    }

}
