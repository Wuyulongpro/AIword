package com.example.aiword.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aiword.webService.HttpUtil;
import com.example.aiword.R;
import com.example.aiword.webService.WordInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class WordRecite extends AppCompatActivity {

    TextView responseText;
    private Button button;
    private EditText editText;
    private EditText editNum;
    private int id;
    private String beginWord;
    private String token;
    private int num;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String ReturnMessage = (String) msg.obj;
            //Json的解析类对象
            Log.i("eeee", ReturnMessage);
            JsonParser parser = new JsonParser();
            //将JSON的String 转成一个JsonArray对象
            JsonArray jsonArray = parser.parse(ReturnMessage).getAsJsonArray();
            Gson gson = new Gson();
            ArrayList<WordInfo> wordInfoList = new ArrayList<>();
            for(JsonElement word : jsonArray) {
                WordInfo wordInfo = gson.fromJson(word, WordInfo.class);
                wordInfoList.add(wordInfo);
                List<WordInfo.AttListBean> attListBeans = wordInfo.getAttList();
                for(WordInfo.AttListBean attList : attListBeans){
                    Log.i("单词意思", attList.getWordatt());
                }
                Log.i("qqqq", wordInfo.getWord().getLevenshtein());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_recite);
        Intent intent = getIntent();
        id = intent.getIntExtra("userID",-1);
        token = intent.getStringExtra("userToken");
        button = (Button)findViewById(R.id.get);
        responseText = (TextView) findViewById(R.id.response_text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText = (EditText)findViewById(R.id.begiWord);
                editNum = (EditText)findViewById(R.id.wordNumber);
                editNum.setInputType(InputType.TYPE_CLASS_NUMBER);
                beginWord = editText.getText().toString();
                final String temp = editNum.getText().toString();
                if(beginWord.trim().equals("")){
                    Toast.makeText(WordRecite.this,"词根不能为空",Toast.LENGTH_SHORT).show();
                }
                else {
                     if ((temp.trim().equals("") || Integer.parseInt(temp) == 0)) {
                        Toast.makeText(WordRecite.this, "请输入获取单词数量", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        num = Integer.parseInt(temp);
                         HttpUtil.sendOkHttpRequestWithHeader("http://129.211.113.93:8080/word/getword?type=cet4&userID=" + id + "&beginword=" + beginWord + "&num=" + num, "token", token, new okhttp3.Callback(){
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
                }
            }
        });
    }

}

