package ek.osnb.jpa.orders.service;

import ek.osnb.jpa.orders.model.Category;
import ek.osnb.jpa.orders.model.Product;
import ek.osnb.jpa.orders.repository.CategoryRepository;
import ek.osnb.jpa.orders.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()){
            throw new RuntimeException("Product not found with id: " + id);
        }
        return product.get();
    }

    public Product createProduct(Product product){
        product.setId(null);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product){
        Product existingProduct = findById(id);

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());

        List<Long> categoryIds = new ArrayList<>();
        for (Category category : product.getCategories()){
            categoryIds.add(category.getId());
        }

        Set<Category> newCategories = new HashSet<>(categoryRepository.findAllById(categoryIds));

        existingProduct.getCategories().clear();
        existingProduct.setCategories(newCategories);

        return existingProduct;
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }
}
