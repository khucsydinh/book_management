package com.example.demo.service;

import com.example.demo.model.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface IUserService {

    public void addListUser(MultipartFile file);

    public void addUser(UserEntity userEntity);

    public void updateUser(UserEntity userEntity, Integer id);

    public void deleteUserById(Integer id);

    public Optional<UserEntity> findById(Integer id);

    public boolean existsById(Integer id);

}
