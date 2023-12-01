package com.isa.platform.u202019128.inventory.interfaces.rest.transform;

import com.isa.platform.u202019128.inventory.domain.model.aggregates.Product;
import com.isa.platform.u202019128.inventory.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {
    public static ProductResource toResourceFromEntity(Product product){
        return new ProductResource(
                product.getId(),
                product.getBrand(),
                product.getModel(),
                product.getMonitoringLevelName(),
                product.getSerialNumber().serialNumber()
        );
    }
}
