package com.test.obs.service;

import com.test.obs.exceptionHandler.CustomErrorException;
import com.test.obs.model.Items;
import com.test.obs.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public Page<Items> getItemAll(Integer page, Integer size) throws Exception {
        Page<Items> result;
        Pageable paging = PageRequest.of(page, size);
        try {
            result = itemRepository.findAll(paging);
//            Integer a = Integer.valueOf("111111111111111");
        }
        catch (Exception e){
            CustomErrorException customErrorException = new CustomErrorException();
            customErrorException.setResponseMessage("Error occured during fetching item data");
            throw customErrorException;
        }

        return result;
    }

    public Items getItem(Integer data) throws Exception {
        Items result;
        try {
            Optional<Items> queryData = itemRepository.findById(data);
            if (ObjectUtils.isEmpty(queryData)) throw new EntityNotFoundException();
            result = queryData.get();
        }
        catch (EntityNotFoundException e){
            CustomErrorException customErrorException = new CustomErrorException();
            customErrorException.setResponseMessage("data with id " + data + " not found");
            throw customErrorException;
        }
        catch (Exception e){
            CustomErrorException customErrorException = new CustomErrorException();
            customErrorException.setResponseMessage("Error occured during fetching item data");
            throw customErrorException;
        }

        return result;
    }

    public Items saveItem(Items data){
        Items result;

        try {
            result = itemRepository.save(data);
        }
        catch (DataIntegrityViolationException e){
            CustomErrorException customErrorException = new CustomErrorException();
            customErrorException.setResponseMessage("name or price must be filled");
            throw customErrorException;
        }
        catch (Exception e){
            CustomErrorException customErrorException = new CustomErrorException();
            customErrorException.setResponseMessage("Error occured during saving item data");
            throw customErrorException;
        }

        return result;
    }

    public Items updateItem(Items data){
        Items result;

        try {
            Items items = getItem(data.getId());

            result = itemRepository.save(items);
        }
        catch (CustomErrorException e){
            CustomErrorException customErrorException = new CustomErrorException();
            customErrorException.setResponseMessage("Error/data not found during get item data");
            throw customErrorException;
        }
        catch (DataIntegrityViolationException e){
            CustomErrorException customErrorException = new CustomErrorException();
            customErrorException.setResponseMessage("name or price must be filled");
            throw customErrorException;
        }
        catch (Exception e){
            CustomErrorException customErrorException = new CustomErrorException();
            customErrorException.setResponseMessage("Error occured during updating item data");
            throw customErrorException;
        }

        return result;
    }

    public String deleteItem(Items data){
        try {
            itemRepository.delete(data);
        }
        catch (Exception e){
            CustomErrorException customErrorException = new CustomErrorException();
            customErrorException.setResponseMessage("Error occured during deleting item data");
            throw customErrorException;
        }

        return "Deleting item with id " + data.getId() + " successful";
    }

}
