package org.rodrigez.validation;


public class BookingRequest{
    // TODO: 16.11.18 validation here for username

    private String username;
    private long roomId;
    private String from;
    private String until;
    private String optionIdList;
    private int price;

    public BookingRequest() {
    }

    public BookingRequest(String username, long roomId, String from, String until, String optionIdList, int price) {
        this.username = username;
        this.roomId = roomId;
        this.from = from;
        this.until = until;
        this.optionIdList = optionIdList;
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public long getRoomId() {
        return roomId;
    }

    public String getFrom() {
        return from;
    }

    public String getUntil() {
        return until;
    }

    public String getOptionIdList() {
        return optionIdList;
    }

    public int getPrice() {
        return price;
    }
}
