package com.isa.platform.u202019128.inventory.interfaces.rest;

import com.isa.platform.u202019128.inventory.domain.model.commands.CreateProductCommand;
import com.isa.platform.u202019128.inventory.domain.model.queries.GetProductByIdQuery;
import com.isa.platform.u202019128.inventory.domain.services.ProductCommandService;
import com.isa.platform.u202019128.inventory.domain.services.ProductQueryService;
import com.isa.platform.u202019128.inventory.interfaces.rest.resources.CreateProductResource;
import com.isa.platform.u202019128.inventory.interfaces.rest.resources.ProductResource;
import com.isa.platform.u202019128.inventory.interfaces.rest.transform.CreateProductCommandFromResourceAssembler;
import com.isa.platform.u202019128.inventory.interfaces.rest.transform.ProductResourceFromEntityAssembler;
import com.isa.platform.u202019128.monitoring.interfaces.rest.resources.CreateSnapshotResource;
import com.isa.platform.u202019128.monitoring.interfaces.rest.resources.SnapshotResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/products", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Products", description = "Products Management Endpoints")
public class ProductsController {
    private final ProductQueryService productQueryService;
    private final ProductCommandService productCommandService;

    public ProductsController(ProductQueryService productQueryService, ProductCommandService productCommandService) {
        this.productQueryService = productQueryService;
        this.productCommandService = productCommandService;
    }

    /**
     * Creates a new Product.
     *
     * @param createProductResource the resource containing the data for the product to be created
     * @return the created product resource
     * @see CreateProductResource
     * @see ProductResource
     */
    @PostMapping
    public ResponseEntity<ProductResource> createProduct(@RequestBody CreateProductResource createProductResource){
        var createProductCommand = CreateProductCommandFromResourceAssembler.toCommandFromResource(createProductResource);
        var productId = productCommandService.handle(createProductCommand);
        if (productId == 0L) {
            return ResponseEntity.badRequest().build();
        }

        var getProductByIdQuery = new GetProductByIdQuery(productId);
        var product = productQueryService.handle(getProductByIdQuery);

        if(product.isEmpty()) return ResponseEntity.badRequest().build();
        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return new ResponseEntity<>(productResource, HttpStatus.CREATED);
    }

}
