package com.driver.io.converter;

import com.driver.io.entity.FoodEntity;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.shared.dto.FoodDto;

public class FoodConverter {

    public static FoodEntity convertFoodRequestToEntity(FoodDetailsRequestModel requestModel) {
        return FoodEntity.builder()
                .foodName(requestModel.getFoodName())
                .foodCategory(requestModel.getFoodCategory())
                .foodPrice(requestModel.getFoodPrice())
                .build();
    }

    public static FoodDto convertEntityToDto(FoodEntity foodEntity) {
        return FoodDto.builder()
                .foodId(foodEntity.getFoodId())
                .foodName(foodEntity.getFoodName())
                .foodCategory(foodEntity.getFoodCategory())
                .foodPrice(foodEntity.getFoodPrice())
                .build();
    }

    public static FoodDetailsResponse convertDtoToResponse(FoodDto foodDto) {
        return FoodDetailsResponse.builder()
                .foodId(foodDto.getFoodId())
                .foodName(foodDto.getFoodName())
                .foodCategory(foodDto.getFoodCategory())
                .foodPrice(foodDto.getFoodPrice())
                .build();
    }
}
