package CarRentalSystem;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class CustomerCreateBookingPage implements ActionListener, FocusListener{
    private JFrame f;
    private JPanel p1, p2, p3, p4, p5;
    private Rental chosenCar = null;
    private JComboBox carChoices;
    private JButton back, clear, createBooking, toRentals, chooseFromRentalsTab, getBookingDetails;
    private JTextField startDateInput, endDateInput;
    private int feeAmount;
    JLabel licensePlateLabel, manufacturerLabel, modelLabel, modelYearLabel, typeLabel,
            colorLabel, rateLabel, mileageLabel, fuelCapacityLabel, numberOfDaysRentLabel,
            totalFee;


    public CustomerCreateBookingPage() {
        f = new JFrame("Car Rental - Customer Create Booking Page");
        f.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmChoice = JOptionPane.showConfirmDialog(f, "Are you sure you want to exit now? Any changes that are not updated will not be saved!", "Close Program", JOptionPane.YES_NO_OPTION);
                if(confirmChoice == 0){
                    Utility.writeToFile();
                    System.exit(0);
                }
            }
        }
        );
        
        p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        p3 = new JPanel();
        p4 = new JPanel(new GridLayout(6, 2, 30, 10));
        p5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        
        back = new JButton("Back");
        clear = new JButton("Clear");
        createBooking = new JButton("Create Booking");
        toRentals = new JButton("Select from Rental page");
        chooseFromRentalsTab = new JButton("Select a car from the Rentals page");
        getBookingDetails = new JButton("Get details");
        back.addActionListener(this);
        clear.addActionListener(this);
        getBookingDetails.addActionListener(this);
        chooseFromRentalsTab.addActionListener(this);
        createBooking.addActionListener(this);
        chooseFromRentalsTab.setBounds(30, 40, 540, 30);
        
        JLabel title = new JLabel("Customer Create Booking Page");
        JLabel startDate = new JLabel("Enter Start Date:");
        JLabel endDate = new JLabel("Enter End Date:");
        JLabel dateRules = new JLabel("Both dates cannot be the same or set in the past and the end date must not be less than the start date!");
        JLabel carChoicesLabel = new JLabel("Choose a Car to rent:");
        licensePlateLabel = new JLabel();
        manufacturerLabel = new JLabel();
        modelLabel = new JLabel();
        modelYearLabel = new JLabel();
        typeLabel = new JLabel();
        colorLabel = new JLabel();
        rateLabel = new JLabel();
        mileageLabel = new JLabel();
        fuelCapacityLabel = new JLabel();
        numberOfDaysRentLabel = new JLabel();
        totalFee = new JLabel();
        carChoicesLabel.setBounds(30, 0, 120, 30);
        
        startDateInput = new JTextField(Utility.cd.toString());
        endDateInput = new JTextField(Utility.cd.toString());
        startDateInput.addFocusListener(this);
        endDateInput.addFocusListener(this);
        carChoices = new JComboBox();
        carChoices.setBounds(160, 0, 410, 30);
        carChoices.setBackground(Color.white);
        addDataToJComboBox();
        
        p4.setBackground(Color.WHITE);
        p4.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        p1.add(title);
        p1.add(dateRules);
        p5.add(back);
        p5.add(clear);
        p5.add(getBookingDetails);
        p5.add(createBooking);
        p2.add(startDate);
        p2.add(startDateInput);
        p2.add(endDate);
        p2.add(endDateInput);
        p3.add(carChoices);
        p3.add(carChoicesLabel);
        p3.add(chooseFromRentalsTab);
        p4.add(licensePlateLabel);
        p4.add(manufacturerLabel);
        p4.add(modelLabel);
        p4.add(modelYearLabel);
        p4.add(typeLabel);
        p4.add(colorLabel);
        p4.add(rateLabel);
        p4.add(mileageLabel);
        p4.add(fuelCapacityLabel);
        p4.add(numberOfDaysRentLabel);
        p4.add(totalFee);
        p5.setBounds(0, 500, 600, 100);
        p4.setBounds(30, 210, 540, 280);
        p3.setBounds(0, 130, 600, 70);
        p2.setBounds(0, 85, 600, 30);
        p1.setBounds(0, 0, 600, 70);
        p3.setLayout(null);
        f.add(p5);
        f.add(p4);
        f.add(p3);
        f.add(p2);
        f.add(p1);
        
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setSize(600, 600);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
    }

    public Rental getChosenCar() {
        return chosenCar;
    }

    public void setChosenCar(Rental chosenCar) {
        this.chosenCar = chosenCar;
    }

    public JFrame getJFrame() {
        return f;
    }
    
    public void calculateFee(int days, Rental car){
        feeAmount = (car.getRate() * days);
    }
    
    public void setLabels() {
        licensePlateLabel.setText("License Plate Number: " + chosenCar.getLicensePlate());
        manufacturerLabel.setText("Manufacturer: " + chosenCar.getCarManufacturer());
        modelLabel.setText("Model: " + chosenCar.getCarModel());
        modelYearLabel.setText("Model Year: " + chosenCar.getModelYear());
        typeLabel.setText("Type: " + chosenCar.getCarType());
        colorLabel.setText("Color: " + chosenCar.getCarColor());
        rateLabel.setText("Rate: " + chosenCar.getRate() + " per Day");
        mileageLabel.setText("Mileage: " + chosenCar.getMileage() + " km");
        fuelCapacityLabel.setText("Fuel Capacity: " + chosenCar.getFuelCapacity() + " L");
        numberOfDaysRentLabel.setText("Renting for " + Utility.daysBetweenDates(LocalDate.parse(startDateInput.getText(), Utility.f),LocalDate.parse(endDateInput.getText(), Utility.f)) + " day(s)");
        calculateFee(Utility.daysBetweenDates(LocalDate.parse(startDateInput.getText(), Utility.f),LocalDate.parse(endDateInput.getText(), Utility.f)), chosenCar);
        totalFee.setText("Total fee: RM " + feeAmount);
    }
    
    public void clearLabels(){
        licensePlateLabel.setText("");
        manufacturerLabel.setText("");
        modelLabel.setText("");
        modelYearLabel.setText("");
        typeLabel.setText("");
        colorLabel.setText("");
        rateLabel.setText("");
        mileageLabel.setText("");
        fuelCapacityLabel.setText("");
        numberOfDaysRentLabel.setText("");
        totalFee.setText("");
    }
    
    public void clearInputs(){
        startDateInput.setText(Utility.cd.toString());
        endDateInput.setText(Utility.cd.toString());
        carChoices.setSelectedItem(0);
    }
    
    public void addDataToJComboBox(){
        String entry;
        for(Rental r : Utility.allRentals){
            entry = r.getLicensePlate();
            carChoices.addItem(entry);
        }
    }

    public JComboBox getCarChoices() {
        return carChoices;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try{
            if(ae.getSource() == back){
                chosenCar = null;
                clearLabels();
                f.setVisible(false);
                Program.customerBookingHomepage.getJFrame().setVisible(true);
            } else if(ae.getSource() == chooseFromRentalsTab){
                f.setVisible(false);
                Program.customerRentalsPage.getJFrame().setVisible(true);
            } else if(ae.getSource() == getBookingDetails){
                if(Utility.isDate(startDateInput.getText()) == false || Utility.isDate(endDateInput.getText()) == false || Utility.daysBetweenDates(LocalDate.parse(startDateInput.getText(), Utility.f),LocalDate.parse(endDateInput.getText(), Utility.f)) <= 0){
                    throw new Exception();
                }
                chosenCar = Utility.checkRental(carChoices.getSelectedItem().toString());
                setLabels();
            } else if(ae.getSource() == clear){
                clearInputs();
                clearLabels();
            } else if(ae.getSource() == createBooking){
                if(Utility.isDate(startDateInput.getText()) == false || Utility.isDate(endDateInput.getText()) == false || Utility.daysBetweenDates(LocalDate.parse(startDateInput.getText(), Utility.f),LocalDate.parse(endDateInput.getText(), Utility.f)) <= 0){
                    throw new Exception();
                }
                chosenCar = Utility.checkRental(carChoices.getSelectedItem().toString());
                int choice = JOptionPane.showConfirmDialog(f, "You are about to book " + chosenCar.getLicensePlate() + " for " + Utility.daysBetweenDates(LocalDate.parse(startDateInput.getText(), Utility.f),LocalDate.parse(endDateInput.getText(), Utility.f)) + " days. Are you sure of your booking details?", "Confirm Booking", JOptionPane.YES_NO_OPTION);
                if(choice == 0){
                    int bookingNo = Utility.allBookings.size() + 50000001;
                    calculateFee(Utility.daysBetweenDates(LocalDate.parse(startDateInput.getText(), Utility.f),LocalDate.parse(endDateInput.getText(), Utility.f)), chosenCar);
                    Booking booking = new Booking(bookingNo, chosenCar, "Awaiting Confirmation", Program.loggedCustomer, 
                            feeAmount, Utility.cd, LocalDate.parse(startDateInput.getText(), Utility.f), LocalDate.parse(endDateInput.getText(), Utility.f));
                    Utility.allBookings.add(booking);
                    Program.loggedCustomer.getMyBookings().add(booking);
                    JOptionPane.showMessageDialog(f, "Your booking has been saved and sent to the admin(s)!");
                    clearInputs();
                    clearLabels();
                } else if(choice == 1){
                    JOptionPane.showMessageDialog(f, "Booking is cancelled");
                    clearInputs();
                    clearLabels();
                }
            }
        }catch(Exception e){
            clearInputs();
            JOptionPane.showMessageDialog(f, "Invalid Input! Try again");
        }
    }

    @Override
    public void focusGained(FocusEvent fe) {
        if(fe.getSource() == startDateInput){
            if(startDateInput.getText().equals(Utility.cd.toString())){
                startDateInput.setText("");
            }
        } else if(fe.getSource() == endDateInput){
            if(endDateInput.getText().equals(Utility.cd.toString())){
                endDateInput.setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent fe) {
        if(fe.getSource() == startDateInput){
            if(startDateInput.getText().equals("")){
                startDateInput.setText(Utility.cd.toString());
            }
        } else if(fe.getSource() == endDateInput){
            if(endDateInput.getText().equals("")){
                endDateInput.setText(Utility.cd.toString());
            }
        }
    }
    
}
