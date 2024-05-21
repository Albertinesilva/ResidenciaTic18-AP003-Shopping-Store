package br.com.techie.shoppingstore.AP003.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techie.shoppingstore.AP003.dto.form.ProductFORM;
import br.com.techie.shoppingstore.AP003.dto.form.ProductUpdateFORM;
import br.com.techie.shoppingstore.AP003.dto.view.ProductVIEW;
import br.com.techie.shoppingstore.AP003.infra.exception.ErrorMessage;
import br.com.techie.shoppingstore.AP003.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Create a new product", description = "Create a new product with the provided details.", responses = {
            @ApiResponse(responseCode = "201", description = "Product created successfully.", content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = ProductVIEW.class))),
            @ApiResponse(responseCode = "422", description = "Invalid input data.", content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = ErrorMessage.class))),
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductVIEW> create(@RequestBody ProductFORM productForm) {
        ProductVIEW newProduct = productService.insert(productForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @Operation(summary = "List all products", description = "Retrieve a paginated list of all products.", responses = {
            @ApiResponse(responseCode = "200", description = "Product list successfully retrieved.", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductVIEW.class)))),
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    public ResponseEntity<Page<ProductVIEW>> getAll(Pageable pageable) {
        Page<ProductVIEW> products = productService.findAllPaged(pageable);
        return ResponseEntity.ok().body(products);
    }

    @Operation(summary = "Retrieve product by ID", description = "Retrieves the details of a specific product by its ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Product successfully recovered.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductVIEW.class))),
            @ApiResponse(responseCode = "404", description = "Product not found.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    public ResponseEntity<ProductVIEW> getById(@PathVariable Long id) {
        ProductVIEW product = productService.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @Operation(summary = "Update product", description = "Updates the details of an existing product.", responses = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductVIEW.class))),
            @ApiResponse(responseCode = "404", description = "Product not found.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "422", description = "Invalid or incorrectly formatted input data.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductVIEW> update(@PathVariable Long id, @RequestBody ProductUpdateFORM productUpdateForm) {
        ProductVIEW product = productService.update(productUpdateForm);
        return ResponseEntity.ok().body(product);
    }

    @Operation(summary = "Delete product", description = "Delete an existing product by its ID.", responses = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Product not found.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
