package com.KKApplication.KK.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;

@Entity
@Table(name = "status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Long statusId;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "status")
    private String status;

    @Lob
    @Column(name = "img")
    private Blob img;

    @Transient
    public byte[] getBytesStatusImg() {
        if (this.img == null) {
            return null;
        }
        try (InputStream is = img.getBinaryStream()) {
            return is.readAllBytes();
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Error reading blob to byte array", e);
        }
    }

    @Override
    public String toString() {
        return "Status{" +
                "statusId=" + statusId +
                ", user=" + user +
                ", status='" + status + '\'' +
                ", img=" + img +
                '}';
    }
}