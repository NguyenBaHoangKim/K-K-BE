package com.KKApplication.KK.entity;

import lombok.Data;

@Data
public class UserResp {
    private Long id;
    private String username;
    private byte[] avatar;

    public UserResp() {
    }

    public UserResp(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.avatar = user.getAvatarBytes();
    }
}
