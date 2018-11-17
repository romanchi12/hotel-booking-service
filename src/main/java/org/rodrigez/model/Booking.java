package org.rodrigez.model;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "BOOKING")
public class Booking {
    @Id
    @Column(name = "BOOKING_ID")
    private long bookingId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="USER_ID", referencedColumnName = "USER_ID")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ROOM_ID")
    private Room room;
    @Column(name = "FROM_DATE")
    private Date from;
    @Column(name = "UNTIL_DATE")
    private Date until;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "BOOKING_OPTION_DATA", joinColumns = {@JoinColumn(name = "BOOKING_ID")},inverseJoinColumns = {@JoinColumn(name = "OPTION_ID")})
    private List<Option> optionList = new LinkedList<>();
    @Column(name = "PRICE")
    private int price;

    public Booking() {
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getUntil() {
        return until;
    }

    public void setUntil(Date until) {
        this.until = until;
    }

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}