package org.rodrigez.controller;

import org.rodrigez.model.domain.OptionType;
import org.rodrigez.model.dto.OptionTypeDTO;
import org.rodrigez.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/options")
public class OptionsResource {
    @Autowired
    InventoryService inventoryService;

    @GetMapping
    public ResponseEntity getOptions(){
        List<OptionType> entities = inventoryService.getOptionTypes();
        List<OptionTypeDTO> dtoList = entities.stream().map(OptionTypeDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping(value = "/{optionId}")
    public ResponseEntity getOption(@PathVariable(value = "optionId") long optionId){
        OptionType optionType = inventoryService.getOptionType(optionId);
        OptionTypeDTO dto = new OptionTypeDTO(optionType);
        return ResponseEntity.ok(dto);
    }
}