package org.rodrigez.model.ids;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rodrigez.model.domain.Booking;
import org.rodrigez.model.domain.OptionType;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingOptionId implements Serializable {

    private Booking booking;

    private OptionType optionType;
}
