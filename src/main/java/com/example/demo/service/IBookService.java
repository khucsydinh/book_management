package com.example.demo.service;

import com.example.demo.model.entity.BookEntity;

import java.util.List;


public interface IBookService {

    public void addBook(BookEntity bookEntity);

    public List<BookEntity> findBooksByName(String name);
}
