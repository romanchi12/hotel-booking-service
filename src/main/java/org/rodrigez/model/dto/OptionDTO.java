package org.rodrigez.model.dto;

import org.rodrigez.model.Option;

import java.io.Serializable;

public class OptionDTO implements Serializable {

    private static final long serialVersionUID = 4L;

    private long optionId;
    private String description;
    private int price;

    public OptionDTO(Option option){
        optionId = option.getOptionId();
        description = option.getDescription();
        price = option.getPrice();
    }

    public OptionDTO() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getOptionId() {
        return optionId;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public void setOptionId(long optionId) {
        this.optionId = optionId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}