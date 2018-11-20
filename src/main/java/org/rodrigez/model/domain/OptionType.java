package org.rodrigez.model.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "OPTION_TYPE")
@RequiredArgsConstructor
@Data
public class OptionType {

    @Id
    @Column(name = "OPTION_TYPE_ID")
    private long id;

    @Column(name = "DESCRIPTION")
    private String description;
}