package com.KKApplication.KK.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserReq {
    private String username;
    private String email;
    private String password;
//    private MultipartFile avatar;
//
    public UserReq() {
    }

    public UserReq(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
