package com.test.obs.repository;

import com.test.obs.model.Items;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Items, Integer> {
    Page<Items> findAll(Pageable pageable);
}
