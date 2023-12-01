package com.isa.platform.u202019128.inventory.interfaces.rest.transform;

import com.isa.platform.u202019128.inventory.domain.model.aggregates.Product;
import com.isa.platform.u202019128.inventory.domain.model.commands.CreateProductCommand;
import com.isa.platform.u202019128.inventory.interfaces.rest.resources.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {

    public static CreateProductCommand toCommandFromResource(CreateProductResource resource){
        return new CreateProductCommand(
                resource.brand(),
                resource.model(),
                Product.toRoleFromName(resource.monitoringLevel())
        );
    }
}
