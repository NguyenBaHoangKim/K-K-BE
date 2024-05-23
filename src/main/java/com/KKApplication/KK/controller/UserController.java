package com.KKApplication.KK.controller;

import com.KKApplication.KK.entity.User;
import com.KKApplication.KK.entity.UserReq;
import com.KKApplication.KK.entity.UserResp;
import com.KKApplication.KK.service.UserService;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test(){
        return "test dc rui ne";
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/str")
    public List<UserResp> getUserStr(){
        return userService.getUserStr();
    }

//    @PostMapping("/create")
//    public ResponseEntity<User> createUser(@RequestBody User user){
//        userService.saveUser(user);
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
//    }


    @PostMapping("/create-no")
    public ResponseEntity<User> createUser(@RequestBody UserReq user){
        User newUser = new User(user.getUsername(), user.getEmail(), user.getPassword());
        userService.saveUser(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

//    @PostMapping("/create")
//    public ResponseEntity<User> createUser(
//            @RequestPart("username") String username,
//            @RequestPart("email") String email,
//            @RequestPart("username") String password,
//            @RequestPart("avatar") MultipartFile avatar) throws IOException {
//
//        User newUser = new User(username, email, password, avatar.getBytes());
//        System.out.println("avt : " + avatar);
//        System.out.println("avt byte : " + avatar.getBytes());
//        userService.saveUser(newUser);
//        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
//    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(
            HttpServletRequest request,
            @RequestPart("user") UserReq user,
            @NotNull @RequestPart("avatar") MultipartFile avatar)
            throws IOException, SQLException {
        byte[] avatarBytes = avatar.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(avatarBytes);

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setAvatar(blob);

        System.out.println(newUser.toString());
        userService.saveUser(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


//    @PostMapping("/create-ava")
//    public ResponseEntity<?> createUserWithAva(
//            @RequestPart("user") User user,
//            @RequestPart("file") MultipartFile file
//    ) {
//        System.out.println("Bd luu user ");
//        try {
//            byte[] avatarBytes = file.getBytes();
//            user.setAvatar(avatarBytes);
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Loi luu anh");
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        userService.saveUser(user);
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
//    }

}
