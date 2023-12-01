package com.isa.platform.u202019128.inventory.domain.model.commands;

import com.isa.platform.u202019128.inventory.domain.model.valueObjects.EMonitoringLevel;

public record CreateProductCommand(
        String brand,
        String model,
        EMonitoringLevel monitoringLevel
) {}
