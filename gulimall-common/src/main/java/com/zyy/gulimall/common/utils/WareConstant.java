package com.zyy.gulimall.common.utils;

public class WareConstant {
  public enum PurchaseEnum {
    //base 基本属性 sale 销售属性
    CREATED("新建", 0),
    ASSIGNED("已分配",1),
    RECEIVE("已领取",2),
    FINISH("已完成",3),
    HASERROR("有异常",4);

    private String msg;

    // 定义一个带参数的构造器，枚举类的构造器只能使用 private 修饰
    private PurchaseEnum(String msg,int code) {
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

//    public static String getCodeByKey(String key){
//        for (PurchaseEnum attrEnum:values()){
//            if (attrEnum.msg.equals(key)){
//                return String.valueOf(attrEnum.getCode());
//            }
//        }
//        return null;
//    }
   }

    public enum PurchaseDetailEnum{
        //base 基本属性 sale 销售属性
        CREATED("新建", 0),
        ASSIGNED("已分配",1),
        BUYING("正在采购",2),
        FINISH("已完成",3),
        BUYERROR("采购失败",4);

        private String msg;

        private PurchaseDetailEnum(String msg,int code) {
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
}
