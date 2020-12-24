package com.example.demo.controller;

import com.example.demo.common.Common;
import com.example.demo.common.Constant;
import com.example.demo.model.bo.Message;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.service.IUserService;
import com.example.demo.service.imp.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/book_management/user")
public class UserController {
    Logger logger = Logger.getLogger("UserController");
    Message response;
    long timeStamp;
    @Autowired
    private UserService userService;
    @Autowired
    private IUserService iUserService;

    @PostMapping("/POST/users")
    public Message addUser(@Valid @RequestBody UserEntity userEntity) {
        timeStamp = Common.getTimeStamp();
        try {
            response = new Message(Constant.SUCCESS_RESPONSE, "Thêm người dùng thành công", timeStamp);
            iUserService.addUser(userEntity);
            logger.info(Common.createMessageLog(userEntity, response, timeStamp, "addUser"));
        } catch (Exception e) {
            response = new Message(Constant.ERROR_RESPONSE, "Không thể thêm người dùng", timeStamp);
            logger.error(Common.createMessageLog(userEntity, response, timeStamp, "addUser"));
        }
        return response;
    }

    @PostMapping("/POST/list_users")
    public Message addListUser(@Valid @RequestParam("file") MultipartFile file) {
        timeStamp = Common.getTimeStamp();
        try {
            response = new Message(Constant.SUCCESS_RESPONSE, "Thêm danh sách người dùng thành công", timeStamp);
            iUserService.addListUser(file);
            logger.info(Common.createMessageLog(file, response, timeStamp, "addListUser"));
        } catch (Exception e) {
            response = new Message(Constant.ERROR_RESPONSE, "Không thể thêm danh sách người dùng", timeStamp);
            logger.error(Common.createMessageLog(file, response, timeStamp, "addListUser"));
        }
        return response;
    }

    @PutMapping("/PUT/{id}")
    public Message updateUser(@Valid @RequestBody UserEntity userEntity, @Valid @PathVariable Integer id) {
        timeStamp = Common.getTimeStamp();
        try {
            Optional<UserEntity> userEntity1 = iUserService.findById(id);
            response = new Message(Constant.SUCCESS_RESPONSE, "Đã cập nhật thông tin người dùng", timeStamp);
            iUserService.updateUser(userEntity, id);
            logger.info(Common.createMessageLog(userEntity, response, timeStamp, "updateUser"));
        } catch (Exception e) {
            response = new Message(Constant.ERROR_RESPONSE, "không tìm thấy người dùng này", timeStamp);
            logger.error(Common.createMessageLog(userEntity, response, timeStamp, "updateUser"));
        }
        return response;
    }

    @DeleteMapping("/DELETE/{id}")
    public Message deleteUserById(@Valid @PathVariable Integer id) {
        timeStamp = Common.getTimeStamp();
        try {
            if (userService.existsById(id)) {
                userService.deleteUserById(id);
                //tham chiếu đến đối tượng cần trả về
                response = new Message(Constant.SUCCESS_RESPONSE, "Xóa thành công", timeStamp);
                logger.info(Common.createMessageLog(id, response, timeStamp, "deleteUserById"));
            } else {
                //sử dựng hàm khởi tạo để giúp code ngắn gọn hơn
                response = new Message(Constant.ERROR_RESPONSE, "Không tìm thấy người dùng này!", timeStamp);
                logger.error(Common.createMessageLog(null, response, timeStamp, "deleteUserById"));
            }
        } catch (Exception e) {
            response = new Message(Constant.ERROR_RESPONSE, e.getMessage(), timeStamp);
            logger.error(Common.createMessageLog(id, response, timeStamp, "deleteById"));
        }
        return response;
    }
}
