package com.test.obs.repository;

import com.test.obs.model.Items;
import com.test.obs.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
    Page<Orders> findAll(Pageable pageable);
}
