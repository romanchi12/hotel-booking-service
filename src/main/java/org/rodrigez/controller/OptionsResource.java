package org.rodrigez.controller;

import org.modelmapper.ModelMapper;
import org.rodrigez.controller.response.ApiError;
import org.rodrigez.controller.response.ApiResponse;
import org.rodrigez.controller.response.Status;
import org.rodrigez.model.domain.OptionType;
import org.rodrigez.model.dto.OptionTypeDTO;
import org.rodrigez.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/options")
public class OptionsResource {
    @Autowired
    InventoryService inventoryService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ApiResponse getOptions(){
        List<OptionType> entities = inventoryService.getOptionTypes();
        List<OptionTypeDTO> dtoList = entities.stream().map(this::convertToDTO).collect(Collectors.toList());
        return new ApiResponse(Status.OK, dtoList);
    }

    @GetMapping(value = "/{optionId}")
    public ApiResponse getOption(@PathVariable(value = "optionId") long optionId){
        try {
            OptionType optionType = inventoryService.getOptionType(optionId);
            return new ApiResponse(Status.OK, convertToDTO(optionType));
        } catch (Exception e){
            return new ApiResponse(Status.ERROR, new ApiError(e.getMessage()));
        }
    }

    private OptionTypeDTO convertToDTO(OptionType optionType){
        return modelMapper.map(optionType, OptionTypeDTO.class);
    }
}