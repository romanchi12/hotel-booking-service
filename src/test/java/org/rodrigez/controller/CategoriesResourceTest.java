package org.rodrigez.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rodrigez.controller.response.ApiError;
import org.rodrigez.controller.response.ApiResponse;
import org.rodrigez.controller.response.Status;
import org.rodrigez.model.domain.Category;
import org.rodrigez.model.dto.CategoryDTO;
import org.rodrigez.service.InventoryService;
import org.rodrigez.service.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@WebMvcTest(value = CategoriesResource.class, secure = false)
public class CategoriesResourceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private InventoryService inventoryService;

    private List<Category> categoryList;

    private ObjectMapper objectMapper;

    @Before
    public void init(){

        objectMapper = new ObjectMapper();

        Category category1 = new Category();
        category1.setId(0);
        category1.setDescription("Standart");

        Category category2 = new Category();
        category2.setId(1);
        category2.setDescription("Lux");

        categoryList = Arrays.asList(category1,category2);
    }

    @Test
    public void testGetCategories() throws Exception {

        when(inventoryService.getCategories()).thenReturn(categoryList);

        List<CategoryDTO> categoryDTOList = categoryList.stream().map(CategoryDTO::new).collect(Collectors.toList());

        ApiResponse response = new ApiResponse(Status.OK, categoryDTOList);

        String expected = objectMapper.writeValueAsString(response);

        this.mvc.perform(
                get("/categories").accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                content().string(expected)
        );
    }

    @Test
    public void testGetCategory_OK() throws Exception {

        Category categoryIn_0 = categoryList.get(0);

        when(inventoryService.getCategory(0)).thenReturn(categoryIn_0);

        ApiResponse response = new ApiResponse(Status.OK, new CategoryDTO(categoryIn_0));

        String expected = objectMapper.writeValueAsString(response);

        this.mvc.perform(
                get("/categories/0").accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                content().string(expected)
        );

    }

    @Test
    public void testGetCategory_Error() throws Exception{

        Exception exception = new NotFoundException("Invalid categoryId " + 2);

        when(inventoryService.getCategory(2)).thenThrow(exception);

        ApiResponse response = new ApiResponse(Status.ERROR, new ApiError(exception.getMessage()));

        String expected = objectMapper.writeValueAsString(response);

        this.mvc.perform(
                get("/categories/2").accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                content().string(expected)
        );
    }
}