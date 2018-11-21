package org.rodrigez.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = CategoriesResource.class, secure = false)
public class CategoriesResourceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private InventoryService inventoryService;

    private List<Category> categoryList;

    @Before
    public void init(){
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

        ObjectMapper objectMapper = new ObjectMapper();
        List<CategoryDTO> categoryDTOList = categoryList.stream().map(CategoryDTO::new).collect(Collectors.toList());

        String expected = "{\"status\":\"OK\",\"data\":" + objectMapper.writeValueAsString(categoryDTOList) + "}";

        when(inventoryService.getCategories()).thenReturn(categoryList);

        this.mvc.perform(
                get("/categories").accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                status().is(200)
        ).andExpect(
                content().string(expected)
        );
    }

    @Test
    public void testGetCategory_OK() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        Category categoryIn_0 = categoryList.get(0);

        when(inventoryService.getCategory(0)).thenReturn(categoryIn_0);

        CategoryDTO categoryDTO = new CategoryDTO(categoryIn_0);

        String expected = "{\"status\":\"OK\",\"data\":" + objectMapper.writeValueAsString(categoryDTO) + "}";

        this.mvc.perform(
                get("/categories/0").accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                content().string(expected)
        );


    }

    @Test
    public void testGetCategory_Error() throws Exception{
        when(inventoryService.getCategory(2)).thenThrow(new NotFoundException("Invalid categoryId " + 2));

        String expected = "{\"status\":\"ERROR\",\"error\":{\"description\":\"Invalid categoryId 2\"}}";

        this.mvc.perform(
                get("/categories/2").accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                content().string(expected)
        );
    }
}