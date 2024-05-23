package com.KKApplication.KK.controller;

import com.KKApplication.KK.dto.StatusReq;
import com.KKApplication.KK.dto.StatusResp;
import com.KKApplication.KK.entity.Status;
import com.KKApplication.KK.entity.User;
import com.KKApplication.KK.service.StatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {
    private StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/test")
    public String test(){
        return "test dc rui ne";
    }

    @GetMapping("/{id}")
    public Status getStatusById(@PathVariable Long id){
        return statusService.findStatusById(id);
    }

    @GetMapping
    public ResponseEntity<List<StatusResp>> getAllStatus() throws SQLException {
        List<StatusResp> list = statusService.viewAllStatusResp();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<StatusResp>> getStatusByUserId(@PathVariable("userId") Long userId) throws SQLException {
        List<StatusResp> list = statusService.viewAllStatusRespByUserId(userId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
//    @GetMapping
//    public ResponseEntity<List<Status>> getAllStatus(){
//        List<Status> list = statusService.viewAllStatus();
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }

    @PostMapping("/creat")
    public ResponseEntity<StatusResp> createStatus(
            @RequestParam("userId") Long userId,
            @RequestParam("status") String status,
            @RequestPart("img") MultipartFile img)
            throws IOException, SQLException {
        byte[] img_status = img.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(img_status);
        Status newStatus = statusService.save(new  StatusReq(userId,status,blob));
        return new ResponseEntity<>(new StatusResp(newStatus), HttpStatus.CREATED);
    }
}
