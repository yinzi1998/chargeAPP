package com.example.chargeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //主界面登录按钮的响应事件
    public void main_denglu(View view){
        DBManager manager = new DBManager(MainActivity.this);
        EditText user = findViewById(R.id.editText_main_name);
        EditText psw = findViewById(R.id.editText_main_psw);
        String userStr = user.getText().toString();
        String pswStr = psw.getText().toString();
        //tab为0的时候用户名不存在，1的时候存在
        int tab = 0;

        if(userStr.equals("") || userStr==null){
            Toast.makeText(MainActivity.this,"用户名不能为空哦",Toast.LENGTH_SHORT).show();
        } else if(pswStr.equals("") || pswStr==null){
            Toast.makeText(MainActivity.this,"密码不能为空哦",Toast.LENGTH_SHORT).show();
        }else{
            for(DBLoginItem i : manager.showListAll_login()){
                if(userStr.equals(i.getUserName())){
                    if(pswStr.equals(i.getPassWord())){
                        Toast.makeText(MainActivity.this,"登录成功啦",Toast.LENGTH_SHORT).show();
                        Intent index = new Intent(this,IndexActivity.class);
                        Log.i(TAG, "main_denglu: 登录成功，跳转起始页");
                        //用户名传入index界面
                        index.putExtra("name", userStr);
                        startActivity(index);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"密码不正确哦",Toast.LENGTH_SHORT).show();
                    }
                    tab = 1;
                    break;
                }
            }
            if(tab == 0){
                Toast.makeText(MainActivity.this,"用户名不存在哦",Toast.LENGTH_SHORT).show();
            }
            tab = 0;
        }
    }

    //主界面注册按钮的相应事件
    public void main_login(View view){
        Intent login = new Intent(this,LoginActivity.class);
        Log.i(TAG, "main_login: 点击注册按钮，跳转注册界面");
        startActivity(login);
    }
}
