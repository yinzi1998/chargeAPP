package com.example.chargeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    //注册界面注册按钮的相应事件
    public void login_login(View view){
        DBManager manager = new DBManager(LoginActivity.this);
        EditText ed_name = findViewById(R.id.editText_login_name);
        EditText ed_psw1 = findViewById(R.id.editText_login_psw1);
        EditText ed_psw2 = findViewById(R.id.editText_login_psw2);
        String name = ed_name.getText().toString();
        String psw1 = ed_psw1.getText().toString();
        String psw2 = ed_psw2.getText().toString();
        //tab为1的时候用户名没有重复，为0的时候重复了主注册失败
        int tab = 1;

        if(name==null || name.equals("")){
            Toast.makeText(LoginActivity.this,"注册用户名不能为空哦",Toast.LENGTH_SHORT).show();
        }else{
            for(DBLoginItem i : manager.showListAll_login()){
                Log.i(TAG, "login_login: 注册表中用户名= " + i.getUserName());
                if(name.equals(i.getUserName())){
                    Toast.makeText(LoginActivity.this,"用户名已被注册了哦",Toast.LENGTH_SHORT).show();
                    tab = 0;
                    break;
                }
            }
            if(tab==1){
                if(psw1==null || psw1.equals("")){
                    Toast.makeText(LoginActivity.this,"密码不能为空哦",Toast.LENGTH_SHORT).show();
                }else if(psw2==null || psw2.equals("")){
                    Toast.makeText(LoginActivity.this,"请重复密码哦",Toast.LENGTH_SHORT).show();
                }else if(!psw1.equals(psw2)){
                    Toast.makeText(LoginActivity.this,"两次密码要相同哦",Toast.LENGTH_SHORT).show();
                }else{
                    DBLoginItem login = new DBLoginItem(name, psw1);
                    manager.add_login(login);
                    Log.i(TAG, "login_login: 一条用户注册信息插入数据库");
                    Toast.makeText(LoginActivity.this,"注册成功啦",Toast.LENGTH_SHORT).show();
                    Intent main = new Intent(this,MainActivity.class);
                    Log.i(TAG, "login_login: 注册成功，跳转登录界面");
                    startActivityForResult(main,1);
                }
            }
        }



    }
}
