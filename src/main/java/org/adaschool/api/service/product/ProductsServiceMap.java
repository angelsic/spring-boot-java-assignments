package org.adaschool.api.service.product;

import org.adaschool.api.repository.ProductRepository;
import org.adaschool.api.repository.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceMap implements ProductsService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.addProduct(product);
    }

    @Override
    public Optional<Product> findById(String id) {
        return productRepository.getProductById(id);
    }

    @Override
    public List<Product> all() {
        return productRepository.getAllProducts();
    }

    @Override
    public void deleteById(String id) {
        productRepository.deleteProduct(id);
    }

    @Override
    public Product update(Product product, String productId) {
        return productRepository.updateProduct(product,productId);
    }
}
