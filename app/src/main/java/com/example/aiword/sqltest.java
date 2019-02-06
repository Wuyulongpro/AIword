package com.example.aiword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sqltest extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqltest);
        dbHelper = new MyDatabaseHelper(this,"UserStore.db",null,1);
        Button button = (Button) findViewById(R.id.create_database);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.getWritableDatabase();
            }
        });
    }
}
