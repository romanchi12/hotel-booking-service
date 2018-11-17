package org.rodrigez.service;

import org.rodrigez.model.Option;

import java.util.List;

public interface OptionService {
    List<Option> getOptions();
    Option getOption(long optionId);
}
