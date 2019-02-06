package com.example.aiword;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 22842 on 2019/1/26.
 */

public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
    public static void sendOkHttpRequestWithHeader(String address, String HeaderName, String HeaderData, okhttp3.Callback callback ){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).addHeader(HeaderName,HeaderData).build();
        client.newCall(request).enqueue(callback);
    }
}
