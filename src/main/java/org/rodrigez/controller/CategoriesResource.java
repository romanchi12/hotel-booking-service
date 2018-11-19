package org.rodrigez.controller;

import org.modelmapper.ModelMapper;
import org.rodrigez.model.domain.Category;
import org.rodrigez.model.dto.CategoryDTO;
import org.rodrigez.service.InventoryService;
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
    InventoryService inventoryService;
    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(value="{categoryId}", method = RequestMethod.GET)
    public CategoryDTO getCategory(@PathVariable(value = "categoryId") long categoryId){
        Category category = inventoryService.getCategory(categoryId);
        return convertToDTO(category);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CategoryDTO> getCategories(){
        List<Category> entities = inventoryService.getCategories();
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private CategoryDTO convertToDTO(Category category){
        return modelMapper.map(category, CategoryDTO.class);
    }
}