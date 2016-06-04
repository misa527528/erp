package com.cqupt.mis.erp.model.materialpurchase;

public class MaterialPurchaseRecord {
    private String userUnique;//用户在竞赛中的唯一标识
    private String materialName;//原材料名称
    private int materialNumber;//原材料数量
    private String wareHouseName;//存放仓库名称
    private String orderId;//订单编号
    private int happenTime;//购买时间
    private int endTime;//入库时间

    public String getUserUnique() {
        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getWareHouseName() {
        return wareHouseName;
    }

    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(int materialNumber) {
        this.materialNumber = materialNumber;
    }

    public int getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(int happenTime) {
        this.happenTime = happenTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

}
