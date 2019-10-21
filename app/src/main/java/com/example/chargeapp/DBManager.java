package com.example.chargeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

//数据库业务的操作类，通过这个类对数据库进行管理，并且对外面提供方法
public class DBManager {
    private DBHelper dbHelper;
    private String TBNAME_CHARGE;
    private String TBNAME_LOGIN;

    //构造函数
    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME_CHARGE = DBHelper.TB_CHARGE;
        TBNAME_LOGIN = DBHelper.TB_LOGIN;
    }

    //注册表插入一条数据
    public void add_login(DBLoginItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("USERNAME", item.getUserName());
        values.put("PASSWORD", item.getPassWord());
        db.insert(TBNAME_LOGIN, null, values);
        db.close();
    }

    //记账表插入一条数据
    public void add_charge(DBChargeItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("DATE", item.getDate());
        values.put("TYPE", item.getType());
        values.put("DETAIL", item.getDetail());
        values.put("MONEY", item.getMoney());
        db.insert(TBNAME_CHARGE, null, values);
        db.close();
    }

    //注册表插入多条数据
    public void addAll_login(List<DBLoginItem> list){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (DBLoginItem i : list){
            ContentValues values = new ContentValues();
            values.put("USERNAME", i.getUserName());
            values.put("PASSWORD", i.getPassWord());
            db.insert(TBNAME_LOGIN, null, values);
        }
        db.close();
    }

    //记账表插入多条数据
    public void addAll_charge(List<DBChargeItem> list){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (DBChargeItem i : list){
            ContentValues values = new ContentValues();
            values.put("DATE", i.getDate());
            values.put("TYPE", i.getType());
            values.put("DETAIL", i.getDetail());
            values.put("MONEY", i.getMoney());
            db.insert(TBNAME_CHARGE, null, values);
        }
        db.close();
    }

    //注册表显示所有数据操作函数
    public List<DBLoginItem> showListAll_login(){
        List<DBLoginItem> loginList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME_LOGIN, null, null, null, null, null, null);
        if(cursor != null){
            loginList = new ArrayList<DBLoginItem>();
            while (cursor.moveToNext()){
                DBLoginItem item = new DBLoginItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setUserName(cursor.getString(cursor.getColumnIndex("USERNAME")));
                item.setPassWord(cursor.getString(cursor.getColumnIndex("PASSWORD")));
                loginList.add(item);
            }
            cursor.close();
        }
        db.close();
        return loginList;
    }

    //记账显示所有数据操作函数
    public List<DBChargeItem> showListAll_charge(){
        List<DBChargeItem> chargeList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME_CHARGE, null, null, null, null, null, null);
        if(cursor != null){
            chargeList = new ArrayList<DBChargeItem>();
            while (cursor.moveToNext()){
                DBChargeItem item = new DBChargeItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setDate(cursor.getString(cursor.getColumnIndex("DATE")));
                item.setType(cursor.getString(cursor.getColumnIndex("TYPE")));
                item.setDetail(cursor.getString(cursor.getColumnIndex("DETAIL")));
                item.setMoney(cursor.getString(cursor.getColumnIndex("MONEY")));
                chargeList.add(item);
            }
            cursor.close();
        }
        db.close();
        return chargeList;
    }

    //注册表删除一条数据
    public void delete_login(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.delete(TBNAME_LOGIN, "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }

    //记账删除一条数据
    public void delete_charge(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.delete(TBNAME_CHARGE, "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }

    //注册表删除所有数据操作函数
    public void deleteAll_login(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.delete(TBNAME_LOGIN, null, null);
        db.close();
    }

    //记账表删除所有数据操作函数
    public void deleteAll_charge(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.delete(TBNAME_CHARGE, null, null);
        db.close();
    }

    //注册表更新操作
    public void update_login(DBLoginItem item){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("USERNAME", item.getUserName());
        values.put("PASSWORD", item.getPassWord());
        db.update(TBNAME_LOGIN, values, "ID=?", new String[]{String.valueOf(item.getId())});
        db.close();
    }

    //记账表更新操作
    public void update_charge(DBChargeItem item){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("DATE", item.getDate());
        values.put("TYPE", item.getType());
        values.put("DETAIL", item.getDetail());
        values.put("MONEY", item.getMoney());
        db.update(TBNAME_CHARGE, values, "ID=?", new String[]{String.valueOf(item.getId())});
        db.close();
    }

    //注册表查询返回结果操作
    public  DBLoginItem findById_login(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME_LOGIN, null, "ID=?", new String[]{String.valueOf(id)}, null, null, null);
        DBLoginItem item = null;
        if(cursor!=null && cursor.moveToFirst()){
            item = new DBLoginItem();
            item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            item.setUserName(cursor.getString(cursor.getColumnIndex("USERNAME")));
            item.setPassWord(cursor.getString(cursor.getColumnIndex("PASSWORD")));
            cursor.close();
        }
        db.close();
        return item;
    }

    //记账表查询返回结果操作
    public  DBChargeItem findById_charge(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME_CHARGE, null, "ID=?", new String[]{String.valueOf(id)}, null, null, null);
        DBChargeItem item = null;
        if(cursor!=null && cursor.moveToFirst()){
            item = new DBChargeItem();
            item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            item.setDate(cursor.getString(cursor.getColumnIndex("DATE")));
            item.setType(cursor.getString(cursor.getColumnIndex("TYPE")));
            item.setDetail(cursor.getString(cursor.getColumnIndex("DETAIL")));
            item.setMoney(cursor.getString(cursor.getColumnIndex("MONEY")));
            cursor.close();
        }
        db.close();
        return item;
    }

}
