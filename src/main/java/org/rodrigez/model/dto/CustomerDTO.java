package org.rodrigez.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.rodrigez.model.domain.Booking;
import org.rodrigez.model.domain.Customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = 3L;

    private long id;
    private String name;
    private List<BookingDTO> bookingList = new ArrayList<>();

    public CustomerDTO() {
    }

    public CustomerDTO(Customer customer) {
        id = customer.getId();
        name = customer.getName();
        for(Booking booking: customer.getBookingList()){
            BookingDTO bookingDTO = new BookingDTO(booking);
            bookingList.add(bookingDTO);
        }
    }

    public Customer toEntity(){
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        List<Booking> bookingList = new ArrayList<>();
        for(BookingDTO bookingDTO : this.bookingList){
            Booking booking = bookingDTO.toEntity();
            bookingList.add(booking);
        }
        customer.setBookingList(bookingList);
        return customer;
    }
}