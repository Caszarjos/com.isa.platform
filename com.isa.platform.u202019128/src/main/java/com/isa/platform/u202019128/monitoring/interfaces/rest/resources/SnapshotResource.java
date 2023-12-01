package com.isa.platform.u202019128.monitoring.interfaces.rest.resources;

public record SnapshotResource(
        Long id,
        String snapshotId,
        Double temperature,
        Double energy,
        Integer leakage
) {
}
