package com.example.demo.repository;

import com.example.demo.model.entity.UserBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IUserBookRepository extends JpaRepository<UserBookEntity, Integer> {
    @Query(value = "select * from tbl_user_book\n" +
            "where reading_check = 1;", nativeQuery = true)
    public List<Map<String, Object>> findUserBookById(Integer userId);
}
