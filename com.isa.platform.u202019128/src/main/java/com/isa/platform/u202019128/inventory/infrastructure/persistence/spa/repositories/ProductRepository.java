package com.isa.platform.u202019128.inventory.infrastructure.persistence.spa.repositories;

import com.isa.platform.u202019128.inventory.domain.model.aggregates.Product;
import com.isa.platform.u202019128.inventory.domain.model.valueObjects.SerialNumberRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsBySerialNumber(SerialNumberRecord serialNumber);
}
