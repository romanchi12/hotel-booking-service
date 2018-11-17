package org.rodrigez.model;

import javax.persistence.*;

@Entity
@Table(name = "CLIENT")
public class User {
    @Id
    @Column(name = "USER_ID")
    private long userId;
    @Column(name = "USERNAME")
    private String name;
    @OneToOne(mappedBy = "user")
    private Booking booking;

    public User() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
