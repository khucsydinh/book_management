package com.example.demo.service.imp;

import com.example.demo.repository.IUserBookRepository;
import com.example.demo.service.IUserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserBookService implements IUserBookService {

    @Autowired
    private IUserBookRepository iUserBookRepository;

    @Override
    public List<Map<String,Object>> findUserBookById(Integer id){
        return iUserBookRepository.findUserBookById(id);
    }
}