package com.isa.platform.u202019128.monitoring.application.internal.commandservices;

import com.isa.platform.u202019128.monitoring.application.internal.outboundservices.acl.ExternalProductService;
import com.isa.platform.u202019128.monitoring.domain.model.aggregates.Snapshot;
import com.isa.platform.u202019128.monitoring.domain.model.commands.CreateSnapshotCommand;
import com.isa.platform.u202019128.monitoring.domain.services.SnapshotCommandService;
import com.isa.platform.u202019128.monitoring.infrastructure.persistence.jpa.repositories.SnapshotRepository;
import org.springframework.stereotype.Service;

@Service
public class SnapshotCommandServiceImpl implements SnapshotCommandService {

    private final SnapshotRepository snapshotRepository;
    private final ExternalProductService externalProductService;

    public SnapshotCommandServiceImpl(SnapshotRepository snapshotRepository, ExternalProductService externalProductService) {
        this.snapshotRepository = snapshotRepository;
        this.externalProductService = externalProductService;
    }

    @Override
    public Long handle(CreateSnapshotCommand command) {
        // Obtenemos product
        var product = externalProductService.getProductById(command.productId());
        if (product.isEmpty()) throw new IllegalArgumentException("Product ");

        // Validamos tipo de monitoring level
        Snapshot snapshot;
        if (product.get().getMonitoringLevelName().equals("ESSENTIAL_MONITORING")) {
            if (command.energy() != null || command.leakage() != null)
                throw new IllegalArgumentException("Snapshot Data not compatible with \n" +
                        "product current Monitoring Level");

            snapshot = new Snapshot(
                    command.snapshotId(),
                    command.temperature(),
                    product.get()
            );
        } else {
            snapshot = new Snapshot(
                    command.snapshotId(),
                    command.temperature(),
                    command.energy(),
                    command.leakage(),
                    product.get()
            );
        }
        snapshotRepository.save(snapshot);
        return snapshot.getId();
    }
}
