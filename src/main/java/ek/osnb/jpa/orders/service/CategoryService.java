package ek.osnb.jpa.orders.service;

import ek.osnb.jpa.orders.model.Category;
import ek.osnb.jpa.orders.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findByID(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()){
            throw new RuntimeException("Category not found with id: " + id);
        }
        return category.get();
    }

    public Category CreateCategory(Category category){
        category.setId(null);
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category category){
        Category existingCategory = findByID(id);
        if (category.getName() != null){
            existingCategory.setName(category.getName());
        }

        return categoryRepository.save(existingCategory);
    }

    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }
}
