package com.response;


import com.constant.ResponseStatus;
import com.constant.ErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "统一响应对象", description = "统一响应对象")
public class CommonResponse<T> {
    @ApiModelProperty(value = "响应状态码。1：成功, 0：失败", required = true)
    private int status;
    @ApiModelProperty(value = "响应数据")
    private T data;
    @ApiModelProperty(value = "错误数据")
    private ErrorMsg error;

    public CommonResponse() {
        this.status = ResponseStatus.OK;
    }

    public CommonResponse(T data) {
        this();
        this.setData(data);
    }

    public CommonResponse(int status, T data, ErrorMsg error){
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorMsg getError() {
        return error;
    }

    public void setError(ErrorMsg error) {
        this.error = error;
    }

    public static Builder builder() {
        return new CommonResponse.Builder();
    }

    public static class Builder {
        private int status;
        private ErrorMsg error;
        private Object data;

        public int getStatus() {
            return status;
        }

        public Builder setStatus(int status) {
            this.status = status;
            return this;
        }

        public ErrorMsg getErrorMsg() {
            return error;
        }

        public Builder setErrorMsg(ErrorMsg error) {
            this.error = error;
            return this;
        }

        public Builder setErrorCode(ErrorCode errorCodeEnum) {
            this.error = new ErrorMsg(errorCodeEnum.getCode(),errorCodeEnum.getMessage());
            return this;
        }

        public Object getData() {
            return data;
        }

        public Builder setData(Object data) {
            this.data = data;
            return this;
        }

        public CommonResponse build() {
            return new CommonResponse(status, data, error);
        }
    }
}