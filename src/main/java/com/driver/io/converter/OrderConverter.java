package com.driver.io.converter;

import com.driver.io.entity.OrderEntity;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.shared.dto.OrderDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderConverter {

    public static OrderDto convertRequestToDto(OrderDetailsRequestModel requestModel){
        return OrderDto.builder()
                .userId(requestModel.getUserId())
                .cost(requestModel.getCost())
                .items(requestModel.getItems())
                .build();
    }

    public static OrderEntity convertDtoToEntity(OrderDto orderDto){
        return OrderEntity.builder()
                .userId(orderDto.getUserId())
                .cost(orderDto.getCost())
                .status(orderDto.isStatus())
                .items(orderDto.getItems())
                .build();
    }

    public static OrderDto convertEntityToDto(OrderEntity orderEntity){
        return OrderDto.builder()
                .orderId(orderEntity.getOrderId())
                .userId(orderEntity.getUserId())
                .cost(orderEntity.getCost())
                .status(orderEntity.isStatus())
                .items(orderEntity.getItems())
                .build();
    }


    public static OrderDetailsResponse convertDtoToResponse(OrderDto orderDto){
        return OrderDetailsResponse.builder()
                .orderId(orderDto.getOrderId())
                .userId(orderDto.getUserId())
                .cost(orderDto.getCost())
                .status(orderDto.isStatus())
                .items(orderDto.getItems())
                .build();
    }
}
