package com.KKApplication.KK.service;

import com.KKApplication.KK.entity.User;
import com.KKApplication.KK.entity.UserResp;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    public User getUserById(Long id);
    public void saveUser(User user);
    public List<User> getAllUser();
    public List<UserResp> getUserStr();
    public void deleteUserById(Long id);
//    public void saveUserWithAva(User user, MultipartFile file) throws IOException;
}
