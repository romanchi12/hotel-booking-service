package org.rodrigez.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rodrigez.model.domain.Category;
import org.rodrigez.model.dto.CategoryDTO;
import org.rodrigez.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = CategoriesResource.class, secure = false)
public class CategoriesResourceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private InventoryService inventoryService;

    @Test
    public void testGetCategories() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        assertNotNull(inventoryService);

        Category category1 = new Category();
        category1.setId(1);
        category1.setDescription("Standart");

        Category category2 = new Category();
        category2.setId(2);
        category2.setDescription("Lux");

        List<Category> categoryList = Arrays.asList(category1,category2);

        when(inventoryService.getCategories()).thenReturn(categoryList);

        String expected = "{\"status\":\"OK\",\"data\":" + objectMapper.writeValueAsString(categoryList) + "}";

        this.mvc.perform(
                get("/categories").accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                status().is(200)
        ).andExpect(
                content().string(expected)
        );
    }

    @Test
    public void testGetCategory() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        assertNotNull(inventoryService);

        Category category1 = new Category();
        category1.setId(1);
        category1.setDescription("Standart");

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category1.getId());
        categoryDTO.setDescription(category1.getDescription());

        when(inventoryService.getCategory(1)).thenReturn(category1);

        String expected = "{\"status\":\"OK\",\"data\":" + objectMapper.writeValueAsString(categoryDTO) + "}";

        this.mvc.perform(
                get("/categories/1").accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                content().string(expected)
        );
    }
}