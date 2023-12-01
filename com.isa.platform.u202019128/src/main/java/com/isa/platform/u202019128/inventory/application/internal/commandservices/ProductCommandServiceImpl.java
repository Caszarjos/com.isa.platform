package com.isa.platform.u202019128.inventory.application.internal.commandservices;

import com.isa.platform.u202019128.inventory.domain.model.aggregates.Product;
import com.isa.platform.u202019128.inventory.domain.model.commands.CreateProductCommand;
import com.isa.platform.u202019128.inventory.domain.services.ProductCommandService;
import com.isa.platform.u202019128.inventory.infrastructure.persistence.spa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductRepository productRepository;

    public ProductCommandServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Long handle(CreateProductCommand command) {

        var product = new Product(command.brand(), command.model(), command.monitoringLevel());

        // Validate Serial Number
        if (productRepository.existsBySerialNumber(product.getSerialNumber()))
            throw new IllegalArgumentException("Serial number already exists");

        productRepository.save(product);
        return product.getId();
    }
}
