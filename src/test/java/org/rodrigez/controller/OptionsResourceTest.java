package org.rodrigez.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.rodrigez.model.domain.OptionType;
import org.rodrigez.model.dto.OptionTypeDTO;
import org.rodrigez.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
@WebMvcTest(OptionsResource.class)
public class OptionsResourceTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private InventoryService inventoryService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getOptions() throws Exception {

        List<OptionType> list = Collections.singletonList(Mockito.mock(OptionType.class));
        List<OptionTypeDTO> dtoList = list.stream().map(OptionTypeDTO::new).collect(Collectors.toList());

        when(this.inventoryService.getOptionTypes()).thenReturn(list);

        String expected = mapper.writeValueAsString(dtoList);

        this.mvc.perform(
                get("/options").contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                content().string(expected)
        ).andExpect(
                status().isOk()
        );

    }

    @Test
    public void getOption() throws Exception {

        int id = 1;

        OptionType type = Mockito.mock(OptionType.class);
        OptionTypeDTO dto = new OptionTypeDTO(type);

        when(this.inventoryService.getOptionType(id)).thenReturn(type);

        String expected = mapper.writeValueAsString(dto);

        this.mvc.perform(
                get("/options/" + id).contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                content().string(expected)
        ).andExpect(
                status().isOk()
        );
    }
}