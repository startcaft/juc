package com.startcaft.basic.core.enums;

/**
 * @author startcaft
 * 状态枚举类
 */
public enum States {

    NORMAL(100,"可用"),
    LOCKED(200,"不可用");

    private String msg;
    private Integer code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    private States(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static States getStates(Integer code){
        switch(code){
            case 100:
                return NORMAL;
            case 200:
                return LOCKED;
            default:
                return NORMAL;
        }
    }
}
