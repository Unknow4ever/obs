package com.test.obs.controller;

import com.test.obs.model.Items;
import com.test.obs.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;

	@GetMapping(value = "/getAll")
	public Page<Items> getAll(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) throws Exception {

		if (ObjectUtils.isEmpty(page)) page = 1;
		if (ObjectUtils.isEmpty(size)) size = 20;
		Page<Items> result = itemService.getItemAll(page,size);

		return result;
	}

	@GetMapping(value = "/get/{id}")
	public Items get(@PathVariable("id") Integer id) throws Exception {
		Items result = itemService.getItem(id);

		return result;
	}

	@PostMapping(value = "/save",consumes = {MediaType.APPLICATION_JSON_VALUE})
	public Items saveData(@RequestBody Items items) {
		Items result = itemService.saveItem(items);

		return result;
	}

	@PostMapping(value = "/update",consumes = {MediaType.APPLICATION_JSON_VALUE})
	public Items updateData(@RequestBody Items items) {
		Items result = itemService.updateItem(items);

		return result;
	}

	@PostMapping(value = "/delete",consumes = {MediaType.APPLICATION_JSON_VALUE})
	public String deleteData(@RequestBody Items items) {
		String result = itemService.deleteItem(items);

		return result;
	}
}