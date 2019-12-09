package com.response;

import com.constant.ErrorCode;
import com.constant.ResponseStatus;

public class ErrorResponse extends CommonResponse {
    public static final int CODE_USER_NOT_LOGIN = 1100;
    private static final String MESSAGE_USER_NOT_LOGIN = "用户未登录";
    public static final int CODE_INVALID_PARAM = 1101;
    private static final String MESSAGE_INVALID_PARAM = "请求参数有误";
    public static final int CODE_PERMISSION_DENY = 1102;
    private static final String MESSAGE_PERMISSION_DENY = "用户权限不足";
    public static final ErrorResponse USER_NOT_LOGIN = new ErrorResponse(CODE_USER_NOT_LOGIN, MESSAGE_USER_NOT_LOGIN);
    public static final ErrorResponse INVALID_PARAM = new ErrorResponse(CODE_INVALID_PARAM, MESSAGE_INVALID_PARAM);
    public static final ErrorResponse PERMISSION_DENY = new ErrorResponse(CODE_PERMISSION_DENY, MESSAGE_PERMISSION_DENY);


    public ErrorResponse() {
        setStatus(ResponseStatus.ERROR);
    }

    public ErrorResponse(Throwable throwable) {
        this();
        setError((new ErrorMsg(ErrorCode.COMMON_ERROR.getCode(), throwable.getMessage())));
    }

    public ErrorResponse(ErrorCode errorCode) {
        this();
        setError(new ErrorMsg(errorCode.getCode(), errorCode.getMessage()));
    }

    public ErrorResponse(String comErrMsg) {
        this();
        setError((new ErrorMsg(ErrorCode.COMMON_ERROR.getCode(), comErrMsg)));
    }

    public ErrorResponse(int errorCode, String comErrMsg) {
        this();
        setError(new ErrorMsg(errorCode, comErrMsg));
    }

    public <D> ErrorResponse(int errorCode, String comErrMsg, D errorDetail) {
        setStatus(0);
        setError(new ErrorMsg(errorCode, comErrMsg, errorDetail));
    }

}
