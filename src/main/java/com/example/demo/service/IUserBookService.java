package com.example.demo.service;

import java.util.List;
import java.util.Map;

public interface IUserBookService {
    public List<Map<String,Object>> findUserBookById(Integer id);
}
