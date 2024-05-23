package com.KKApplication.KK.service;

import com.KKApplication.KK.dto.StatusReq;
import com.KKApplication.KK.dto.StatusResp;
import com.KKApplication.KK.entity.Status;
import com.KKApplication.KK.entity.User;
import com.KKApplication.KK.repository.StatusRepo;
import com.KKApplication.KK.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatusServiceImpl implements StatusService{
    private StatusRepo statusRepo;
    private UserRepo userRepo;

    public StatusServiceImpl(StatusRepo statusRepo, UserRepo userRepo) {
        this.statusRepo = statusRepo;
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public Status save(StatusReq status) {
        User user = userRepo.findUserById(status.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("User with id " + status.getUserId() + " not found");
        }
        Status newStatus = new Status();
        newStatus.setUser(user);
        newStatus.setStatus(status.getStatus());
        newStatus.setImg(status.getImg());
        return statusRepo.save(newStatus);
    }

    @Override
    public Status findStatusById(Long id) {
        return statusRepo.getById(id);
    }

    @Override
    public List<Status> viewAllStatus() {
        return (List<Status>) statusRepo.findAll();
    }

    @Override
    public List<StatusResp> viewAllStatusResp() throws SQLException {
        List<StatusResp> list = new ArrayList<>();
        List<Status> statusList = viewAllStatus();
        for (Status status : statusList) {
            User user = status.getUser();
            if (user == null) {
                throw new IllegalArgumentException("User with id " + status.getUser().getId() + " not found");
            }
            list.add(new StatusResp(status));
        }
        return list;
    }

    @Override
    public List<StatusResp> viewAllStatusRespByUserId(Long userId) throws SQLException {
        List<Status> statusList = statusRepo.findByUserId(userId);
        List<StatusResp> list = new ArrayList<>();
        for (Status status : statusList) {
            User user = status.getUser();
            list.add(new StatusResp(status));
        }
        return list;
    }

    @Override
    @Transactional
    public void deleteStatusById(Long id) {
        statusRepo.deleteById(id);
    }
}
