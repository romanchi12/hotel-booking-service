package org.rodrigez.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "OPTION_TYPE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OptionType {

    @Id
    @Column(name = "OPTION_TYPE_ID")
    private long id;

    @Column(name = "DESCRIPTION")
    private String description;
}