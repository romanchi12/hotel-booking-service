package org.rodrigez.service.impl;

import org.rodrigez.model.Category;
import org.rodrigez.repository.CategoryRepository;
import org.rodrigez.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(long categoryId) {
        return categoryRepository.getOne(categoryId);
    }
}
