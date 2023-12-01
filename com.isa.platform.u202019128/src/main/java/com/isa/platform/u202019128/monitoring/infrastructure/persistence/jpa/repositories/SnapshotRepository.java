package com.isa.platform.u202019128.monitoring.infrastructure.persistence.jpa.repositories;

import com.isa.platform.u202019128.monitoring.domain.model.aggregates.Snapshot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SnapshotRepository extends JpaRepository<Snapshot, Long> {

    List<Snapshot> findAllByProductId(Long productId);
}
