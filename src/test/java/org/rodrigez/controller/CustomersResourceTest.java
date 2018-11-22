package org.rodrigez.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.rodrigez.model.domain.Booking;
import org.rodrigez.model.domain.Category;
import org.rodrigez.model.domain.Customer;
import org.rodrigez.model.domain.Room;
import org.rodrigez.model.dto.BookingDTO;
import org.rodrigez.model.dto.CustomerDTO;
import org.rodrigez.service.BookingService;
import org.rodrigez.service.CustomerService;
import org.rodrigez.service.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomersResource.class, secure = false)
public class CustomersResourceTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    CustomerService customerService;

    @MockBean
    BookingService bookingService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getCustomer_OK() throws Exception {

        long id = 1;

        Customer customer = Mockito.mock(Customer.class);
        CustomerDTO dto = new CustomerDTO(customer);

        when(this.customerService.getCustomer(1)).thenReturn(customer);

        String expected = mapper.writeValueAsString(dto);

        this.mvc.perform(
                get("/customers/" + id).accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                content().string(expected)
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    public void getCustomer_Error() throws Exception {

        long id = 10;

        Exception exception = new NotFoundException("Invalid customerId " + id);

        when(this.customerService.getCustomer(10)).thenThrow(exception);

        this.mvc.perform(
                get("/customers/" + id).accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                status().isNotFound()
        );
    }

    @Test
    public void addCustomer() throws Exception {

        CustomerDTO dto = new CustomerDTO();
        dto.setName("Mykola");

        Customer customer = dto.toEntity();

        Customer newCustomer = new Customer();
        newCustomer.setId(0);
        newCustomer.setName("Mykola");
        newCustomer.setBookingList(new ArrayList<>());

        CustomerDTO newDTO = new CustomerDTO(customer);

        when(this.customerService.add(customer)).thenReturn(newCustomer);

        String expected = mapper.writeValueAsString(newDTO);

        this.mvc.perform(
                post("/customers").contentType(MediaType.APPLICATION_JSON_UTF8).content(
                        mapper.writeValueAsString(dto)
                )
        ).andExpect(
                content().string(expected)
        ).andExpect(
                status().isCreated()
        );

    }

    @Test
    public void getCustomerBookings() throws Exception {

        long customerId = 1;

        List<Booking> bookingList = mockBookingList(customerId);
        List<BookingDTO> dtoList = bookingList.stream().map(BookingDTO::new).collect(Collectors.toList());

        when(this.bookingService.getCustomerBookings(customerId)).thenReturn(bookingList);

        String expected = mapper.writeValueAsString(dtoList);

        this.mvc.perform(
                get("/customers/" + customerId + "/bookings").contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                content().string(expected)
        ).andExpect(
                status().isOk()
        );

    }

    private List<Booking> mockBookingList(long customerId){
        Customer customer = new Customer();
        customer.setId(customerId);
        Booking booking = new Booking();
        booking.setId(1);
        booking.setCustomer(customer);

        Room room = new Room();
        room.setId(1);
        room.setCategory(Mockito.mock(Category.class));
        room.setBookingList(new ArrayList<>());
        booking.setRoom(room);

        booking.setOptionList(new ArrayList<>());
        booking.setUntil(Mockito.mock(Date.class));
        booking.setFrom(Mockito.mock(Date.class));

        List<Booking> bookingList = Collections.singletonList(booking);

        customer.setBookingList(bookingList);

        return customer.getBookingList();
    }
}