package com.test.obs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

//@Table(name = "ORDERS")
@Entity
public class Orders {
    @Id
    @Column(nullable = false)
    private String order_no;

    @ManyToOne
    @JoinColumn(name = "id")
    private Items item_id;

    @Column(nullable = false)
    private Integer qty;
}
