package org.rodrigez.controller;

import org.modelmapper.ModelMapper;
import org.rodrigez.controller.response.ApiError;
import org.rodrigez.controller.response.ApiResponse;
import org.rodrigez.controller.response.Status;
import org.rodrigez.model.domain.Category;
import org.rodrigez.model.dto.CategoryDTO;
import org.rodrigez.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoriesResource {
    @Autowired
    InventoryService inventoryService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping(value="{categoryId}")
    public ApiResponse getCategory(@PathVariable(value = "categoryId") long categoryId){
        try {
            Category category = inventoryService.getCategory(categoryId);
            return new ApiResponse(Status.OK, convertToDTO(category));
        } catch (Exception e){
            return new ApiResponse(Status.ERROR, new ApiError(e.getMessage()));
        }
    }

    @GetMapping
    public ApiResponse getCategories(){
        List<Category> entities = inventoryService.getCategories();
        List<CategoryDTO> dtoList = entities.stream().map(this::convertToDTO).collect(Collectors.toList());
        return new ApiResponse(Status.OK, dtoList);
    }

    private CategoryDTO convertToDTO(Category category){
        return modelMapper.map(category, CategoryDTO.class);
    }
}