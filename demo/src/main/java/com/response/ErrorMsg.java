package com.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "错误数据", description = "错误数据")
public class ErrorMsg<D> implements Serializable {
    @ApiModelProperty(value = "错误码", name = "code")
    private int code;
    @ApiModelProperty(value = "错误信息", name = "message")
    private String message;
    @ApiModelProperty(value = "错误详情", name = "detail")
    private D detail;

    public ErrorMsg(int code, String message) {
        setCode(code);
        setMessage(message);
    }

    public ErrorMsg(int code, String message, D detail) {
        setCode(code);
        setMessage(message);
        setDetail(detail);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public D getDetail() {
        return detail;
    }

    public void setDetail(D detail) {
        this.detail = detail;
    }
}
