package com.cqupt.mis.erp.model.materialpurchase;

public class MaterialInventory {
    private String userUnique;//用户在竞赛中的唯一标识
    private String materialName;//原材料名称
    private int mNumber;//原材料数量
    private String wareHouseName;//存放在的仓库名称

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getUserUnique() {
        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
    }

    public String getWareHouseName() {
        return wareHouseName;
    }

    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    public int getmNumber() {
        return mNumber;
    }

    public void setmNumber(int mNumber) {
        this.mNumber = mNumber;
    }
}
