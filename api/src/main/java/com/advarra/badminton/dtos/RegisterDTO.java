package com.advarra.badminton.dtos;

import java.sql.Timestamp;

public class RegisterDTO {

    private String userName;
    private String password;

    private String contactInfo;

    private String gender;

    public RegisterDTO() {
    }

    public RegisterDTO(String userName, String password, String contactInfo, String gender) {
        this.userName = userName;
        this.password = password;
        this.contactInfo = contactInfo;
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }


    @Override
    public String toString() {
        return "RegisterDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
