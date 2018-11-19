package org.rodrigez.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 4L;

    private long id;
    private String description;
}
