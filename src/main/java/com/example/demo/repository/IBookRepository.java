package com.example.demo.repository;

import com.example.demo.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<BookEntity,Integer>{
    @Query(value = "select * from tbl_book where name like '%%'",nativeQuery = true)
    List<BookEntity> findBookByName(String name);
}
