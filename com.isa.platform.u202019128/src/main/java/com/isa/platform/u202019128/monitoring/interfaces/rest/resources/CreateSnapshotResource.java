package com.isa.platform.u202019128.monitoring.interfaces.rest.resources;

public record CreateSnapshotResource(
        String snapshotId,
        Double temperature,
        Long productId,
        // Obligatorio par AdvanceMonitoring
        Double energy,
        Integer leakage
) {
}
