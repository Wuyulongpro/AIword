package com.example.aiword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aiword.activities.WordRecite;

public class Webtest extends AppCompatActivity{

    private int id;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webtest);
        Button button = (Button) findViewById(R.id.recite);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Webtest.this, WordRecite.class);
                intent.putExtra("userID",id);
                intent.putExtra("userToken",token);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        id = intent.getIntExtra("User_id",-1);
        token = intent.getStringExtra("User_token");
        findViewById(R.id.sql).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Webtest.this,sqltest.class);
                startActivity(intent);
            }
        });
    }
}
