package com.example.aiword.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aiword.R;

public class WrongWord extends AppCompatActivity {

    private String[] data = {"apple", "atmosphere","battle","cat","dog","dose","elephant","frog","gas","height","iron","jack","knock","lemon","metal","nod",
            "online","paddle","quarter","rob","stop","trust","umbrella","vote","wolf","xo","yellow","zoom"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrongword);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(WrongWord.this,android.R.layout.simple_list_item_1, data);
        ListView listView = (ListView) findViewById(R.id.w_word);
        listView.setAdapter(adapter);
    }
}
