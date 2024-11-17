package CarRentalSystem;

import java.time.LocalDate;

public class Booking {
    private int bookingNo;
    private Rental car;
//    Possible status: Awaiting Payment, Awaiting Comfirmation, Ongoing, Confirmed, Completed, Rejected, Cancelled, Not Paid.
    private String status;
    private Customer customer;
    private int fee;
    private LocalDate dateBooked;
    private LocalDate startDate;
    private LocalDate endDate;

    public Booking(int bookingNo, Rental car, String status, Customer customer, int fee, LocalDate dateBooked, LocalDate startDate, LocalDate endDate) {
        this.bookingNo = bookingNo;
        this.car = car;
        this.status = status;
        this.customer = customer;
        this.fee = fee;
        this.dateBooked = dateBooked;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(int bookingNo) {
        this.bookingNo = bookingNo;
    }

    public Rental getCar() {
        return car;
    }

    public void setCar(Rental car) {
        this.car = car;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(LocalDate dateBooked) {
        this.dateBooked = dateBooked;
    }
    
}