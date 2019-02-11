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

import com.example.aiword.SharedPreferencesUtils;
import com.example.aiword.webService.HttpUtil;
import com.example.aiword.R;
import com.example.aiword.webService.UserBean;
import com.google.gson.Gson;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    private EditText name;
    private TextView toRegist;
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
        //Log.i("获取的返回信息",ReturnMessage);
        UserBean userBean = new Gson().fromJson(ReturnMessage, UserBean.class);
        if(userBean.getResultCode() == 0){
            Intent intent = new Intent(LoginActivity.this, WordSet.class);
            intent.putExtra("token",userBean.getToken());
            intent.putExtra("ID",userBean.getUserID());
            startActivity(intent);
            loadCheckBoxState();
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
        toRegist = (TextView) findViewById(R.id.nav_to_regist);
        pwd = (EditText) findViewById(R.id.pwd);
        login = (Button) findViewById(R.id.login);
        checkBox_password = (CheckBox) findViewById(R.id.checkBox_password);
        checkBox_login = (CheckBox) findViewById(R.id.checkBox_login);
        iv_see_password = (ImageView) findViewById(R.id.iv_see_password);
        checkBox_password.setOnCheckedChangeListener(new CheckboxFunction());
        checkBox_login.setOnCheckedChangeListener(new CheckboxFunction());


        toRegist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loadUserName();    //保存一下用户名
                login(); //登陆
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
        initData();
    }
    private void initData() {
        //判断用户第一次登陆
        if (firstLogin()) {

            Log.d("首次登陆","first!!!!!");
            checkBox_password.setChecked(false);//取消记住密码的复选框
            checkBox_login.setChecked(false);//取消自动登录的复选框
        }
        //判断是否记住密码
        if (rememberPassword()) {
            checkBox_password.setChecked(true);//勾选记住密码
            setTextNameAndPassword();//把密码和账号输入到输入框中
            Log.i("qwer", "got");
        } else {
            setTextName();//把用户账号放到输入账号的输入框中
        }

        //判断是否自动登录
        if (autoLogin()) {
            checkBox_login.setChecked(true);
            setTextNameAndPassword();
            login();//去登录就可以

        }
    }


    public void loadUserName() {
        if (!getAccount().equals("") || !getAccount().equals("请输入登录账号")) {
            SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "localInfo");
            helper.putValues(new SharedPreferencesUtils.ContentValue("name", getAccount()));
        }

    }

    /**
     * 获取账号
     */
    public String getAccount() {
        return name.getText().toString().trim();//去掉空格
    }

    /**
     * 获取密码
     */
    public String getPassword() {
        return pwd.getText().toString().trim();//去掉空格
    }

    /**
     * 保存用户选择“记住密码”和“自动登陆”的状态
     */
    private void loadCheckBoxState() {
        loadCheckBoxState(checkBox_password, checkBox_login);
        Log.d("存储完毕","success!!!!!");
    }



    /**
     * 保存按钮的状态值
     */
    public void loadCheckBoxState(CheckBox checkBox_password, CheckBox checkBox_login) {

        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "localInfo");

        //如果设置自动登录
        if (checkBox_login.isChecked()) {
            //创建记住密码和自动登录是都选择,保存密码数据
            helper.putValues(
                    new SharedPreferencesUtils.ContentValue("rememberPassword", true),
                    new SharedPreferencesUtils.ContentValue("autoLogin", true),
                    new SharedPreferencesUtils.ContentValue("password", getPassword()));

        } else if (!checkBox_password.isChecked()) { //如果没有保存密码，那么自动登录也是不选的
            //创建记住密码和自动登录是默认不选,密码为空
            helper.putValues(
                    new SharedPreferencesUtils.ContentValue("rememberPassword", false),
                    new SharedPreferencesUtils.ContentValue("autoLogin", false),
                    new SharedPreferencesUtils.ContentValue("password", ""));
        } else if (checkBox_password.isChecked()) {   //如果保存密码，没有自动登录
            //创建记住密码为选中和自动登录是默认不选,保存密码数据
            helper.putValues(
                    new SharedPreferencesUtils.ContentValue("rememberPassword", true),
                    new SharedPreferencesUtils.ContentValue("autoLogin", false),
                    new SharedPreferencesUtils.ContentValue("password", getPassword()));
        }
    }

    private void login() {
        //获取相关参数
        name1=getAccount();
        pwd1=getPassword();
        if (getAccount().isEmpty()){
            Toast.makeText(getApplicationContext(), "你输入的账号为空！",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (getPassword().isEmpty()){
            Toast.makeText(getApplicationContext(), "你输入的密码为空！",
                    Toast.LENGTH_SHORT).show();
            return;
        }
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
    /**
     * 判断是否是第一次登陆
     */
    private boolean firstLogin() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "localInfo");
        boolean first = helper.getBoolean("first", true);
        if (first) {
            //创建一个ContentVa对象（自定义的）设置不是第一次登录，,并创建记住密码和自动登录是默认不选，创建账号和密码为空
            helper.putValues(new SharedPreferencesUtils.ContentValue("first", false),
                    new SharedPreferencesUtils.ContentValue("rememberPassword", false),
                    new SharedPreferencesUtils.ContentValue("autoLogin", false),
                    new SharedPreferencesUtils.ContentValue("name", ""),
                    new SharedPreferencesUtils.ContentValue("password", ""));
            return true;
        }
        return false;
    }

    /**
     * 把本地保存的数据设置数据到输入框中
     */
    public void setTextNameAndPassword() {
        name.setText("" + getLocalName());
        pwd.setText("" + getLocalPassword());
    }

    /**
     * 设置数据到输入框中
     */
    public void setTextName() {
        name.setText("" + getLocalName());
    }


    /**
     * 获得保存在本地的用户名
     */
    public String getLocalName() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "localInfo");
        String name = helper.getString("name");
        return name;
    }


    /**
     * 获得保存在本地的密码
     */
    public String getLocalPassword() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "localInfo");
        String password = helper.getString("password");
        return password;

    }

    /**
     * 判断是否自动登录
     */
    private boolean autoLogin() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "localInfo");
        boolean autoLogin = helper.getBoolean("autoLogin", false);
        return autoLogin;
    }

    /**
     * 判断是否记住密码
     */
    private boolean rememberPassword() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "localInfo");
        boolean rememberPassword = helper.getBoolean("rememberPassword", false);
        return rememberPassword;
    }

    private class CheckboxFunction implements CompoundButton.OnCheckedChangeListener{
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView == checkBox_password) {  //记住密码选框发生改变时
                if (!isChecked) {   //如果取消“记住密码”，那么同样取消自动登陆
                    checkBox_login.setChecked(false);
                }
            } else if (buttonView == checkBox_login) {   //自动登陆选框发生改变时
                if (isChecked) {   //如果选择“自动登录”，那么同样选中“记住密码”
                    checkBox_password.setChecked(true);
                }
            }
        }
    }
}
