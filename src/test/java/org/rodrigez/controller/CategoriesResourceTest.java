package org.rodrigez.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.rodrigez.model.domain.Category;
import org.rodrigez.model.dto.CategoryDTO;
import org.rodrigez.service.InventoryService;
import org.rodrigez.service.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
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

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetCategories() throws Exception {

        List<Category> categoryList = Collections.singletonList(Mockito.mock(Category.class));
        List<CategoryDTO> categoryDTOList = categoryList.stream().map(CategoryDTO::new).collect(Collectors.toList());

        when(inventoryService.getCategories()).thenReturn(categoryList);

        ResponseEntity response = ResponseEntity.ok(categoryDTOList);
        String expected = mapper.writeValueAsString(response.getBody());

        this.mvc.perform(
                get("/categories").accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                content().string(expected)
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    public void testGetCategory_OK() throws Exception {

        Category category = Mockito.mock(Category.class);
        CategoryDTO categoryDTO = new CategoryDTO(category);

        when(inventoryService.getCategory(0)).thenReturn(category);

        ResponseEntity response = ResponseEntity.ok(categoryDTO);
        String expected = mapper.writeValueAsString(response.getBody());

        this.mvc.perform(
                get("/categories/0").accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                content().string(expected)
        ).andExpect(
                status().isOk()
        );

    }

    @Test
    public void testGetCategory_Error() throws Exception{

        Exception exception = new NotFoundException("Invalid categoryId " + 2);

        when(inventoryService.getCategory(2)).thenThrow(exception);

        this.mvc.perform(
                get("/categories/2").accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                status().isNotFound()
        );
    }
}