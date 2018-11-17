package org.rodrigez.service;

import org.rodrigez.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    Category getCategory(long categoryId);
}
