package com.isa.platform.u202019128.inventory.interfaces.acl;

import com.isa.platform.u202019128.inventory.domain.model.aggregates.Product;
import com.isa.platform.u202019128.inventory.domain.model.queries.GetProductByIdQuery;
import com.isa.platform.u202019128.inventory.domain.services.ProductQueryService;
import org.springframework.stereotype.Service;

@Service
public class ProductsContextFacade {

    private final ProductQueryService productQueryService;

    public ProductsContextFacade(ProductQueryService productQueryService) {
        this.productQueryService = productQueryService;
    }

    public Product getProductById(Long id){
        var getProductByIdQuery = new GetProductByIdQuery(id);
        var product = productQueryService.handle(getProductByIdQuery);
        if (product.isEmpty()) return null;
        return product.get();
    }
}
