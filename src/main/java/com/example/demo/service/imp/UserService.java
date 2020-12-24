package com.example.demo.service.imp;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.IUserService;
import com.example.demo.upload.excel.ExcelToUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public Optional<UserEntity> findById(Integer id) {
        return iUserRepository.findById(id);
    }

    @Override
    public void addListUser(MultipartFile file) {
        try {
            List<UserEntity> userEntityList = ExcelToUser.parseExcelFile(file.getInputStream());
            iUserRepository.saveAll(userEntityList);
        } catch (IOException e) {
            throw new RuntimeException("Tải lên thất bại: " + e.getMessage());
        }
    }

    @Override
    public void addUser(UserEntity userEntity) {
        iUserRepository.save(userEntity);
    }

    @Override
    public void updateUser(UserEntity userEntity, Integer id) {
        Optional<UserEntity> updateUser = iUserRepository.findById(id);
        if (updateUser.isPresent()) {
            updateUser.get().setAddress(userEntity.getAddress());
            updateUser.get().setAge(userEntity.getAge());
            updateUser.get().setName(userEntity.getName());
            iUserRepository.save(updateUser.get());
        }
    }

    @Override
    public void deleteUserById(Integer id) {
        iUserRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return iUserRepository.existsById(id);
    }

}
