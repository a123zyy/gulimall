package com.zyy.gulimall.common.consted;

public enum  AttrEnum {

    BASE_TYPE("", 1), SALE_TYPE("",0);

    private String msg;

    // 定义一个带参数的构造器，枚举类的构造器只能使用 private 修饰
    private AttrEnum(String msg,int code) {
        this.msg = msg;
        this.code = code;
    }

    public int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
