package org.rodrigez.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.rodrigez.model.domain.Category;

import java.io.Serializable;

@Getter
@Setter
public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 4L;

    private long id;
    private String description;

    public CategoryDTO(){
    }

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.description = category.getDescription();
    }
}