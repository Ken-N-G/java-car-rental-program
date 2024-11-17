package CarRentalSystem;

import java.time.LocalDate;
import java.util.ArrayList;

public class Customer extends Admin{
    private long phone;
    private String email;
    private char gender;
    private LocalDate dob;
    private LocalDate dateRegistered;
    private String homeAddress;
    private boolean registered;
    private ArrayList<Booking> myBookings = new ArrayList<Booking>();
    private ArrayList<Receipt> myReceipts = new ArrayList<Receipt>();
    private ArrayList<Feedback> myFeedbacks = new ArrayList<Feedback>();

    public Customer(String username, String password, long phone, String email, char gender, LocalDate dob, LocalDate dateRegistered, String homeAddress, boolean registered) {
        this.phone = phone;
        this.email = email;
        this.gender = gender; // Will be either M, F or O. (M = Male, F = Female, O = Other).
        this.dob = dob;
        this.dateRegistered = dateRegistered;
        this.homeAddress = homeAddress;
        this.registered = registered;
        super.setUsername(username);
        super.setPassword(password);
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
    
    public ArrayList<Booking> getMyBookings() {
        return myBookings;
    }
    
    public void setMyBookings(ArrayList<Booking> myBookings) {
        this.myBookings = myBookings;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }   

    public ArrayList<Receipt> getMyReceipts() {
        return myReceipts;
    }

    public void setMyReceipts(ArrayList<Receipt> myReceipts) {
        this.myReceipts = myReceipts;
    }

    public LocalDate getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(LocalDate dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public ArrayList<Feedback> getMyFeedbacks() {
        return myFeedbacks;
    }

    public void setMyFeedbacks(ArrayList<Feedback> myFeedbacks) {
        this.myFeedbacks = myFeedbacks;
    }
    
}
