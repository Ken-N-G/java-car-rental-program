package CarRentalSystem;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Utility {
    public static ArrayList<Customer> allCustomers = new ArrayList<Customer>();
    public static ArrayList<Admin> allAdmins = new ArrayList<Admin>();
    public static ArrayList<Rental> allRentals = new ArrayList<Rental>();
    public static ArrayList<Booking> allBookings = new ArrayList<Booking>();
    public static ArrayList<Receipt> allReceipts = new ArrayList<Receipt>();
    public static ArrayList<Feedback> allFeedbacks = new ArrayList<Feedback>();
    public static DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
    public static LocalDate cd = LocalDate.now();
    public static void readFromFile(){
        try{
//            Loop to get customers from customer file into customer ArrayList
            File file = new File("customer.txt");
            Scanner s1 = new Scanner(file);
            while(s1.hasNext()){
                String username = s1.nextLine();
                String password = s1.nextLine();
                long phone = Long.parseLong(s1.nextLine());
                String email = s1.nextLine();
                char gender = s1.nextLine().charAt(0);
                LocalDate dob = LocalDate.parse(s1.nextLine(), f);
                LocalDate dateRegistered = LocalDate.parse(s1.nextLine(), f);
                String homeAddress = s1.nextLine();
                boolean registered = Boolean.parseBoolean(s1.nextLine());
                s1.nextLine();
                Customer c = new Customer(username, password, phone, email, gender, dob, dateRegistered, homeAddress, registered);
                allCustomers.add(c);
            }
//            Loop to get admins from admin file into admin ArrayList
            Scanner s2 = new Scanner(new File("admin.txt"));
            while(s2.hasNext()){
                String username = s2.nextLine();
                String password = s2.nextLine();
                s2.nextLine();
                Admin a = new Admin(username, password);
                allAdmins.add(a);
            }
//            Loop to get all rental cars from rental file into rental ArrayList
            Scanner s3 = new Scanner(new File("rental.txt"));
            while(s3.hasNext()){
                String licensePlate = s3.nextLine();
                String carType = s3.nextLine();
                String carColor = s3.nextLine();
                String carManufacturer = s3.nextLine();
                String carModel = s3.nextLine();
                LocalDate dateAdded = LocalDate.parse(s3.nextLine());
                int fuelCapacity = Integer.parseInt(s3.nextLine());
                int modelYear = Integer.parseInt(s3.nextLine());
                int mileage = Integer.parseInt(s3.nextLine());
                int rate = Integer.parseInt(s3.nextLine());
                s3.nextLine();
                Rental r = new Rental(licensePlate, carType, carColor, carManufacturer, carModel, dateAdded, fuelCapacity, modelYear, mileage, rate);
                allRentals.add(r);
            }
//            Loop to get all bookings from booking file into booking ArrayList
            Scanner s4 = new Scanner(new File("booking.txt"));
            while(s4.hasNext()){
                int bookingNo = Integer.parseInt(s4.nextLine());
                Rental car = checkRental(s4.nextLine());
                String status = s4.nextLine();
                Customer customer = checkCustomer(s4.nextLine());
                int fee = Integer.parseInt(s4.nextLine());
                LocalDate dateBooked = LocalDate.parse(s4.nextLine());
                LocalDate startDate = LocalDate.parse(s4.nextLine(), f);
                LocalDate endDate = LocalDate.parse(s4.nextLine(), f);
                s4.nextLine();
                Booking b = new Booking(bookingNo, car, status, customer, fee, dateBooked, startDate, endDate);
                allBookings.add(b);
            }
//            Loop to get all receipts from receipt file into receipt ArrayList
            Scanner s5 = new Scanner(new File("receipt.txt"));
            while(s5.hasNext()){
                int receiptNo = Integer.parseInt(s5.nextLine());
                String paymentType = s5.nextLine();
                Booking booking = checkBooking(Integer.parseInt(s5.nextLine()));
                Customer customer = checkCustomer(s5.nextLine());
                LocalDate datePaid = LocalDate.parse(s5.nextLine(), f);
                s5.nextLine();
                Receipt r = new Receipt(receiptNo, paymentType, booking, customer, datePaid);
                allReceipts.add(r);
            }
//            Loop to get all feedbacks from feedback file into feedback ArrayList
            Scanner s6 = new Scanner(new File("feedback.txt"));
            while(s6.hasNext()){
                int feedbackNo = Integer.parseInt(s6.nextLine());
                Customer customer = checkCustomer(s6.nextLine());
                int rating = Integer.parseInt(s6.nextLine());
                String subjectMatter = s6.nextLine();
                String comment = s6.nextLine();
                s6.nextLine();
                Feedback fe = new Feedback(feedbackNo, customer, rating, subjectMatter, comment);
                allFeedbacks.add(fe);
            }
//            Get all customer specific bookings, receipts, and feedback into each customer profile stored in the customer class as myBookings, myReceipts, and myFeedbacks ArrayList
            for(Customer c : allCustomers){
                ArrayList<Booking> cBookings = new ArrayList<Booking>();
                ArrayList<Receipt> cReceipts = new ArrayList<Receipt>();
                ArrayList<Feedback> cFeedback = new ArrayList<Feedback>();
                for(Booking b : allBookings){
                    if(c.getUsername().equals(b.getCustomer().getUsername())){
                        cBookings.add(b);
                    }
                }
                c.setMyBookings(cBookings);
                for(Receipt r : allReceipts){
                    if(c.getUsername().equals(r.getCustomer().getUsername())){
                        cReceipts.add(r);
                    }
                }
                c.setMyReceipts(cReceipts);
                for(Feedback fe : allFeedbacks){
                    if(c.getUsername().equals(fe.getCustomer().getUsername())){
                        cFeedback.add(fe);
                    }
                }
                c.setMyFeedbacks(cFeedback);
            }
        }catch(Exception e){
            System.out.println("Reading error or files not found");
        }
    }
    
    public static void writeToFile (){
        try{
//            Loop to write customer profiles into customer.txt
            PrintWriter p1 = new PrintWriter("customer.txt");
            for(Customer c : allCustomers){
                p1.println(c.getUsername());
                p1.println(c.getPassword());
                p1.println(Long.toString(c.getPhone()));
                p1.println(c.getEmail());
                p1.println(Character.toString(c.getGender()));
                p1.println(c.getDob().toString());
                p1.println(c.getDateRegistered().toString());
                p1.println(c.getHomeAddress());
                p1.println(Boolean.toString(c.isRegistered()));
                p1.println();
            }
            p1.close();
//            Loop to write admin profiles into customer.txt
            PrintWriter p2 = new PrintWriter("admin.txt");
            for(Admin a : allAdmins){
                p2.println(a.getUsername());
                p2.println(a.getPassword());
                p2.println();
            }
            p2.close();
//            Loop to write rental information into rental.txt
            PrintWriter p3 = new PrintWriter("rental.txt");
            for(Rental r : allRentals){
                p3.println(r.getLicensePlate());
                p3.println(r.getCarType());
                p3.println(r.getCarColor());
                p3.println(r.getCarManufacturer());
                p3.println(r.getCarModel());
                p3.println(r.getDateAdded().toString());
                p3.println(Integer.toString(r.getFuelCapacity()));
                p3.println(Integer.toString(r.getModelYear()));
                p3.println(Integer.toString(r.getMileage()));
                p3.println(Integer.toString(r.getRate()));
                p3.println();
            }
            p3.close();
//            Loop to write booking informtion into booking.txt
            PrintWriter p4 = new PrintWriter("booking.txt");
            for(Booking b : allBookings){
                p4.println(Integer.toString(b.getBookingNo()));
                p4.println(b.getCar().getLicensePlate());
                p4.println(b.getStatus());
                p4.println(b.getCustomer().getUsername());
                p4.println(Integer.toString(b.getFee()));
                p4.println(b.getDateBooked().toString());
                p4.println(b.getStartDate().toString());
                p4.println(b.getEndDate().toString());
                p4.println();
            }
            p4.close();
//            Loop to write receipt information into receipt.txt
            PrintWriter p5 = new PrintWriter("receipt.txt");
            for(Receipt r : allReceipts){
                p5.println(Integer.toString(r.getReceiptNo()));
                p5.println(r.getPaymentType());
                p5.println(Integer.toString(r.getBooking().getBookingNo()));
                p5.println(r.getCustomer().getUsername());
                p5.println(r.getDatePaid().toString());
                p5.println();
            }
            p5.close();
//            Loop to write feedback information into feedback.txt
            PrintWriter p6 = new PrintWriter("feedback.txt");
            for(Feedback fe : allFeedbacks){
                p6.println(Integer.toString(fe.getFeedbackNo()));
                p6.println(fe.getCustomer().getUsername());
                p6.println(Integer.toString(fe.getRating()));
                p6.println(fe.getSubjectMatter());
                p6.println(fe.getComment());
                p6.println();
            }
            p6.close();
        }catch(Exception e){
            System.out.println("Writing error.");
        }
    }
    
    public static void boot(){
        String[] files = new String[]{"customer.txt", "admin.txt", "rental.txt", "booking.txt", "receipt.txt", "feedback.txt"};
        for(String s : files){
            File file = new File(s);
            if(file.exists() == false){
                System.out.println(s+" file does not exist");
                try{
                    file.createNewFile();
                }catch(Exception ex){
                    System.out.println("Error in creating "+s);
                }
            }else{
                System.out.println(s+" file exists");
            }
        }
        try{
            Scanner scanner = new Scanner(new File(files[1]));
            if(!scanner.hasNext()){
                try (PrintWriter p = new PrintWriter(files[1])) {
                    p.println("Admin1");
                    p.println("12345");
                    p.println();
                    p.close();
                }
                System.out.println("No admin profiles detected. Admin profile with username 'Admin1' and password '12345' has been successfully created");
            }else{
                System.out.println("At least 1 admin user profile in admin file. Program can proceed");
            }
        }catch(Exception e){
            System.out.println("Error in admin file creation");
        }
    }
    
    public static Customer checkCustomer(String username){
        Customer found = null;
        for(Customer c : allCustomers){
            if(username.equals(c.getUsername())){
                found = c;
                break;
            }
        }
        return found;
    }
    
    public static Admin checkAdmin(String username){
        Admin found = null;
        for(Admin a : allAdmins){
            if(username.equals(a.getUsername())){
                found = a;
                break;
            }
        }
        return found;
    }
    
    public static Rental checkRental(String licensePlate){
        Rental found = null;
        for(Rental r : allRentals){
            if(licensePlate.equals(r.getLicensePlate())){
                found = r;
                break;
            }
        }
        return found;
    }
   
    public static Booking checkBooking(int bookingNo){
        Booking found = null;
        for(Booking b : allBookings){
            if(bookingNo == b.getBookingNo()){
                found = b;
                break;
            }
        }
        return found;
    }
    
    public static Receipt checkReceipt(int receiptNo){
        Receipt found = null;
        for(Receipt r : allReceipts){
            if(receiptNo == r.getReceiptNo()){
                found = r;
                break;
            }
        }
        return found;
    }
    
    public static Feedback checkFeedback(int feedbackNo){
        Feedback found = null;
        for(Feedback fe : allFeedbacks){
            if(feedbackNo == fe.getFeedbackNo()){
                found = fe;
                break;
            }
        }
        return found;
    }
    
    public static int daysBetweenDates(LocalDate startDate, LocalDate endDate){
        int numberOfDays;
        try{
            numberOfDays = (int)ChronoUnit.DAYS.between(startDate, endDate);
        }catch(Exception e){
            numberOfDays = 0;
        }
        return numberOfDays;
    }
    
    public static int yearsBetweenDates(LocalDate startDate, LocalDate endDate){
        int numberOfYears;
        try{
            numberOfYears = (int)ChronoUnit.YEARS.between(startDate, endDate);
        }catch(Exception e){
            numberOfYears = 0;
        }
        return numberOfYears;
    }
    
    public static void updateBookingStatusByDate(){
        String[] skippedStatuses = {"Cancelled", "Finished"};
        for(Booking b : allBookings){
            if(Arrays.asList(skippedStatuses).contains(b.getStatus())){
                continue;
            }
            if(b.getStartDate().isBefore(cd) && b.getEndDate().isAfter(cd)) {
                if(b.getStatus().contains("Awaiting")){
                    b.setStatus("Cancelled");
                } else {
                    b.setStatus("Ongoing");
                }
            } else if(b.getEndDate().isBefore(cd)) {
                if(b.getStatus().contains("Awaiting")){
                    b.setStatus("Cancelled");
                } else {
                    b.setStatus("Finished");
                }
            }
        }
    }
    
     // Type Validation functions (Jilan).
    
    public static boolean isName(String name){
        // To check whether the name inputted is text. It can accept any inputs such as jILan, Jilan, etc.
        
        boolean validName = false;
        
        char stringArray[] = name.toCharArray();
        
        for(char c: stringArray){
            if((c >= 97 && c <= 122) || (c >= 65 && c <= 90) || !name.contains(" ")){
                validName = true;
            }else{
                validName = false;
                break;
            }
        }
        
        return validName;
    }
    
    public static String getFormattedName(String name){
        
        // Function for changing the name in the correct format.
        
        String formattedName = "";
        char[] nameArray = name.toCharArray();
        
        // Making every letter lowercase.
        for(int k = 0; k < nameArray.length; k++){
            nameArray[k] = Character.toLowerCase(nameArray[k]);
        }
        
        // Making the first letter uppercase.
        nameArray[0] = Character.toUpperCase(nameArray[0]);
        
        
        formattedName = String.valueOf(nameArray);
        
        return formattedName;
        
    }
    
    
    public static boolean isInt(String val, int minRange, int maxRange){
        int num = 0;
        
        boolean check1 = false; // To check if its an integer.
        boolean check2 = false; // To check if its in the range.
        
        // check1.
        try{
            num = Integer.parseInt(val);
            check1 = true;
        }catch(Exception e){
            check1 = false;
        }
        
        // check2.
        if(num >= minRange && num <= maxRange){
            check2 = true;
        }else{
            check2 = false;
        }
        
        return (check1 && check2);
        
    }
    
    public static boolean isPhone(long number){
        
        boolean validNumber = false;
        
        if(number>= 60000000000L && number <= 609999999999L){
            validNumber = true;
        }
        
        return validNumber;
    }
    
    
    public static boolean isGender(String gender){
        // For checking the date when it is inputted.
        
        boolean validGender = false;
        
        if(gender.equals("M") || gender.equals("F") || gender.equals("O")){
            validGender = true;
        }
        
        return validGender;
    }
    
    public static boolean isDate(String date){
        boolean validDate = false;
        
        try{
            LocalDate.parse(date, f);
            validDate = true;
        }catch(Exception e){
            validDate = false;
        }
        
        return validDate;
    }
    
    public static boolean isMail(String mail){
        boolean validMail = false;
        
        if(mail.matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            validMail = true;
        }
        
        return validMail;
    }
    
    
        public static void deleteRental(String licensePlate){
            
            for(int k = 0; k < allRentals.size(); k++){
                Rental rental = allRentals.get(k);
                if(rental.getLicensePlate().equals(licensePlate)){
                    allRentals.remove(rental);
                }
            }
        }
        
        
        
        
        // Validation Function
        
        public static boolean isNull(Object o){
        boolean retVal = false;
        if(o == null){
            retVal = true;
        }
        return retVal;
    }
        
        
        
        public static boolean hasChar(String s, char c){
    boolean retVal = false;
    char charArray[] = s.toCharArray();
    
    for(char k: charArray){
        if(k == c){
            retVal = true;
        }
    }

    return retVal;
}

public static boolean isCharInt(char c){
    boolean retVal = Character.isDigit(c);
    
    return retVal;
}

public static boolean isCharString(char c){
    boolean retVal = Character.isLetter(c);
    
    return retVal;
}

public static boolean hasIllegalChar(String s){
    boolean retVal = true;
    
    char charArray[] = s.toCharArray();
    
    for(char c: charArray){
        if((!(isCharInt(c)) && !(isCharString(c))) && c != '-'){
            retVal = false;
        }
    }
    
    return retVal;
}

public static boolean checkLength(String s, int min, int max){
    boolean retVal = false;
    
    if(s.length() <= max && s.length() >= min){
        retVal = true;
    }
    
    return retVal;
}

public static boolean isLicensePlate(String licensePlate){
    boolean retVal = false;
    
    boolean check1 = hasIllegalChar(licensePlate);
    
    boolean check2 = false;
    if(licensePlate.length() >= 2){
        String first = licensePlate.substring(0, 1);
        String last = licensePlate.substring(licensePlate.length() - 2, licensePlate.length() - 1);
        check2 = !(first.equals("-")) && !(last.equals("-"));
    }else{
        check2 = false;
    }
    
    
    boolean check3 = checkLength(licensePlate, 2, 8);
    
    
    retVal = check1 && check2 && check3;
    
    return retVal;
}

public static int getCurrentYear(){ // For validating the rental model year.
    int retVal = Year.now().getValue();
    return retVal;
}
  
public static boolean isNumber(String s){
        boolean retVal = false;
        
        try{
            Integer.valueOf(s);
            retVal = true;
        }catch(Exception e){
            retVal = false;
        }
        
        return retVal;
    }
  

    public static boolean startswithAdmin(String username){
       boolean retVal = false;
       
       if(username.substring(0, 5).equals("Admin")){
           retVal = true;
       }
       
       return retVal;
    }
  

    
    public static boolean endswithAdmin(String username){
       boolean retVal = false;
       int len = username.length();
       
       if(isNumber(username.substring(5, len))){
           retVal = true;
       }
       
       
       return retVal;
    }
    
    public static boolean isAdminUsername(String username){
        boolean retVal = false;
        
        boolean check1 = false;
        boolean check2 = false;
        boolean check3 = false;
        
        if(username.length()>=5 && username.length() <= 11){
            check1 = true;
            if(startswithAdmin(username)){
                check2 = true;
            }

            if(endswithAdmin(username)){
                check3 = true;
            }
        }
        
        
        retVal = check1 && check2 && check3;
        return retVal;
    }

}


  