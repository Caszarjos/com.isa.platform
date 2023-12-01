package com.isa.platform.u202019128.monitoring.domain.model.commands;

public record CreateSnapshotCommand(
        String snapshotId,
        Double temperature,
        Long productId,
        // Obligatorio par AdvanceMonitoring
        Double energy,
        Integer leakage

) {
}
