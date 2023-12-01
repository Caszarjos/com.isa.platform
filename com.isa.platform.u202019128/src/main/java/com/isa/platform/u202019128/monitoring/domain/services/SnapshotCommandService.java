package com.isa.platform.u202019128.monitoring.domain.services;

import com.isa.platform.u202019128.monitoring.domain.model.commands.CreateSnapshotCommand;

public interface SnapshotCommandService {
    Long handle(CreateSnapshotCommand command);
}
