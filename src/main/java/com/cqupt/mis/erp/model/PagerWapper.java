package com.cqupt.mis.erp.model;

public class PagerWapper {
    /**
     * 状态码
     */
    private String statusCode;
    /**
     * 数据
     */
    private Object datas;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }
}
