package com.example.aiword.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by 22842 on 2019/1/17.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_USER = "create table User ("
            + "id integer primary key autoincrement not null,"
            + "name text,"
            + "password text,"
            + "phone integer,"
            + "choice integer)";
    public static final String CREATE_WORD = "create table Word ("
        + "wordID integer primary key not null,"
        +"word text not null,"
        +"wordpart text,"
        +"wordpast text,"
        +"wordpast_part text,"
        +"wordplural text,"
        +"wordcomparative text,"
        +"wordsuperlative text,"
        +"wordthird text,"
        +"wordread text,"
        +"examplesentence text)";
    private Context mContext;
    public  MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, "my.db", factory,1);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_WORD);
        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("ALTER TABLE User ADD phone VARCHAR(12)");
    }
}
