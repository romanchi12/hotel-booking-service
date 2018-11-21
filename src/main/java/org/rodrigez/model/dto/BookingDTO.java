package org.rodrigez.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.rodrigez.model.domain.*;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Getter
@Setter
public class BookingDTO implements Serializable {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static {
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Kiev");
        dateFormat.setTimeZone(timeZone);
    }

    private static final long serialVersionUID = 1L;

    private long id;
    private long customerId;
    private String customerName;
    private long roomId;
    private long roomCategoryId;
    private String roomCategoryDescription;
    private int roomNumber;
    private String fromDate;
    private String untilDate;
    private List<BookingOptionDTO> optionList;
    private int roomPrice;
    private int summaryPrice;

    public BookingDTO(){
    }

    public BookingDTO(Booking booking) {
        this.id = booking.getId();
        this.customerId = booking.getCustomer().getId();
        this.customerName = booking.getCustomer().getName();
        this.roomId = booking.getRoom().getId();
        this.roomCategoryId = booking.getRoom().getCategory().getId();
        this.roomCategoryDescription = booking.getRoom().getCategory().getDescription();
        this.roomNumber = booking.getRoom().getNumber();
        this.fromDate = dateFormat.format(booking.getFrom());
        this.untilDate = dateFormat.format(booking.getUntil());
        optionList = new ArrayList<>();
        for(BookingOption option: booking.getOptionList()){
            BookingOptionDTO optionDTO = new BookingOptionDTO(option);
            optionList.add(optionDTO);
        }
        this.roomPrice = booking.getRoomPrice();
        this.summaryPrice = booking.getSummaryPrice();
    }

    public Booking toEntity(){
        Booking booking = new Booking();

        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName(customerName);
        booking.setCustomer(customer);

        Room room = new Room();
        room.setId(roomId);
        Category category = new Category();
        category.setId(roomCategoryId);
        category.setDescription(roomCategoryDescription);
        room.setCategory(category);
        room.setNumber(roomNumber);
        booking.setRoom(room);

        booking.setFrom(Date.valueOf(LocalDate.parse(fromDate, dateTimeFormatter)));
        booking.setUntil(Date.valueOf(LocalDate.parse(untilDate, dateTimeFormatter)));

        List<BookingOption> optionList = new ArrayList<>();
        for(BookingOptionDTO optionDTO: this.optionList){
            BookingOption bookingOption = optionDTO.toEntity();
            optionList.add(bookingOption);
        }
        booking.setOptionList(optionList);

        booking.setRoomPrice(roomPrice);
        booking.setSummaryPrice(summaryPrice);

        return booking;
    }
}