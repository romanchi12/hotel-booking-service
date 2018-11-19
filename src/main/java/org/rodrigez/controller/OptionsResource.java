package org.rodrigez.controller;

import org.modelmapper.ModelMapper;
import org.rodrigez.model.domain.OptionType;
import org.rodrigez.model.dto.OptionTypeDTO;
import org.rodrigez.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/options")
public class OptionsResource {
    @Autowired
    InventoryService inventoryService;
    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<OptionTypeDTO> getOptions(){
        List<OptionType> entities = inventoryService.getOptionTypes();
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{optionId}",method = RequestMethod.GET)
    public OptionTypeDTO getOption(@PathVariable(value = "optionId") long optionId){
        OptionType optionType = inventoryService.getOptionType(optionId);
        return convertToDTO(optionType);
    }

    private OptionTypeDTO convertToDTO(OptionType optionType){
        return modelMapper.map(optionType, OptionTypeDTO.class);
    }
}