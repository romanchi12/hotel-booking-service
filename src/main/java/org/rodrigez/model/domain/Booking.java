package org.rodrigez.model.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "BOOKING")
@Data
@RequiredArgsConstructor
public class Booking {
    @Id
    @Column(name = "BOOKING_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ROOM_ID")
    private Room room;

    @Column(name = "FROM_DATE")
    private Date from;

    @Column(name = "UNTIL_DATE")
    private Date until;

    @OneToMany(mappedBy = "booking")
    private List<BookingOption> bookingOptionList = new LinkedList<>();

    @Column(name = "ROOM_PRICE")
    private int roomPrice;

    @Column(name = "SUMMARY_PRICE")
    private int summaryPrice;

    public void addBookingOption(OptionType optionType, int price){
        BookingOption bookingOption = new BookingOption(this, optionType,price);
        bookingOptionList.add(bookingOption);
    }
}