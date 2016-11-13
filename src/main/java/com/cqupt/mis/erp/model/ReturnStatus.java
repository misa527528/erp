package com.cqupt.mis.erp.model;

/**
 * Created by 杨青 on 2016/8/28.
 */
public class ReturnStatus {
    // 返回失败的状态码
    public static final int ERROR = 0;

    // 返回成功的状态码
    public static final int SUCCESS = 1;

    // 以下是登陆后跳转到相应页面的状态码
    // 跳转到注册用户页面的状态码
    public static final int REGISTERPAGE = 2;

    // 跳转到管理员页面状态码
    public static final int ADMINPAGE = 3;

    // 未加入分组页面状态码
    public static final int NOTINAGROUPAGE = 4;

    // 普通游戏者加入分组但还没开始比赛页面状态码
    public static final int JOINGROUPBUTNOTBEGINPAGE = 5;

    // 游戏创建者加入分组但还没开始比赛的页面状态码
    public static final int GAMECREATORBUTNOTSTARTPAGE = 6;

    // 比赛开始页面状态码
    public static final int GAMERUNNINGPAGE = 7;

    // 比赛结束页面状态码
    public static final int GAMEFINISHEDPAGE = 8;

    // 比赛破产页面状态码
    public static final int BROKENUPPAGE = 9;
}
