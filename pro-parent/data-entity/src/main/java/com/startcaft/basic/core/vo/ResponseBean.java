package com.startcaft.basic.core.vo;

/**
 *
 * @author startcaft
 * @date 2018/3/1
 */
public class ResponseBean<T> {

    /**
     * 请求是否成功
     */
    private boolean reqSuccess = false;
    /**
     * 消息提示
     */
    private String msg;
    /**
     * 携带数据
     */
    private T data;

    public boolean isReqSuccess() {
        return reqSuccess;
    }

    public void setReqSuccess(boolean reqSuccess) {
        this.reqSuccess = reqSuccess;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseBean(boolean reqSuccess, String msg, T data) {
        this.reqSuccess = reqSuccess;
        this.msg = msg;
        this.data = data;
    }

    public ResponseBean() {
    }
}
