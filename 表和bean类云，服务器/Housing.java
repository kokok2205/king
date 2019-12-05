package org.lanqiao.bean;

public class Housing {
    private int hid;//房子id
    private double hmoney;//房子价格
    private double harea;//房子面积
    private String haddress;//房子地址
    private String housetype;//房子房型
    private String hphoto;//房子图片
    private int horder;//房子预约量
    private String hsynopsis;//房子简介

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public double getHmoney() {
        return hmoney;
    }

    public void setHmoney(double hmoney) {
        this.hmoney = hmoney;
    }

    public double getHarea() {
        return harea;
    }

    public void setHarea(double harea) {
        this.harea = harea;
    }

    public String getHaddress() {
        return haddress;
    }

    public void setHaddress(String haddress) {
        this.haddress = haddress;
    }

    public String getHousetype() {
        return housetype;
    }

    public void setHousetype(String housetype) {
        this.housetype = housetype;
    }

    public String getHphoto() {
        return hphoto;
    }

    public void setHphoto(String hphoto) {
        this.hphoto = hphoto;
    }

    public int getHorder() {
        return horder;
    }

    public void setHorder(int horder) {
        this.horder = horder;
    }

    public String getHsynopsis() {
        return hsynopsis;
    }

    public void setHsynopsis(String hsynopsis) {
        this.hsynopsis = hsynopsis;
    }
}
