package com.example.pankajknitting.model;

import android.net.Uri;

public class Users {
    String userName, password, userId, lastMessage, mail;
    Uri profilepic;

    public Users(Uri profilepic, String userName, String password, String userId, String lastMessage, String mail){
        this.profilepic = profilepic;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.userId = userId;
        this.lastMessage = lastMessage;
    }
    public Users(){}    // Empty constructor as per firebase guideline

    //Constructor to implemnet Signup functionality
    public Users(String userName, String mail, String password){
        this.userName = userName;
        this.password = password;
        this.mail = mail;
    }

    public Uri getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(Uri profilepic) {
        this.profilepic = profilepic;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
