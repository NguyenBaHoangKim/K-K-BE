package com.KKApplication.KK.repository;

import com.KKApplication.KK.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepoImpl implements UserRepo{
    private EntityManager entityManager;

    @Autowired
    public UserRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findUserById(Long id) {
        return entityManager.find(User.class,id);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        entityManager.remove(findUserById(id));
    }

    @Override
    public List<User> getAllUser() {
        String query = "SELECT u FROM User u";
        return entityManager.createQuery(query, User.class).getResultList();
    }
}
