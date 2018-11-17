package org.rodrigez.model.dto;

import org.rodrigez.model.User;
import org.rodrigez.validation.UserRequest;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 3L;

    private long userId;
    private String name;
    //private long bookingId;

    public UserDTO(User user) {
        userId=user.getUserId();
        name=user.getName();
        //bookingId=user.getBooking().getBookingId();
    }

    public UserDTO(UserRequest userRequest) {
        name=userRequest.getName();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

//    public long getBookingId() {
//        return bookingId;
//    }
}
