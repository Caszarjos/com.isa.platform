package com.isa.platform.u202019128.monitoring.interfaces.rest;


import com.isa.platform.u202019128.monitoring.domain.model.queries.GetAllSnapshotsQuery;
import com.isa.platform.u202019128.monitoring.domain.model.queries.GetSnapshotByIdQuery;
import com.isa.platform.u202019128.monitoring.domain.services.SnapshotCommandService;
import com.isa.platform.u202019128.monitoring.domain.services.SnapshotQueryService;
import com.isa.platform.u202019128.monitoring.interfaces.rest.resources.CreateSnapshotResource;
import com.isa.platform.u202019128.monitoring.interfaces.rest.resources.SnapshotResource;
import com.isa.platform.u202019128.monitoring.interfaces.rest.transform.CreateSnapshotCommandFromResourceAssembler;
import com.isa.platform.u202019128.monitoring.interfaces.rest.transform.SnapshotResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/snapshots", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Snapshots", description = "Snapshots Management Endpoints")
public class SnapshotsController {

    private final SnapshotCommandService snapshotCommandService;
    private final SnapshotQueryService snapshotQueryService;

    public SnapshotsController(SnapshotCommandService snapshotCommandService, SnapshotQueryService snapshotQueryService) {
        this.snapshotCommandService = snapshotCommandService;
        this.snapshotQueryService = snapshotQueryService;
    }

    /**
     * Creates a new snapshot.
     *
     * @param createSnapshotResource the resource containing the data for the course to be created
     * @return the created snapshot resource
     * @see CreateSnapshotResource
     * @see SnapshotResource
     */
    @PostMapping
    public ResponseEntity<SnapshotResource> createSnapshot(@RequestBody CreateSnapshotResource createSnapshotResource){
        var createSnapshotCommand = CreateSnapshotCommandFromResourceAssembler.toCommandFromResource(createSnapshotResource);
        var snapshotId = snapshotCommandService.handle(createSnapshotCommand);
        if (snapshotId == 0L) return ResponseEntity.badRequest().build();

        var getSnapshotByIdQuery = new GetSnapshotByIdQuery(snapshotId);
        var snapshot = snapshotQueryService.handle(getSnapshotByIdQuery);
        if(snapshot.isEmpty()) return ResponseEntity.badRequest().build();

        var snapshotResource = SnapshotResourceFromEntityAssembler.toResourceFromEntity(snapshot.get());
        return new ResponseEntity<>(snapshotResource, HttpStatus.CREATED);
    }

    /**
     * Gets all the Snapshots.
     *
     * @return the list of all the snapshot resources
     * @see SnapshotResource
     */
    @GetMapping
    public ResponseEntity<List<SnapshotResource>> getAllSnapshots() {
        var getAllSnapshotsQuery = new GetAllSnapshotsQuery();
        var snapshots = snapshotQueryService.handle(getAllSnapshotsQuery);
        var snapshotResources = snapshots.stream().map(SnapshotResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(snapshotResources);
    }
}
