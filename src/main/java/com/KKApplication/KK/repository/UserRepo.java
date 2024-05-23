package com.KKApplication.KK.repository;

import com.KKApplication.KK.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo{
    public void save(User user);
    public User findUserById(Long id);
    public void deleteUserById(Long id);
    public List<User> getAllUser();
}
