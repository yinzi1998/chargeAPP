package com.example.chargeapp;

//数据库的记账表的实体类，封装了ID，用户名NAME，日期DATE，吃穿用类型TYPE，具体事项DETAIL，金额MONEY
public class DBChargeItem {
    private  int id;
    private String name;
    private String date;
    private String type;
    private String detail;
    private String money;

    public DBChargeItem(String name, String date, String type, String detail, String money) {
        this.name = name;
        this.date = date;
        this.type = type;
        this.detail = detail;
        this.money = money;
    }

    //construct构造函数
    public DBChargeItem() {
        this.name = "";
        this.date = "";
        this.type = "";
        this.detail = "";
        this.money = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}

