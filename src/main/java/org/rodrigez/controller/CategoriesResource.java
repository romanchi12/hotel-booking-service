package org.rodrigez.controller;

import org.rodrigez.model.Category;
import org.rodrigez.model.dto.CategoryDTO;
import org.rodrigez.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoriesResource {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value="{categoryId}", method = RequestMethod.GET)
    public CategoryDTO getCategory(@PathVariable(value = "categoryId") long categoryId){
        Category category = categoryService.getCategory(categoryId);
        return new CategoryDTO(category);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CategoryDTO> getCategories(){
        List<Category> entities = categoryService.getCategories();
        return entities.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }
}