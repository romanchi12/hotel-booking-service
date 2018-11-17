package org.rodrigez.model.dto;

import org.rodrigez.model.Category;

import java.io.Serializable;

public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 4L;

    private long categoryId;
    private String description;

    public CategoryDTO(Category category){
        categoryId=category.getCategoryId();
        description=category.getDescription();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return description;
    }
}
