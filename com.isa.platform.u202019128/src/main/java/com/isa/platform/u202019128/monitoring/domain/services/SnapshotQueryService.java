package com.isa.platform.u202019128.monitoring.domain.services;

import com.isa.platform.u202019128.monitoring.domain.model.aggregates.Snapshot;
import com.isa.platform.u202019128.monitoring.domain.model.queries.GetAllSnapshotsQuery;
import com.isa.platform.u202019128.monitoring.domain.model.queries.GetProductSnapshotsQuery;
import com.isa.platform.u202019128.monitoring.domain.model.queries.GetSnapshotByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SnapshotQueryService {
    Optional<Snapshot> handle(GetSnapshotByIdQuery query);
    List<Snapshot> handle(GetAllSnapshotsQuery query);
    List<Snapshot> handle(GetProductSnapshotsQuery query);
}
