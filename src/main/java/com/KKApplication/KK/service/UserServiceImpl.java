package com.KKApplication.KK.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.KKApplication.KK.entity.User;
import com.KKApplication.KK.entity.UserResp;
import com.KKApplication.KK.repository.UserRepoImpl;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserRepoImpl userRepoImpl;

    public UserServiceImpl(UserRepoImpl userRepo) {
        this.userRepoImpl = userRepo;
    }

    @Override
    public User getUserById(Long id) {
        return userRepoImpl.findUserById(id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepoImpl.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepoImpl.getAllUser();
    }

    @Override
    public List<UserResp> getUserStr() {
        List<User> userList = getAllUser();
        List<UserResp> userRespList = new ArrayList<>();
        for (User user1: userList){
            userRespList.add(new UserResp(user1));
        }
        return userRespList;
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepoImpl.deleteUserById(id);
    }

//    @Override
//    public void saveUserWithAva(User user, MultipartFile file) throws IOException {
//        user.setAvatar(file.getBytes());
//        userRepoImpl.save(user);
//    }


}
