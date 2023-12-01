package com.isa.platform.u202019128.monitoring.application.internal.queryservices;

import com.isa.platform.u202019128.monitoring.domain.model.aggregates.Snapshot;
import com.isa.platform.u202019128.monitoring.domain.model.queries.GetAllSnapshotsQuery;
import com.isa.platform.u202019128.monitoring.domain.model.queries.GetProductSnapshotsQuery;
import com.isa.platform.u202019128.monitoring.domain.model.queries.GetSnapshotByIdQuery;
import com.isa.platform.u202019128.monitoring.domain.services.SnapshotQueryService;
import com.isa.platform.u202019128.monitoring.infrastructure.persistence.jpa.repositories.SnapshotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SnapshotQueryServiceImpl implements SnapshotQueryService {

    private final SnapshotRepository snapshotRepository;

    public SnapshotQueryServiceImpl(SnapshotRepository snapshotRepository) {
        this.snapshotRepository = snapshotRepository;
    }


    @Override
    public Optional<Snapshot> handle(GetSnapshotByIdQuery query) {
        return snapshotRepository.findById(query.snapshotId());
    }

    @Override
    public List<Snapshot> handle(GetAllSnapshotsQuery query) {
        return snapshotRepository.findAll();
    }

    @Override
    public List<Snapshot> handle(GetProductSnapshotsQuery query) {
        return snapshotRepository.findAllByProductId(query.productId());
    }
}
