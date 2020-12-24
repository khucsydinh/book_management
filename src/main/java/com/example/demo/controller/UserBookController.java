package com.example.demo.controller;

import com.example.demo.common.Common;
import com.example.demo.common.Constant;
import com.example.demo.model.bo.Message;
import com.example.demo.model.bo.ResponseEntityBO;
import com.example.demo.service.IUserBookService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book_management/user_book")
public class UserBookController {
    Logger logger = Logger.getLogger("UserController");
    Message response;
    long timeStamp;

    @Autowired
    IUserBookService iUserBookService;

    @GetMapping("/GET/{id}")
    public ResponseEntity<?> findUserBookById(@Valid @PathVariable Integer id) {
        timeStamp = Common.getTimeStamp();
        try {
            List<Map<String, Object>> userBookEntities = iUserBookService.findUserBookById(id);
            response = new ResponseEntityBO<>(Constant.SUCCESS_RESPONSE, "Đã tìm thấy người dùng", timeStamp, userBookEntities);
            logger.info(Common.createMessageLog(id, response, timeStamp, "findUserBookById"));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response = new Message(Constant.ERROR_RESPONSE, "Không tìm thấy người dùng", timeStamp);
            logger.error(Common.createMessageLog(id, response, timeStamp, "findUserBookById"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
