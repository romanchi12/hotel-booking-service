package org.rodrigez.service.impl;

import org.rodrigez.model.Option;
import org.rodrigez.repository.OptionRepository;
import org.rodrigez.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {
    @Autowired
    OptionRepository optionRepository;

    @Override
    public List<Option> getOptions() {
        return optionRepository.findAll();
    }

    @Override
    public Option getOption(long optionId) {
        return optionRepository.getOne(optionId);
    }
}
