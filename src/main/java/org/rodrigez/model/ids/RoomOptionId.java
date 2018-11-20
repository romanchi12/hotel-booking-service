package org.rodrigez.model.ids;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rodrigez.model.domain.OptionType;
import org.rodrigez.model.domain.Room;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomOptionId implements Serializable {

    private Room room;

    private OptionType optionType;
}
