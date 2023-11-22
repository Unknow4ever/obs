package com.test.obs.controller;

import com.test.obs.dto.OrdersDto;
import com.test.obs.model.Orders;
import com.test.obs.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@GetMapping(value = "/getAll")
	public Page<Orders> version(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {

		if (ObjectUtils.isEmpty(page)) page = 1;
		if (ObjectUtils.isEmpty(size)) size = 20;
		Page<Orders> result = orderService.getOrderAll(page,size);

		return result;
	}

	@PostMapping(value = "/save",consumes = {MediaType.APPLICATION_JSON_VALUE})
	public Orders saveData(@Validated @RequestBody OrdersDto orders) {
		Orders result = orderService.saveOrder(orders);

		return result;
	}

	@PostMapping(value = "/update",consumes = {MediaType.APPLICATION_JSON_VALUE})
	public Orders updateData(@Validated @RequestBody OrdersDto orders) {
		Orders result = orderService.updateOrder(orders);

		return result;
	}

	@PostMapping(value = "/delete",consumes = {MediaType.APPLICATION_JSON_VALUE})
	public String deleteData(@Validated @RequestBody OrdersDto orders) {
		String result = orderService.deleteOrder(orders);

		return result;
	}
}