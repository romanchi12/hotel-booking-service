package org.rodrigez.model.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@Data
@RequiredArgsConstructor
public class Customer {
    @Id
    @Column(name = "CUSTOMER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST})
    private List<Booking> bookingList = new ArrayList<>();

    public void addBooking(Booking booking){
        bookingList.add(booking);
    }

}