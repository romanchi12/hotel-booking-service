package org.rodrigez.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "ROOM")
public class Room {
    @Id
    @Column(name = "ROOM_ID")
    private long roomId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
    @Column(name = "NUMBER")
    private int number;
    @OneToMany(mappedBy = "room")
    private List<Booking> bookingList = new LinkedList<>();
    @Column(name = "PRICE")
    private int price;

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}