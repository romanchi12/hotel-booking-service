package org.rodrigez.model.domain;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "OPTION_TYPE")
@RequiredArgsConstructor
@Data
public class OptionType {

    @Id
    @Column(name = "OPTION_TYPE_ID")
    @NotFound(action = NotFoundAction.IGNORE)
    private long id;

    @Column(name = "DESCRIPTION")
    private String description;
}