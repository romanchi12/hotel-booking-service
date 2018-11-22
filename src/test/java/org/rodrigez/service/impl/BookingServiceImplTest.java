package org.rodrigez.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rodrigez.model.domain.*;
import org.rodrigez.model.dto.BookingDTO;
import org.rodrigez.model.dto.BookingOptionDTO;
import org.rodrigez.repository.BookingRepository;
import org.rodrigez.service.AvailabilityService;
import org.rodrigez.service.BookingService;
import org.rodrigez.service.CustomerService;
import org.rodrigez.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceImplTest {

    @Autowired
    BookingService bookingService;
    @MockBean
    InventoryService inventoryService;
    @MockBean
    CustomerService customerService;
    @MockBean
    AvailabilityService availabilityService;
    @MockBean
    BookingRepository bookingRepository;

    private Booking booking;

    @Before
    public void setUp() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setCustomerId(1);
        bookingDTO.setRoomId(1);
        bookingDTO.setFromDate("2018-06-07");
        bookingDTO.setUntilDate("2018-06-08");
        BookingOptionDTO option = new BookingOptionDTO();
        option.setOptionTypeId(1);
        bookingDTO.setOptionList(Collections.singletonList(option));
        booking = bookingDTO.toEntity();
    }

    @Test
    public void add_OK() throws Exception {

        Booking booking = this.booking;

        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Mykola");

        Room room = new Room();
        room.setId(1);
        room.setNumber(123);
        room.setBookingList(new ArrayList<>());

        OptionType optionType = new OptionType();
        optionType.setId(1);

        RoomOption roomOption = new RoomOption();
        roomOption.setRoom(room);
        roomOption.setOptionType(optionType);

        when(this.customerService.getCustomer(1)).thenReturn(customer);
        when(this.inventoryService.getRoom(1)).thenReturn(room);
        when(this.availabilityService.isAvailableRoom(any(), any())).thenReturn(true);
        when(this.inventoryService.getOptionType(1)).thenReturn(optionType);
        when(this.inventoryService.getRoomOption(1,1)).thenReturn(roomOption);
        when(bookingRepository.save(any())).thenReturn(booking);

        Booking newBooking = this.bookingService.add(booking);

        assertEquals(booking.getCustomer().getId(), newBooking.getCustomer().getId());

    }
}