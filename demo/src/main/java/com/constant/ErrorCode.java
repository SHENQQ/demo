package com.constant;

/**
 * @author yyc
 */

public enum ErrorCode {

    // 通用错误
    COMMON_ERROR(100, "通用错误"),
    // 参数错误
    PARAM_ERROR(400, "参数错误"),
    // 没有权限
    NO_PERMISSION(403, "没有权限"),
    // 服务器内部错误
    SERVER_ERROR(500, "服务器内部错误"),

    // 组件大小相关
    SERVER_COMP_CHECK_CSV_ERROR(601, "参数错误 组件大小检查-修改前组件csv为空"),
    SERVER_COMP_CHECK_AFTER_CSV_ERROR(602, "参数错误 组件大小检查-修改后组件csv为空"),
    SERVER_COMP_CHECK_INTEGRATION_JSON_ERROR(603, "参数错误 组件大小检查-变更组件列表为空"),

    //备注相关
    SERVER_COMP_CHECK_REMARK_ERROR(604, "啊哈，任务备注已经不能再多了~");

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
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
}
