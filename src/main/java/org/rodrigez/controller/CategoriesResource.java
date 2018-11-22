package org.rodrigez.controller;

import org.rodrigez.model.domain.Category;
import org.rodrigez.model.dto.CategoryDTO;
import org.rodrigez.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoriesResource {
    @Autowired
    InventoryService inventoryService;

    @GetMapping(value="{categoryId}")
    public ResponseEntity getCategory(@PathVariable(value = "categoryId") long categoryId){
        Category category = inventoryService.getCategory(categoryId);
        CategoryDTO dto = new CategoryDTO(category);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCategories(){
        List<Category> entities = inventoryService.getCategories();
        List<CategoryDTO> dtoList = entities.stream().map(CategoryDTO::new).collect(Collectors.toList());
        return new ResponseEntity(dtoList, HttpStatus.OK);
    }
}