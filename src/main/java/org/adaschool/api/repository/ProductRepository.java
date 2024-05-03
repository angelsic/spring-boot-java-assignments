package org.adaschool.api.repository;

import org.adaschool.api.repository.product.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private List<Product> list = new ArrayList<Product>();
    public void createProducts(){
        Product p1 = new Product("1", "Whole Milk", "Whole Milk 200ml", "Dairy", 15.488);
        List<String> tags = new ArrayList<String>();
        tags.add("Food");
        tags.add("Dairy");
        p1.setTags(tags);
        p1.setImageUrl("https://www.dairyfoods.com/ext/resources/2024/02/05/Remilk_milk_bottle_2.jpg?1707149614");
        list.add(p1);
        Product p2 = new Product("2", "Milk", "Milk 300ml", "Dairy", 20.500);
        p2.setTags(tags);
        p2.setImageUrl("https://bloximages.newyork1.vip.townnews.com/lancasterfarming.com/content/tncms/assets/v3/editorial/4/15/415b4790-c431-11ee-8e8c-bff4ca708ef7/65c0ede8626e7.image.jpg?resize=1476%2C1107");
        list.add(p2);
    }

    public ProductRepository(){
        createProducts();
    }

    public List<Product> getAllProducts(){
        return list.stream().toList();
    }

    public Optional<Product> getProductById(String id){
        return Optional.ofNullable(list.stream()
                .filter(product -> id.equals(product.getId()))
                .findAny()
                .orElse(null));
    }

    public Product addProduct(Product product){
        list.add(product);
        return product;
    }

    public Product updateProduct(Product product, String id){
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId().equals(id)){
                index = i;
            }
        }
        list.set(index, product);
        return product;
    }

    public void deleteProduct(String id){
        list.removeIf(product -> product.getId().equals(id));
    }
}
