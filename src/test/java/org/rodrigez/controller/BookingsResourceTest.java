package org.rodrigez.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.rodrigez.controller.response.ApiResponse;
import org.rodrigez.controller.response.Status;
import org.rodrigez.model.domain.Booking;
import org.rodrigez.model.domain.Category;
import org.rodrigez.model.domain.Customer;
import org.rodrigez.model.domain.Room;
import org.rodrigez.model.dto.BookingDTO;
import org.rodrigez.model.dto.BookingPriceDTO;
import org.rodrigez.model.dto.CustomerDTO;
import org.rodrigez.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookingsResource.class, secure = false)
public class BookingsResourceTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    BookingService bookingService;

    private ObjectMapper mapper;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    public void getBooking() throws Exception {

        Booking booking = mockBooking();
        BookingDTO dto = new BookingDTO(booking);

        long id = 1;
        when(this.bookingService.getBooking(id)).thenReturn(booking);

        ApiResponse response = new ApiResponse(Status.OK, dto);
        String expected = mapper.writeValueAsString(response);

        this.mvc.perform(
                get("/bookings/" + id).accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                content().string(expected)
        );
    }

    @Test
    public void getBookingPrice() throws Exception {

        Booking booking = mockBooking();
        BookingPriceDTO dto = new BookingPriceDTO(booking);

        long id = 1;
        when(this.bookingService.getBooking(id)).thenReturn(booking);

        ApiResponse response = new ApiResponse(Status.OK, dto);
        String expected = mapper.writeValueAsString(response);

        this.mvc.perform(
                get("/bookings/" + id + "/price").accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                content().string(expected)
        );
    }

    @Test
    public void getBookings() throws Exception {

        List<Booking> bookingList = Collections.singletonList(mockBooking());
        List<BookingDTO> dtoList = bookingList.stream().map(BookingDTO::new).collect(Collectors.toList());

        when(this.bookingService.getBookings()).thenReturn(bookingList);

        ApiResponse response = new ApiResponse(Status.OK, dtoList);
        String expected = mapper.writeValueAsString(response);

        this.mvc.perform(
                get("/bookings").accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                content().string(expected)
        );
    }

    @Test
    public void addBooking() throws Exception {

        BookingDTO dto = mockBookingDTO();
        Booking booking = dto.toEntity();
        Booking newBooking = mockBooking();
        BookingDTO newDTO = new BookingDTO(newBooking);

        when(this.bookingService.add(booking)).thenReturn(newBooking);
        ApiResponse response = new ApiResponse(Status.OK, newDTO);
        String expected = mapper.writeValueAsString(response);

        this.mvc.perform(
                post("/bookings").contentType(MediaType.APPLICATION_JSON_UTF8).content(
                        mapper.writeValueAsString(dto)
                )
        ).andExpect(
                MockMvcResultMatchers.content().string(expected));
    }

    private Booking mockBooking(){
        Booking booking = new Booking();
        booking.setCustomer(new Customer());
        booking.setRoom(new Room());
        booking.getRoom().setCategory(new Category());
        booking.setFrom(new Date());
        booking.setUntil(new Date());
        return booking;
    }

    private BookingDTO mockBookingDTO(){
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setFromDate("2018-11-11");
        bookingDTO.setUntilDate("2018-12-11");
        bookingDTO.setOptionList(new ArrayList<>());
        return bookingDTO;
    }
}