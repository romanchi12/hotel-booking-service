package org.rodrigez.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.rodrigez.controller.response.ApiResponse;
import org.rodrigez.controller.response.Status;
import org.rodrigez.model.domain.OptionType;
import org.rodrigez.model.dto.OptionTypeDTO;
import org.rodrigez.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@WebMvcTest(OptionsResource.class)
public class OptionsResourceTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private InventoryService inventoryService;

    private ObjectMapper mapper;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    public void getOptions() throws Exception {
        List<OptionType> list = Collections.singletonList(Mockito.mock(OptionType.class));
        List<OptionTypeDTO> dtoList = list.stream().map(OptionTypeDTO::new).collect(Collectors.toList());

        when(this.inventoryService.getOptionTypes()).thenReturn(list);

        ApiResponse response = new ApiResponse(Status.OK, dtoList);
        String expected = mapper.writeValueAsString(response);

        this.mvc.perform(
                get("/options").contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                MockMvcResultMatchers.content().string(expected));

    }

    @Test
    public void getOption() throws Exception {
        OptionType type = Mockito.mock(OptionType.class);
        OptionTypeDTO dto = new OptionTypeDTO(type);

        int id = 1;
        when(this.inventoryService.getOptionType(1)).thenReturn(type);

        ApiResponse response = new ApiResponse(Status.OK, dto);
        String expected = mapper.writeValueAsString(response);

        this.mvc.perform(
                get("/options/" + id).contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                MockMvcResultMatchers.content().string(expected));
    }
}