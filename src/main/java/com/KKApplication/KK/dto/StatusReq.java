package com.KKApplication.KK.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Blob;

@Data
public class StatusReq {
    @NotNull
    private Long userId;
    private String status;
    private Blob img;

    public StatusReq() {
    }

    public StatusReq(Long userId, String status, Blob img) {
        this.userId = userId;
        this.status = status;
        this.img = img;
    }
}
