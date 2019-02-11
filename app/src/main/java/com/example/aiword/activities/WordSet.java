package com.example.aiword.activities;

import android.content.Intent;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aiword.ContentAdapter;
import com.example.aiword.DrawerContent;
import com.example.aiword.R;
import com.example.aiword.Webtest;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

public class WordSet extends AppCompatActivity {

    private List<DrawerContent> drawerList = new ArrayList<DrawerContent>();
    private ListView myListView;
    private long exitTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_set);
        initData();
        Button button1 = (Button) findViewById(R.id.mkplan_bt);
        Button button2 = (Button) findViewById(R.id.review_bt);
        final Button button3 = (Button) findViewById(R.id.lv4_bt);
        final Button button4 = (Button) findViewById(R.id.lv6_bt);
        final Button button5 = (Button) findViewById(R.id.signWord_bt);
        final Button button6 = (Button) findViewById(R.id.wrongWord_bt);
        Intent intent = getIntent();
        final String data = intent.getStringExtra("token");
        final int id = intent.getIntExtra("ID",-1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button3.setVisibility(View.VISIBLE);
                button4.setVisibility(View.VISIBLE);
                button5.setVisibility(View.INVISIBLE);
                button6.setVisibility(View.INVISIBLE);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button3.setVisibility(View.INVISIBLE);
                button4.setVisibility(View.INVISIBLE);
                button5.setVisibility(View.VISIBLE);
                button6.setVisibility(View.VISIBLE);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WordSet.this, Lib.class);
               // intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WordSet.this, Webtest.class);
                intent.putExtra("User_token",data);
                intent.putExtra("User_id",id);
                //intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WordSet.this,SignWord.class);
                startActivity(intent);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WordSet.this,WrongWord.class);
                startActivity(intent);
            }
        });
        myListView = (ListView) findViewById(R.id.listview);
        ContentAdapter contentAdapter = new ContentAdapter(this,drawerList);
        myListView.setAdapter(contentAdapter);
        final DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        findViewById(R.id.leftmenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {

                    case 0://用户头像与id


                        break;
                    case 1://用户中心
                        Intent intent = new Intent(WordSet.this,UserCenter.class);
                        startActivity(intent);
                        break;
                    case 2://自定义学习计划

                        break;
                    case 3://意见反馈

                        break;
                    default:
                        break;
                }
                //点击任一项item项，都关闭左侧菜单
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            // 透明状态栏
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //系统条着色管理器 需要添加依赖  SystemBarTint.jar
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            // 激活状态栏
            tintManager.setStatusBarTintEnabled(true);
            //  激活导航栏
            tintManager.setNavigationBarTintEnabled(true);
            //通知栏所需颜色//如果没有设置颜色将是直接透明的
            tintManager.setStatusBarTintResource(R.color.blue);
        }
    }
    private void initData(){
        drawerList.add(new DrawerContent(R.drawable.user_im,"id",1));
        drawerList.add(new DrawerContent(R.drawable.bt1,"用户中心",2));
        drawerList.add(new DrawerContent(R.drawable.bt1,"自定义学习计划",3));
        drawerList.add(new DrawerContent(R.drawable.bt1,"意见反馈",4));
    }
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis() - exitTime > 2000){
                //System.currentTimeMillis()系统当前时间4
                Object mHelperUtils;
                Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else{
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode,keyEvent);
    }
}
