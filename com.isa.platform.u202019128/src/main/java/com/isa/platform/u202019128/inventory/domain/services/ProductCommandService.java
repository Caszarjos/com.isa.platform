package com.isa.platform.u202019128.inventory.domain.services;

import com.isa.platform.u202019128.inventory.domain.model.commands.CreateProductCommand;
import org.springframework.stereotype.Service;

public interface ProductCommandService {
    Long handle(CreateProductCommand command);
}
