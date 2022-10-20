package com.codeafrica.markeplace.service;

import com.codeafrica.markeplace.dto.ProductDto;
import com.codeafrica.markeplace.model.Category;
import com.codeafrica.markeplace.model.Product;
import com.codeafrica.markeplace.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    public Category readCategory(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category>readCategory(Integer categoryId){
      return categoryRepository.findById(categoryId);
    }


    public void updateCategory(Integer categoryID, Category newCategory){
        Category category = categoryRepository.findById(categoryID).get();
        category.setCategoryName(newCategory.getCategoryName());
        category.setDescription(newCategory.getDescription());
        category.setImageUrl(newCategory.getImageUrl());
        categoryRepository.save(category);

    }



}
