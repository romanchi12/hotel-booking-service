package org.rodrigez.model.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.rodrigez.model.ids.RoomOptionId;

import javax.persistence.*;

@Entity
@Table(name = "ROOM_OPTION")
@Data
@RequiredArgsConstructor
@IdClass(RoomOptionId.class)
public class RoomOption {

    @Id
    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @Id
    @ManyToOne
    @JoinColumn(name = "OPTION_TYPE_ID")
    private OptionType optionType;

    @Column(name = "PRICE")
    private int price;
}