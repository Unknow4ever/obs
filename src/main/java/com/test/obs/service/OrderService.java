package com.test.obs.service;

import com.test.obs.dto.OrdersDto;
import com.test.obs.exceptionHandler.CustomErrorException;
import com.test.obs.model.Items;
import com.test.obs.model.Orders;
import com.test.obs.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemService itemService;

    public Page<Orders> getOrderAll(Integer page, Integer size){
        Page<Orders> result;
        Pageable paging = PageRequest.of(page, size);

        try {
            result = orderRepository.findAll(paging);
        }
        catch (Exception e){
            CustomErrorException customErrorException = new CustomErrorException();
            customErrorException.setResponseMessage("Error occured during get order data");
            throw customErrorException;
        }

        return result;
    }

    public Orders saveOrder(OrdersDto data){
        Orders result;

        try {
            Items items = itemService.getItem(data.getItem_id());

            Orders orders = Orders.builder().order_no(data.getOrder_no()).qty(data.getQty()).item_id(items).build();

            result = orderRepository.save(orders);
        }
        catch (CustomErrorException e){
            CustomErrorException customErrorException = new CustomErrorException();
            customErrorException.setResponseMessage("Error/data not found during get item data");
            throw customErrorException;
        }
        catch (DataIntegrityViolationException e){
            CustomErrorException customErrorException = new CustomErrorException();
            customErrorException.setResponseMessage("order_no/item_id/qty must be filled");
            throw customErrorException;
        }
        catch (Exception e){
            CustomErrorException customErrorException = new CustomErrorException();
            customErrorException.setResponseMessage("Error occured during saving order data");
            throw customErrorException;
        }

        return result;
    }

    public Orders updateOrder(OrdersDto data){
        Orders result;

        try {
            Items items = itemService.getItem(data.getItem_id());

            Orders orders = Orders.builder().order_no(data.getOrder_no()).qty(data.getQty()).item_id(items).build();

            result = orderRepository.save(orders);
        }
        catch (CustomErrorException e){
            CustomErrorException customErrorException = new CustomErrorException();
            customErrorException.setResponseMessage("Error/data not found during get item data");
            throw customErrorException;
        }
        catch (DataIntegrityViolationException e){
            CustomErrorException customErrorException = new CustomErrorException();
            customErrorException.setResponseMessage("order_no/item_id/qty must be filled");
            throw customErrorException;
        }
        catch (Exception e){
            CustomErrorException customErrorException = new CustomErrorException();
            customErrorException.setResponseMessage("Error occured during updating order data");
            throw customErrorException;
        }

        return result;
    }

    public String deleteOrder(OrdersDto data){
        try {
            Items items = itemService.getItem(data.getItem_id());

            Orders orders = Orders.builder().order_no(data.getOrder_no()).qty(data.getQty()).item_id(items).build();

            orderRepository.delete(orders);
        }
        catch (CustomErrorException e){
            CustomErrorException customErrorException = new CustomErrorException();
            customErrorException.setResponseMessage("Error/data not found during get item data");
            throw customErrorException;
        }
        catch (Exception e){
            CustomErrorException customErrorException = new CustomErrorException();
            customErrorException.setResponseMessage("Error occured during deleting order data");
            throw customErrorException;
        }

        return "Deleting item with order no " + data.getOrder_no() + " successful";
    }
}
