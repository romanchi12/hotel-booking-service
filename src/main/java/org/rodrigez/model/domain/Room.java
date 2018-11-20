package org.rodrigez.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "ROOM")
@Data
public class Room {

    @Id
    @Column(name = "ROOM_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Column(name = "NUMBER")
    private int number;

    @OneToMany(mappedBy = "room")
    private List<RoomOption> optionList = new LinkedList<>();

    @OneToMany(mappedBy = "room")
    private List<Booking> bookingList = new LinkedList<>();

    @Column(name = "PRICE")
    private int currentPrice;
}