package org.adaschool.api.controller.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.adaschool.api.exception.ProductNotFoundException;
import org.adaschool.api.mapper.ProductMapper;
import org.adaschool.api.repository.product.Product;
import org.adaschool.api.repository.product.ProductDto;
import org.adaschool.api.service.product.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/products/")
@Tag(name = "product resource")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(@Autowired ProductsService productsService) {
        this.productsService = productsService;
    }
    @Operation(summary = "Post new Product")
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        Product p = ProductMapper.mapToProduct(productDto);
        productsService.save(p);
        URI createdProductUri = URI.create("/v1/products/" + p.getId());
        return ResponseEntity.created(createdProductUri).body(p);
    }

    @Operation(summary = "Get All products")
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {

        return ResponseEntity.ok(productsService.all());
    }

    @Operation(summary = "Get Product by Id")
    @GetMapping("{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable("id") String id) {
        Optional<Product> productOptional = productsService.findById(id);
        if (productOptional.isEmpty()){
            throw new ProductNotFoundException(id);
        }else {
            return ResponseEntity.ok(productOptional);
        }
    }

    @Operation(summary = "Update Product by Id")
    @PutMapping(path = "{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody Product p) {
        if(productsService.findById(id).isEmpty()){
            throw new ProductNotFoundException(id);
        }else{
            Product product = productsService.update(p, id);
            return ResponseEntity.ok(product);
        }
    }

    @Operation(summary = "Delete Product by Id")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
        if(productsService.findById(id).isEmpty()){
            throw new ProductNotFoundException(id);
        }else{
            productsService.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }
}
