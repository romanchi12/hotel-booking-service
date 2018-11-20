package org.rodrigez.model.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.rodrigez.model.dto.BookingDTO;

import javax.persistence.*;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "BOOKING")
@Data
@RequiredArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKING_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="ROOM_ID")
    private Room room;

    @Column(name = "FROM_DATE")
    private Date from;

    @Column(name = "UNTIL_DATE")
    private Date until;

    @OneToMany(mappedBy = "booking",cascade = {CascadeType.PERSIST})
    private List<BookingOption> optionList = new LinkedList<>();

    @Column(name = "ROOM_PRICE")
    private int roomPrice;

    @Column(name = "SUMMARY_PRICE")
    private int summaryPrice;
}