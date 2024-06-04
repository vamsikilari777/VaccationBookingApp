package com.techrovarit;

public class BookingDetails{
    private String name;
    private String bookingType;
    private int numberOfPeople;
    private int numberOfDaysStay;
    private String bookingId;

    public BookingDetails(String name, String bookingType, int numberOfPeople, int numberOfDaysStay, String bookingId) {
        this.name = name;
        this.bookingType = bookingType;
        this.numberOfPeople = numberOfPeople;
        this.numberOfDaysStay = numberOfDaysStay;
        this.bookingId = bookingId;
    }

    public BookingDetails(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public int getNumberOfDaysStay() {
        return numberOfDaysStay;
    }

    public void setNumberOfDaysStay(int numberOfDaysStay) {
        this.numberOfDaysStay = numberOfDaysStay;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}
