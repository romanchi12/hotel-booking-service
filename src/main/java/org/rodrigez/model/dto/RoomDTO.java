package org.rodrigez.model.dto;

import org.rodrigez.model.Room;

import java.io.Serializable;

public class RoomDTO implements Serializable {

    private static final long serialVersionUID = 2L;

    private long roomId;
    private long categoryId;
    private int number;
    private int price;

    public RoomDTO(Room room) {
        roomId=room.getRoomId();
        categoryId=room.getCategory().getCategoryId();
        number=room.getNumber();
        price=room.getPrice();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getRoomId() {
        return roomId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public int getNumber() {
        return number;
    }

    public int getPrice() {
        return price;
    }
}