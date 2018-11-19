package org.rodrigez.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
@Data
@RequiredArgsConstructor
public class Category {

    @Id
    @Column(name = "CATEGORY_ID")
    private long id;

    @Column(name = "DESCRIPTION")
    private String description;
}
