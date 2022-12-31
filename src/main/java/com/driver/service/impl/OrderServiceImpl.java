package com.driver.service.impl;

import com.driver.io.converter.OrderConverter;
import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto order) {
        OrderEntity orderEntity = OrderConverter.convertDtoToEntity(order);
        orderEntity = orderRepository.save(orderEntity);
        return OrderConverter.convertEntityToDto(orderEntity);
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        return OrderConverter.convertEntityToDto(orderEntity);
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        long id = orderRepository.findByOrderId(orderId).getId();
        OrderEntity orderEntity = OrderEntity.builder()
                .id(id)
                .orderId(orderId)
                .userId(order.getUserId())
                .cost(order.getCost())
                .items(order.getItems())
                .status(true)
                .build();
        orderRepository.save(orderEntity);
        return OrderConverter.convertEntityToDto(orderEntity);
    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        if(orderEntity == null){
            throw new Exception("Order not found!!!");
        }
        orderRepository.delete(orderEntity);
    }

    @Override
    public List<OrderDto> getOrders() {
        List<OrderEntity> orderEntityList = (List<OrderEntity>) orderRepository.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();

        for(OrderEntity orderEntity: orderEntityList){
            OrderDto orderDto = OrderConverter.convertEntityToDto(orderEntity);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }
}