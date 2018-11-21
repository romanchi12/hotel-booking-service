package org.rodrigez.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.rodrigez.model.domain.OptionType;
import org.rodrigez.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@WebMvcTest(OptionsResource.class)
public class OptionsResourceTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private InventoryService inventoryService;

    @MockBean
    ModelMapper modelMapper;

    @Test
    public void getOptions(){
        OptionType optionType = new OptionType();
        optionType.setId(1);
        optionType.setDescription("Test");
        List<OptionType> optionTypeList = new ArrayList<>();
        optionTypeList.add(optionType);
        assertNotNull(inventoryService);
    }

    @Test
    public void getOption() {
    }
}