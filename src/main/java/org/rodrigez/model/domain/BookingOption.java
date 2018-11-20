package org.rodrigez.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.rodrigez.model.ids.BookingOptionId;

import javax.persistence.*;

@Entity
@Table(name = "BOOKING_OPTION")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@IdClass(BookingOptionId.class)
public class BookingOption {

    @Id
    @ManyToOne
    @JoinColumn(name = "BOOKING_ID")
    private Booking booking;

    @Id
    @ManyToOne
    @JoinColumn(name = "OPTION_TYPE_ID")
    private OptionType optionType;

    @Column(name = "PRICE")
    private int price;
}