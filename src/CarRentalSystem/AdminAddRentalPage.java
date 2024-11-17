
package CarRentalSystem;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import javax.swing.*;
import java.util.ArrayList;

public class AdminAddRentalPage {
    
    private JFrame f = new JFrame();
    private JPanel p = new JPanel();
    private ArrayList<String> carDetails= new ArrayList<String>();
    
    private JTextField licensePlate_tf = new JTextField();
    private JComboBox carType_tf = new JComboBox();
    private JTextField carColor_tf = new JTextField();
    private JTextField carManufacturer_tf = new JTextField();
    private JTextField carModel_tf = new JTextField();
    private JTextField dateAdded_tf = new JTextField();
    private JTextField fuelCapacity_tf = new JTextField();
    private JTextField modelYear_tf = new JTextField();
    private JTextField mileage_tf = new JTextField();
    private JTextField rate_tf = new JTextField();
    
    private String carTypes[] = {"sedan", "coupe", "hatchback", "suv", "convertible", "minivan"};
    
    AdminAddRentalPage(){
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setTitle("Car Rental - Add Rental");
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
        
        p.setLayout(new GridLayout(14,2));
        
        JLabel licensePlate = new JLabel("License Plate:");
        licensePlate_tf = new JTextField();
        
        JLabel carType = new JLabel("Car Type:");
        carType_tf = new JComboBox(carTypes);
        
        JLabel carColor = new JLabel("Car Color:");
        carColor_tf = new JTextField();
        
        JLabel carManufacturer = new JLabel("Car Manufacturer:");
        carManufacturer_tf = new JTextField();
        
        JLabel carModel = new JLabel("Car Model:");
        carModel_tf = new JTextField();
        
        JLabel dateAdded = new JLabel("Date Added (yyyy-mm-dd):");
        dateAdded_tf = new JTextField();
        
        JLabel fuelCapacity = new JLabel("Fuel Capacity:");
        fuelCapacity_tf = new JTextField();
        
        JLabel modelYear = new JLabel("Model Year:");
        modelYear_tf = new JTextField();
        
        JLabel mileage = new JLabel("Mileage:");
        mileage_tf = new JTextField();
        
        JLabel rate = new JLabel("Rate:");
        rate_tf = new JTextField();
        
        JButton submit = new JButton("Submit");
        JButton clear = new JButton("Clear");
        
        JButton back = new JButton("Back");
        
        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                submitCarDetails();
            }
        });
        
        clear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                clearCarDetails();
            }
        });
        
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.setVisible(false);
                Program.adminRentalPage.getJFrame().setVisible(true);
            }
        });
        
        // Adding to Panel.
        p.add(licensePlate);
        p.add(licensePlate_tf);
        p.add(carType);
        p.add(carType_tf);
        p.add(carColor);
        p.add(carColor_tf);
        p.add(carManufacturer);
        p.add(carManufacturer_tf);
        p.add(carModel);
        p.add(carModel_tf);
        p.add(dateAdded);
        p.add(dateAdded_tf);
        p.add(fuelCapacity);
        p.add(fuelCapacity_tf);
        p.add(modelYear);
        p.add(modelYear_tf);
        p.add(mileage);
        p.add(mileage_tf);
        p.add(rate);
        p.add(rate_tf);
        p.add(new JSeparator());p.add(new JSeparator()); // Adding a horizontal line.
        p.add(submit);
        p.add(clear);
        p.add(new JSeparator());p.add(new JSeparator());
        p.add(back);
        
        
        p.setPreferredSize(new Dimension(570,570));
        f.add(p);
        f.setSize(600,600);
        f.setLocationRelativeTo(null);
        
    }
    
    

    
    public void clearCarDetails(){
        
    licensePlate_tf.setText("");
    carColor_tf.setText("");
    carManufacturer_tf.setText("");
    carModel_tf.setText("");
    dateAdded_tf.setText("");
    fuelCapacity_tf.setText("");
    modelYear_tf.setText("");
    mileage_tf.setText("");
    rate_tf.setText("");
    }
    
        private void submitCarDetails(){
       
            while(true){
                if(Utility.isLicensePlate(licensePlate_tf.getText())){
                    carDetails.add(licensePlate_tf.getText());
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter a valid License Plate Number");
                    break;
                }


                carDetails.add((String)carType_tf.getSelectedItem());

                if(Utility.isName(carColor_tf.getText())){
                    carDetails.add(carColor_tf.getText().toLowerCase());
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter a valid color");
                    break;
                }

                if(Utility.isName(carManufacturer_tf.getText())){
                    carDetails.add(carManufacturer_tf.getText().toLowerCase());
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter a valid manufacturer");
                    break;
                }

                if(Utility.isName(carModel_tf.getText())){
                    carDetails.add(carModel_tf.getText());
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter a valid model (Only letters allowed)");
                    break;
                }

                if(Utility.isDate(dateAdded_tf.getText()) && (LocalDate.parse(dateAdded_tf.getText()).isBefore(LocalDate.now()) || LocalDate.parse(dateAdded_tf.getText()).isEqual(LocalDate.now())) ){
                    carDetails.add(dateAdded_tf.getText());
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter a valid date (It must be today or earlier)");
                    break;
                }

                if(Utility.isInt(fuelCapacity_tf.getText(), 20, 100)){
                    carDetails.add(fuelCapacity_tf.getText());
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter a valid fuel capacity (between 20 to 100)");
                    break;
                }

                if(Utility.isInt(modelYear_tf.getText(), 1950, Utility.getCurrentYear())){
                    carDetails.add(modelYear_tf.getText());
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter a valid model year (between 1950 and current year)");
                    break;
                }

                if(Utility.isInt(mileage_tf.getText(), 3, 60)){
                    carDetails.add(mileage_tf.getText());
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter a valid mileage (between 3 and 60)");
                    break;
                }

                if(Utility.isInt(rate_tf.getText(), 5, 100000)){
                    carDetails.add(rate_tf.getText());
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter a valid rate (between 5 and 100000");
                    break;
                }

                LocalDate date = LocalDate.parse(dateAdded_tf.getText(),Utility.f); 


                if(Utility.checkRental(licensePlate_tf.getText()) != null){
                    JOptionPane.showMessageDialog(null, "Record Already Exists");
                    clearCarDetails();
                    break;
                }else{ 
                    Rental r = new Rental(licensePlate_tf.getText(), (String)carType_tf.getSelectedItem(), carColor_tf.getText(),carManufacturer_tf.getText(),carModel_tf.getText(),date, Integer.parseInt(fuelCapacity_tf.getText()),Integer.parseInt(modelYear_tf.getText()),Integer.parseInt(mileage_tf.getText()),Integer.parseInt(rate_tf.getText()));
                    Utility.allRentals.add(r);
                }
                
                Program.adminRentalPage.refreshRentalTable();
                clearCarDetails();
                f.setVisible(false);
                Program.adminRentalPage.getJFrame().setVisible(true);
                break;
            }
    }
    
    public ArrayList<String> getCarDetails(){    
        return carDetails;
    }
    
    public JFrame getJFrame(){
        return f;
    }
    
}
