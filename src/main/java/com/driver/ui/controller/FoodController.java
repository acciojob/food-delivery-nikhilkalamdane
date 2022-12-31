package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.io.converter.FoodConverter;
import com.driver.io.entity.FoodEntity;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.FoodService;
import com.driver.service.impl.FoodServiceImpl;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foods")
public class FoodController {

	@Autowired
	FoodServiceImpl foodService;

	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{
		FoodDto foodDto = foodService.getFoodById(id);
		return FoodConverter.convertDtoToResponse(foodDto);
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {
		FoodDto foodDto = FoodConverter.convertRequestToDto(foodDetails);
		foodDto = foodService.createFood(foodDto);
		return FoodConverter.convertDtoToResponse(foodDto);
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{
		FoodDto foodDto = FoodConverter.convertRequestToDto(foodDetails);
		foodDto = foodService.updateFoodDetails(id,foodDto);
		return FoodConverter.convertDtoToResponse(foodDto);
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{
		foodService.deleteFoodItem(id);
		OperationStatusModel model = OperationStatusModel.builder()
				.operationName(RequestOperationName.DELETE.toString())
				.operationResult(RequestOperationStatus.SUCCESS.toString())
				.build();
		return model;
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {
		List<FoodDto> foodDtoList = foodService.getFoods();
		List<FoodDetailsResponse> responseList = new ArrayList<>();

		for(FoodDto foodDto: foodDtoList){
			FoodDetailsResponse response = FoodConverter.convertDtoToResponse(foodDto);
			responseList.add(response);
		}
		return responseList;

	}
}
