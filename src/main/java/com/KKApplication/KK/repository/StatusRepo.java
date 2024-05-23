package com.KKApplication.KK.repository;

import com.KKApplication.KK.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepo extends JpaRepository<Status, Long> {
    List<Status> findByUserId(Long userId);
}
