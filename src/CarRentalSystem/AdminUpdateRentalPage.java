
package CarRentalSystem;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import javax.swing.*;
import java.util.ArrayList;

public class AdminUpdateRentalPage {
    
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
    
    private String carTypes[] ={"sedan", "coupe", "hatchback", "suv", "convertible", "minivan"};
    public void process(){
        f = new JFrame();
        
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
        
        p = new JPanel();
        f.setTitle("Car Rental - Update Rental");
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        p.setLayout(new GridLayout(14,2));
        String x = AdminRentalPage.idToForward;
        Rental selectedRental = Utility.checkRental(AdminRentalPage.idToForward);
        
        JLabel licensePlate = new JLabel("License Plate:");
        licensePlate_tf = new JTextField();
        licensePlate_tf.setText(selectedRental.getLicensePlate());
        
        JLabel carType = new JLabel("Car Type:");
        carType_tf = new JComboBox(carTypes);
        
        JLabel carColor = new JLabel("Car Color:");
        carColor_tf = new JTextField();
        carColor_tf.setText(selectedRental.getCarColor());

        JLabel carManufacturer = new JLabel("Car Manufacturer:");
        carManufacturer_tf = new JTextField();
        carManufacturer_tf.setText(selectedRental.getCarManufacturer());
        
        JLabel carModel = new JLabel("Car Model:");
        carModel_tf = new JTextField();
        carModel_tf.setText(selectedRental.getCarModel());
        
        JLabel dateAdded = new JLabel("Date Added (yyyy-mm-dd):");
        dateAdded_tf = new JTextField();
        dateAdded_tf.setText(String.valueOf(selectedRental.getDateAdded()));
        
        JLabel fuelCapacity = new JLabel("Fuel Capacity:");
        fuelCapacity_tf = new JTextField();
        fuelCapacity_tf.setText(String.valueOf(selectedRental.getFuelCapacity()));
        
        JLabel modelYear = new JLabel("Model Year:");
        modelYear_tf = new JTextField();
        modelYear_tf.setText(String.valueOf(selectedRental.getModelYear()));
        
        JLabel mileage = new JLabel("Mileage:");
        mileage_tf = new JTextField();
        mileage_tf.setText(String.valueOf(selectedRental.getMileage()));
        
        JLabel rate = new JLabel("Rate:");
        rate_tf = new JTextField();
        rate_tf.setText(String.valueOf(selectedRental.getRate()));
        
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
                clearCarDetails();
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
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        
    }
    
    public void refresh(){
        process();
    }
    
    public void clearCarDetails(){
        
    licensePlate_tf.setText("");
    carColor_tf.setText("");
    carType_tf.setSelectedItem(null);
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
            Rental selectedRental = Utility.checkRental(AdminRentalPage.idToForward);
            
            LocalDate date = LocalDate.parse(dateAdded_tf.getText(),Utility.f);
            
            if(Utility.checkRental(licensePlate_tf.getText()) != null && Utility.checkRental(licensePlate_tf.getText()) != selectedRental){
                JOptionPane .showMessageDialog(null, "Duplicate Admin not allowed");
                break;
                }
            if(Utility.isLicensePlate(licensePlate_tf.getText())){
                    selectedRental.setLicensePlate(licensePlate_tf.getText());
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter a valid License Plate Number");
                    break;
                }

            if(Utility.isName(carColor_tf.getText())){
                selectedRental.setCarColor(carColor_tf.getText().toLowerCase());
            }else{
                JOptionPane.showMessageDialog(null, "Please enter a valid color");
                break;
            }

            if(Utility.isName(carManufacturer_tf.getText())){
                selectedRental.setCarManufacturer(carManufacturer_tf.getText().toLowerCase());
            }else{
                JOptionPane.showMessageDialog(null, "Please enter a valid manufacturer");
                break;
            }

            if(Utility.isName(carModel_tf.getText())){
                selectedRental.setCarModel(carModel_tf.getText());
            }else{
                JOptionPane.showMessageDialog(null, "Please enter a valid model (Only letters allowed)");
                break;
            }

            if(Utility.isDate(dateAdded_tf.getText()) && (LocalDate.parse(dateAdded_tf.getText()).isBefore(LocalDate.now()) || LocalDate.parse(dateAdded_tf.getText()).isEqual(LocalDate.now())) ){
                selectedRental.setDateAdded(date);
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter a valid date (It must be today or earlier)");
                    break;
                }
            
             if(Utility.isInt(fuelCapacity_tf.getText(), 20, 100)){
                 
                selectedRental.setFuelCapacity(Integer.parseInt(fuelCapacity_tf.getText()));
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter a valid fuel capacity (between 20 and 100)");
                    break;
                }

                if(Utility.isInt(modelYear_tf.getText(), 1995, Utility.getCurrentYear())){
            selectedRental.setModelYear(Integer.parseInt(modelYear_tf.getText()));
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter a valid model year (between 1950 and current year)");
                    break;
                }

                if(Utility.isInt(mileage_tf.getText(), 3, 60)){
            selectedRental.setMileage(Integer.parseInt(mileage_tf.getText()));
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter a valid mileage (between 3 and 60)");
                    break;
                }

                if(Utility.isInt(rate_tf.getText(), 5, 100000)){
            selectedRental.setRate(Integer.parseInt(rate_tf.getText()));
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter a valid rate (between 5 and 10000");
                    break;
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
