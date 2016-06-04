package com.cqupt.mis.erp.model.advertisement;

/**
 * Created by yangqing on 2016/6/2.
 * AdvertisementStatusOfUser
 * 用户在投广告费和选订单期间的状态信息，具体包括是否完成的广告费的投放，订单的选择以及是否可以进入选单的界面
 */
public class AdvertisementStatusOfUser {
    private String userUnique;//用户唯一标识符
    private Integer period;//表示时间，总第几期
    private Integer finishAdvertiseFlag;//表示用户是否完成了当前期的广告费投放，0表示未完成，1表示完成
    private Integer finishOrderFlag;//表示用户是否完成了当前期的所有订单选择，0表示未完成，1表示已经完成
    private Integer chooseOrderFlag;//表示用户是否可以开始当前期的选订单工作，0表示选单尚未开始，1表示选单正在进行中。

    public String getUserUnique() {
        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getFinishAdvertiseFlag() {
        return finishAdvertiseFlag;
    }

    public void setFinishAdvertiseFlag(Integer finishAdvertiseFlag) {
        this.finishAdvertiseFlag = finishAdvertiseFlag;
    }

    public Integer getChooseOrderFlag() {
        return chooseOrderFlag;
    }

    public void setChooseOrderFlag(Integer chooseOrderFlag) {
        this.chooseOrderFlag = chooseOrderFlag;
    }

    public Integer getFinishOrderFlag() {
        return finishOrderFlag;
    }

    public void setFinishOrderFlag(Integer finishOrderFlag) {
        this.finishOrderFlag = finishOrderFlag;
    }
}
