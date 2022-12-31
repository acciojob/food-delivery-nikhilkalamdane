package com.driver.io.converter;

import com.driver.io.entity.UserEntity;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.UserResponse;
import com.driver.shared.dto.UserDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConverter {

    public static UserDto convertRequestToDto(UserDetailsRequestModel requestModel){
        return UserDto.builder()
                .firstName(requestModel.getFirstName())
                .lastName(requestModel.getLastName())
                .email(requestModel.getEmail())
                .build();
    }

    public static UserEntity convertDtoToEntity(UserDto userDto){
        return UserEntity.builder()
                .id(userDto.getId())
                .userId(userDto.getUserId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .build();
    }

    public static UserDto convertEntityToDto(UserEntity entity){
        return UserDto.builder()
                .userId(entity.getUserId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .build();
    }

    public static UserResponse convertDtoToResponse(UserDto userDto){
        return UserResponse.builder()
                .userId(userDto.getUserId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .build();
    }
}
