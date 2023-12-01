package com.isa.platform.u202019128.monitoring.interfaces.rest;

import com.isa.platform.u202019128.monitoring.domain.model.queries.GetProductSnapshotsQuery;
import com.isa.platform.u202019128.monitoring.domain.services.SnapshotQueryService;
import com.isa.platform.u202019128.monitoring.interfaces.rest.resources.SnapshotResource;
import com.isa.platform.u202019128.monitoring.interfaces.rest.transform.SnapshotResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/products/{productId}/snapshots", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Snapshots")
public class ProductSnapshotsController {

    private final SnapshotQueryService snapshotQueryService;

    public ProductSnapshotsController(SnapshotQueryService snapshotQueryService) {
        this.snapshotQueryService = snapshotQueryService;
    }

    /**
     * {@code GET /api/v1/products/{productId}/snapshots} : Get all the snapshots for a product.
     *
     * @param productId the product id.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enrollment resources in body.
     * @see SnapshotResource
     */
    @GetMapping
    public ResponseEntity<List<SnapshotResource>> getProductSnapshots(@PathVariable Long productId){
        var getProductSnapshotsQuery = new GetProductSnapshotsQuery(productId);
        var snapshots = snapshotQueryService.handle(getProductSnapshotsQuery);
        var snapshotResource = snapshots.stream().map(SnapshotResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(snapshotResource);
    }
}
