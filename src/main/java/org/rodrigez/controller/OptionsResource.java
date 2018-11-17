package org.rodrigez.controller;

import org.rodrigez.model.Option;
import org.rodrigez.model.dto.OptionDTO;
import org.rodrigez.service.OptionService;
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
    OptionService optionService;

    @RequestMapping(method = RequestMethod.GET)
    public List<OptionDTO> getOptions(){
        List<Option> entities = optionService.getOptions();
        return entities.stream().map(OptionDTO::new).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{optionId}",method = RequestMethod.GET)
    public OptionDTO getOption(@PathVariable(value = "optionId") long optionId){
        Option option = optionService.getOption(optionId);
        return new OptionDTO(option);
    }
}