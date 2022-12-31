package com.driver.service;

import java.util.List;

import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.shared.dto.FoodDto;

/**
 * Handle exception cases for all methods which throw Exception
 */
public interface FoodService {

	FoodDto createFood(FoodDetailsRequestModel food);
	FoodDto getFoodById(String foodId) throws Exception;
	FoodDto updateFoodDetails(String foodId, FoodDetailsRequestModel foodDetails) throws Exception;
	void deleteFoodItem(String id) throws Exception;
	List<FoodDto> getFoods();
}
