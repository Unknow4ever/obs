package com.test.obs.dto;

import com.test.obs.model.Items;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDto {
    private String order_no;
    private Integer item_id;
    private Integer qty;
}
