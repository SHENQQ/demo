package com.utils;

public interface ReqCallBack {
    /**
     * 响应成功
     */
    void onReqSuccess(String result);

    /**
     * 响应失败
     */
    void onReqFailed(String errorMsg);
}
