package com.driver.service.impl;

import com.driver.io.converter.UserConverter;
import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public  class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDetailsRequestModel user) throws Exception {
        UserEntity userEntity = UserConverter.convertUserRequestToEntity(user);
        userRepository.save(userEntity);
        return UserConverter.convertUserEntityToDto(userEntity);
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity userEntity = userRepository.findByEmail(email);
        return UserConverter.convertUserEntityToDto(userEntity);
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        return UserConverter.convertUserEntityToDto(userEntity);
    }

    @Override
    public UserDto updateUser(String userId, UserDetailsRequestModel user) throws Exception {
        long id = userRepository.findByUserId(userId).getId();
        UserEntity userEntity = UserEntity.builder()
                .id(id)
                .userId(userId)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();

        userRepository.save(userEntity);
        return UserConverter.convertUserEntityToDto(userEntity);
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity == null){
            throw new Exception("User not found!!!");
        }
        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> userEntityList = (List<UserEntity>) userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for(UserEntity userEntity: userEntityList){
            UserDto userDto = UserConverter.convertUserEntityToDto(userEntity);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
}