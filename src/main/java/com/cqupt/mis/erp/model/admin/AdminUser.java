package com.cqupt.mis.erp.model.admin;

/**
 * Created by yangqing on 2016/6/2.
 */
public class AdminUser {
    private String adminId;
    private String adminName;
    private String password;
    private String position;
    private String email;
    private String tel;
    private String rem1;
    private String role;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRem1() {
        return rem1;
    }

    public void setRem1(String rem1) {
        this.rem1 = rem1;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
