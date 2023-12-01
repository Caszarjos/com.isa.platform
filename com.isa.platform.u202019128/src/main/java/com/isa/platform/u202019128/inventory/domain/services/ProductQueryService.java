package com.isa.platform.u202019128.inventory.domain.services;

import com.isa.platform.u202019128.inventory.domain.model.aggregates.Product;
import com.isa.platform.u202019128.inventory.domain.model.queries.GetAllProductsQuery;
import com.isa.platform.u202019128.inventory.domain.model.queries.GetProductByIdQuery;
import com.isa.platform.u202019128.inventory.domain.model.queries.GetProductBySerialNumberQuery;

import java.util.List;
import java.util.Optional;

public interface ProductQueryService {
    Optional<Product> handle(GetProductByIdQuery query);
    List<Product> handle(GetAllProductsQuery query);
}
