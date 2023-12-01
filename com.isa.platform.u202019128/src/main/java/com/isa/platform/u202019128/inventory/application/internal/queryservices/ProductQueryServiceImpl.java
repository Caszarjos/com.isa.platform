package com.isa.platform.u202019128.inventory.application.internal.queryservices;

import com.isa.platform.u202019128.inventory.domain.model.aggregates.Product;
import com.isa.platform.u202019128.inventory.domain.model.queries.GetAllProductsQuery;
import com.isa.platform.u202019128.inventory.domain.model.queries.GetProductByIdQuery;
import com.isa.platform.u202019128.inventory.domain.model.queries.GetProductBySerialNumberQuery;
import com.isa.platform.u202019128.inventory.domain.services.ProductQueryService;
import com.isa.platform.u202019128.inventory.infrastructure.persistence.spa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;

    public ProductQueryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> handle(GetProductByIdQuery query) {
        return productRepository.findById(query.productId());
    }

    @Override
    public List<Product> handle(GetAllProductsQuery query) {
        return productRepository.findAll();
    }
}
