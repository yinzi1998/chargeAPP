package com.example.chargeapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//数据库访问的类，用于创建表更新表之类的
public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "charge.db";
    //登录的表
    public static final String TB_LOGIN = "tb_login";
    //记账的表
    public static final String TB_CHARGE = "tb_charge";

    //构造函数
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);

    }
    //构造函数
    public DBHelper(Context context){
        super(context, DB_NAME, null, VERSION);

    }

    //创建数据库的操作
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建表
        sqLiteDatabase.execSQL("CREATE TABLE " + TB_LOGIN + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TB_CHARGE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DATE TEXT, TYPE TEXT, DETAIL TEXT, MONEY TEXT)");
    }

    //升级数据库的操作
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
