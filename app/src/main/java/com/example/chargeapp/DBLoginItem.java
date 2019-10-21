package com.example.chargeapp;

//数据库的注册表的实体类，封装了ID，用户名USERNAME，密码PASSWORD
public class DBLoginItem {
    private  int id;
    private String userName;
    private String passWord;

    //construct构造函数
    public DBLoginItem() {
        this.userName = "";
        this.passWord = "";
    }

    //construct构造函数
    public DBLoginItem(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}

