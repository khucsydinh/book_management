package com.example.demo.controller;

import com.example.demo.common.Common;
import com.example.demo.common.Constant;
import com.example.demo.model.bo.Message;
import com.example.demo.model.bo.ResponseEntityBO;
import com.example.demo.model.entity.BookEntity;
import com.example.demo.service.IBookService;
import com.example.demo.service.imp.BookService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book_management/book")
public class BookController {
    Logger logger = Logger.getLogger("UserController");
    Message response;
    long timeStamp;
    @Autowired
    IBookService iBookService;
    @Autowired
    BookService bookService;

    @GetMapping("/GET/books/{name}")
    public ResponseEntity<?> findBooksByName(@Valid @PathVariable String name) {
        timeStamp = Common.getTimeStamp();
        try {
            List<BookEntity> bookEntities = iBookService.findBooksByName(name);
            response = new ResponseEntityBO<>(Constant.SUCCESS_RESPONSE, "Đã tìm thấy sách", timeStamp, bookEntities);
            logger.info(Common.createMessageLog(name, response, timeStamp, "findBooksByName"));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response = new Message(Constant.ERROR_RESPONSE, "Không tìm thấy tên sách", timeStamp);
            logger.error(Common.createMessageLog(name, response, timeStamp, "findBooksByName"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/POST/books")
    public ResponseEntity<?> addBook(@Valid @RequestBody BookEntity bookEntity) {
        timeStamp = Common.getTimeStamp();
        try {
            response = new Message(Constant.SUCCESS_RESPONSE, "Đã thêm sách mới", timeStamp);
            iBookService.addBook(bookEntity);
            logger.info(Common.createMessageLog(bookEntity, response, timeStamp, "addBook"));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response = new Message(Constant.ERROR_RESPONSE, "Không thể thêm người dùng", timeStamp);
            logger.error(Common.createMessageLog(bookEntity, response, timeStamp, "addBook"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
