package org.adaschool.api.mapper;

import org.adaschool.api.repository.product.Product;
import org.adaschool.api.repository.product.ProductDto;

public class ProductMapper {
    public static ProductDto mapToProductoDto(Product product){
        return new ProductDto(
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getTags(),
                product.getPrice(),
                product.getImageUrl()
        );
    }

    public static Product mapToProduct (ProductDto productDto){
        return new Product(productDto);
    }
}
