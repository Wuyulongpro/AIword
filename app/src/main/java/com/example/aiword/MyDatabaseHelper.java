package com.example.aiword;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by 22842 on 2019/1/17.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_USER = "create table User ("
            + "id integer primary key autoincrement,"
            + "name text,"
            + "password text,"
            + "phone integer,"
            + "choice integer)";
    private Context mContext;
    public  MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory,version);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER);
        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
}
