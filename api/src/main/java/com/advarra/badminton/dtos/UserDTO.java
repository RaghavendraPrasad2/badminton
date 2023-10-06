package com.advarra.badminton.dtos;

public class UserDTO {

    private long userId;
    private String userName;
    private String contactInfo;
    private String gender;

    public UserDTO() {
    }

    public UserDTO(long userId, String userName, String contactInfo, String gender) {
        this.userId = userId;
        this.userName = userName;
        this.contactInfo = contactInfo;
        this.gender = gender;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
