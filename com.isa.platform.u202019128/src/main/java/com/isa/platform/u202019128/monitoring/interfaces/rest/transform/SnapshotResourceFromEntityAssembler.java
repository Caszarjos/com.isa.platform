package com.isa.platform.u202019128.monitoring.interfaces.rest.transform;

import com.isa.platform.u202019128.monitoring.domain.model.aggregates.Snapshot;
import com.isa.platform.u202019128.monitoring.interfaces.rest.resources.SnapshotResource;

public class SnapshotResourceFromEntityAssembler {
    public static SnapshotResource toResourceFromEntity(Snapshot snapshot){
        return new SnapshotResource(
                snapshot.getId(),
                snapshot.getSnapshotId(),
                snapshot.getTemperature(),
                snapshot.getEnergy(),
                snapshot.getLeakage()
        );
    }
}
