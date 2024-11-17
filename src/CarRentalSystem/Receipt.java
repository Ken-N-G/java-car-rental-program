package CarRentalSystem;

import java.time.LocalDate;

public class Receipt {
    private int receiptNo;
    private String paymentType;
    private Booking booking;
    private Customer customer;
    private LocalDate datePaid;

    public Receipt(int receiptNo, String paymentType, Booking booking, Customer customer, LocalDate datePaid) {
        this.receiptNo = receiptNo;
        this.paymentType = paymentType;
        this.booking = booking;
        this.customer = customer;
        this.datePaid = datePaid;
    }

    public int getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(int receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(LocalDate datePaid) {
        this.datePaid = datePaid;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
    
}
