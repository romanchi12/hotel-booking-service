package org.rodrigez.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rodrigez.model.domain.OptionType;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class OptionTypeDTO implements Serializable {

    private static final long serialVersionUID = 4L;

    private long id;
    private String description;

    public OptionTypeDTO(OptionType optionType) {
        id = optionType.getId();
        description = optionType.getDescription();
    }
}