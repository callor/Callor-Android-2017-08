package com.callor.dbexam.db;

/**
 * Created by callor on 2017-09-08.
 */

public class MemberVO {

    private String userId ;
    private String userPassword;
    private String userEmail ;
    private String userPhone ;

    public MemberVO(){

    }

    public MemberVO(String userId, String userPassword, String userEmail, String userPhone) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
