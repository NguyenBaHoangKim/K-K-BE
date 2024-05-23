package com.KKApplication.KK.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
//    private String firstName;
//    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @Lob
    @Column(name = "avatar")
    private Blob avatar;

    @OneToMany(
            mappedBy = "user",
            cascade = {
                CascadeType.REMOVE,
                CascadeType.MERGE,
                CascadeType.PERSIST,
                CascadeType.DETACH,
                CascadeType.REFRESH
            })
    private List<Status> statuses;

    public User() {
    }

    public User(Long id, String username, String email, String password, Blob avatar, List<Status> statuses) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.statuses = statuses;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password, Blob avatar) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Transient  // This annotation is to ensure this field is not mapped to any column
    public byte[] getAvatarBytes() {
        if (this.avatar == null) {
            return null;
        }
        try (InputStream is = avatar.getBinaryStream()) {
            return is.readAllBytes();
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Error reading blob to byte array", e);
        }
    }

    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avatar=" + avatar + '\'' +
                ", statuses=" + statuses +
                '}';
    }
}
