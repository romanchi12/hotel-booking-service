package org.rodrigez.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@Data
public class Customer {
    @Id
    @Column(name = "CUSTOMER_ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "customer")
    private List<Booking> bookingList = new LinkedList<>();

    public void addBooking(Booking booking){
        bookingList.add(booking);
    }

}