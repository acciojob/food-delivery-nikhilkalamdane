package com.driver.service.impl;

import com.driver.io.converter.FoodConverter;
import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodRepository foodRepository;

    @Override
    public FoodDto createFood(FoodDetailsRequestModel requestModel) {
        FoodEntity foodEntity = FoodConverter.convertRequestToEntity(requestModel);
        foodEntity = foodRepository.save(foodEntity);
        return FoodConverter.convertEntityToDto(foodEntity);
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
        return FoodConverter.convertEntityToDto(foodEntity);
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDetailsRequestModel foodDetails) throws Exception {
        long id = foodRepository.findByFoodId(foodId).getId();
        FoodEntity foodEntity = FoodEntity.builder()
                .id(id)
                .foodId(foodId)
                .foodName(foodDetails.getFoodName())
                .foodCategory(foodDetails.getFoodCategory())
                .foodPrice(foodDetails.getFoodPrice())
                .build();
        foodRepository.save(foodEntity);

        return FoodConverter.convertEntityToDto(foodEntity);
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(id);
        if(foodEntity == null){
            throw new Exception("Food not found!!!");
        }
        foodRepository.delete(foodEntity);
    }

    @Override
    public List<FoodDto> getFoods() {
        List<FoodEntity> foodEntityList = (List<FoodEntity>) foodRepository.findAll();
        List<FoodDto> foodDtoList = new ArrayList<>();

        for(FoodEntity foodEntity: foodEntityList){
            FoodDto foodDto = FoodConverter.convertEntityToDto(foodEntity);
            foodDtoList.add(foodDto);
        }
        return foodDtoList;
    }
}