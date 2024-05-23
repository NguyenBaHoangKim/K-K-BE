package com.KKApplication.KK.dto;

import com.KKApplication.KK.entity.Status;
import lombok.Data;

import java.sql.Blob;
import java.sql.SQLException;

@Data
public class StatusResp {
    private Long statusId;
    private Long userId;
    private byte[] avt;
    private String username;
    private String status;
    private byte[] img;

    public StatusResp(Status status) {
        this.statusId = status.getStatusId();
        this.userId = status.getUser().getId();
        this.avt = status.getUser().getAvatarBytes();
        this.username = status.getUser().getUsername();
        this.status = status.getStatus();
        this.img = status.getBytesStatusImg();
    }
}
