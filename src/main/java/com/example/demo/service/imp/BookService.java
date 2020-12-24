package com.example.demo.service.imp;

import com.example.demo.model.entity.BookEntity;
import com.example.demo.repository.IBookRepository;
import com.example.demo.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    private IBookRepository iBookRepository;

    @Override
    public void addBook(BookEntity bookEntity) {
        iBookRepository.save(bookEntity);
    }

    @Override
    public List<BookEntity> findBooksByName(String name) {
        return iBookRepository.findBookByName(name);
    }
}
