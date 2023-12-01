package com.isa.platform.u202019128.monitoring.application.internal.outboundservices.acl;

import com.isa.platform.u202019128.inventory.domain.model.aggregates.Product;
import com.isa.platform.u202019128.inventory.interfaces.acl.ProductsContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalProductService {
    private final ProductsContextFacade productsContextFacade;

    public ExternalProductService(ProductsContextFacade productsContextFacade) {
        this.productsContextFacade = productsContextFacade;
    }

    public Optional<Product> getProductById(Long id) {
        var product = productsContextFacade.getProductById(id);
        if (product == null) return Optional.empty();
        return Optional.of(product);
    }

}
