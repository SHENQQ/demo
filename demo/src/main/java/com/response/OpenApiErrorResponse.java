package com.response;

public class OpenApiErrorResponse extends ErrorResponse {

    public static final int PARAM_ERROR_APPNAME_EMPTY = 1200;
    public static final String MESSAGE_PARAM_ERROR_APPNAME_EMPTY = "appName/componentName不能为空";

    public static final int PARAM_ERROR_BUILD_TYPE_NAME_EMPTY = 1201;
    public static final String MESSAGE_PARAM_ERROR_BUILD_TYPE_NAME_EMPTY = "构建类型名字不能为空";

    public static final int PARAM_ERROR_PROJECT_NOT_FOUND = 1202;
    public static final String MESSAGE_PARAM_ERROR_PROJECT_NOT_FOUND = "没有找到当前项目";

    public static final int PARAM_ERROR_BUILD_TYPE_NOT_FOUND = 1203;
    public static final String MESSAGE_PARAM_ERROR_BUILD_TYPE_NOT_FOUND = "没有找到指定构建类型";

    public static final int PARAM_ERROR_COMPONTENT_RELEASE_NOT_FOUND = 1204;
    public static final String MESSAGE_PARAM_ERROR_COMPONTENT_RELEASE_NOT_FOUND = "没有找到您想要的组件发版记录";

    public static final int PARAM_ERROR_APP_BUILD_INFO_NOT_FOUND = 1205;
    public static final String MESSAGE_PARAM_ERROR_APP_BUILD_INFO_NOT_FOUND = "没有找到您想要的打包记录";

    public static final int PARAM_ERROR_TARGET_BUILD_TYPE_NOT_FOUND = 1206;
    public static final String MESSAGE_PARAM_ERROR_TARGET_BUILD_TYPE_NOT_FOUND = "没有找到你想集成的这个应用的构建类型";

    public static final int PARAM_ERROR_APP_DEPENDENCES_NOT_FOUND = 1207;
    public static final String MESSAGE_PARAM_ERROR_APP_DEPENDENCES_NOT_FOUND = "没有找到该应用的组件依赖列表";

    public static final int PARAM_ERROR_BUILD_BUMBER_EMPTY = 1208;
    public static final String MESSAGE_PARAM_ERROR_BUILD_BUMBER_EMPTY = "buildNumber不能为空";

    public static final int PARAM_ERROR_BU_NOT_FOUND = 1209;
    public static final String MESSAGE_PARAM_ERROR_BU_NOT_FOUND = "没有找到当前BU";

    public static final int PARAM_ERROR_PROJECT_TAG_NOT_FOUND = 1210;
    public static final String MESSAGE_PARAM_ERROR_PROJECT_TAG_NOT_FOUND = "没有找到当前tagName";

    // Only one name can be provided
    public static final int PARAM_ERROR_ONLY_ONE_NAME_CAN_BE_PROVIDED = 1211;
    public static final String MESSAGE_PARAM_ERROR_ONLY_ONE_NAME_CAN_BE_PROVIDED = "appName componentName 只能传入一个";

    public static final int PARAM_ERROR_TASK_NOT_FOUND = 1212;
    public static final String MESSAGE_PARAM_ERROR_TASK_NOT_FOUND = "没有找到当前task";

    public static final int PARAM_ERROR_NAME_EMPTY = 1213;
    public static final String MESSAGE_ERROR_NAME_EMPTY = "必须提供 appName 或 componentName";

    public static final int PARAM_ERROR_STATUS_NOT_FOUND = 1214;
    public static final String MESSAGE_PARAM_ERROR_STATUS_NOT_FOUND = "没有找到当前状态";

    public static final int PARAM_ERROR_BRANCH_HAS_BEEN_LOCKED = 1215;
    public static final String MESSAGE_PARAM_ERROR_BRANCH_HAS_BEEN_LOCKED = "当前分支已经被锁了，您没有权限发起集成";

    public static final int PARAM_ERROR_NO_INTEGRATABLE_BRANCH = 1216;
    public static final String MESSAGE_PARAM_ERROR_NO_INTEGRATABLE_BRANCH = "当前应用没有开放集成的分支";
}