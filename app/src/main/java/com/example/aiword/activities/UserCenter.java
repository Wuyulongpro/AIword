package com.example.aiword.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aiword.OptionAdapter;
import com.example.aiword.R;
import com.example.aiword.UserOptions;

import java.util.ArrayList;
import java.util.List;

public class UserCenter extends AppCompatActivity {

    private List<UserOptions> options = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
        initOptions();
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                UserCenter.this.finish();
            }
        });
        OptionAdapter optionAdapter = new OptionAdapter(this,R.layout.options_item,options);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(optionAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserOptions userOptions = options.get(position);
                switch (position) {

                    case 0://账号管理
                        Toast.makeText(UserCenter.this,"账号管理",Toast.LENGTH_SHORT).show();
                        break;
                    case 1://通知管理
                        Toast.makeText(UserCenter.this,"通知管理",Toast.LENGTH_SHORT).show();
                        break;
                    case 2://词集拓展
                        Toast.makeText(UserCenter.this,"词集拓展",Toast.LENGTH_SHORT).show();
                        break;
                    case 3://安全与隐私
                        Toast.makeText(UserCenter.this,"安全与隐私",Toast.LENGTH_SHORT).show();
                        break;
                    case 4://关于我们
                        Toast.makeText(UserCenter.this,"关于我们",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }
    private void initOptions(){
        UserOptions account = new UserOptions("账号管理");
        options.add(account);
        UserOptions message = new UserOptions("通知管理");
        options.add(message);
        UserOptions expand = new UserOptions("词集拓展");
        options.add(expand);
        UserOptions security = new UserOptions("安全与隐私");
        options.add(security);
        UserOptions about = new UserOptions("关于我们");
        options.add(about);
    }
}
