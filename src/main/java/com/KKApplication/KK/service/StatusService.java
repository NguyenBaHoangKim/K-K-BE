package com.KKApplication.KK.service;

import com.KKApplication.KK.dto.StatusReq;
import com.KKApplication.KK.dto.StatusResp;
import com.KKApplication.KK.entity.Status;

import java.sql.SQLException;
import java.util.List;

public interface StatusService {
    public Status save(StatusReq status);
    public Status findStatusById(Long id);
    public List<Status> viewAllStatus();

    public List<StatusResp> viewAllStatusResp() throws SQLException;
    public List<StatusResp> viewAllStatusRespByUserId(Long userId) throws SQLException;
    public void deleteStatusById(Long id);
}
