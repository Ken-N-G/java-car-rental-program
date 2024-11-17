/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CarRentalSystem;

import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.category.DefaultCategoryDataset;


/**
 *
 * @author moham
 */
public class AdminReportPage { 
   
    private JFrame root = new JFrame();
    private ArrayList<Receipt> receipts = Utility.allReceipts;
    private ArrayList<Booking> bookings = Utility.allBookings;
    private ArrayList<Feedback> feedbacks = Utility.allFeedbacks;
    private static DefaultTableModel rentalTableModel;
    private TableRowSorter rowSorter;
    
    // Supporting functions.
    
    private int getIndex(String s[], String val){
        int index = 0;
        
        for(String e: s){
            if(e.equals(val)){
                break;
            }else{
                index++;
            }
        }
        
        return index;
    }
    
    private String[] getAllUsernames(){
        ArrayList<Customer> customerList= Utility.allCustomers;
        
        int index = 0;
        
        String usernames[] = new String[customerList.size()];
        
        for(Customer c: customerList){
            usernames[index] = c.getUsername();
            index++;
        }
        
        return usernames;
    }
    
    private void getReceiptData(){
        Object rowData[] =  new Object[5];
        
        for(Receipt r: Utility.allReceipts){
            rowData[0] = r.getReceiptNo();
            rowData[1] = r.getPaymentType();
            rowData[2] = r.getBooking().getBookingNo();
            rowData[3] = r.getCustomer().getUsername();
            rowData[4] = r.getDatePaid();
            rentalTableModel.addRow(rowData);
            
        }
    }
    
    private void getBookingData(){
        Object rowData[] =  new Object[6];
        
        for(Booking b: Utility.allBookings){
            rowData[0] = b.getBookingNo();
            rowData[1] = b.getCar().getLicensePlate();
            rowData[2] = b.getCar().getCarManufacturer() + " " + b.getCar().getCarModel();
            rowData[3] = b.getStatus();
            rowData[4] = b.getStartDate();
            rowData[5] = b.getEndDate();
            rentalTableModel.addRow(rowData);
            
        }
    }
    
    private int countRentalByTypeAndGender(String type, char gender){
        int count = 0;
        for(Booking b: Utility.allBookings){
                    if(b.getCustomer().getGender() == gender && b.getCar().getCarType().equals(type) && (b.getStatus().equals("Accepted") || b.getStatus().equals("Ongoing") || b.getStatus().equals("Completed"))){
                        count++;
                    }
                }
        return count;
    }

    private int getAgeFromDOB(LocalDate date){
    int age = Utility.getCurrentYear() - date.getYear();
    return age;
}
    
    private int countRentalByTypeAndAge(String type, int age1, int age2){
        int count = 0;
        for(Booking b: Utility.allBookings){
            if((getAgeFromDOB(b.getCustomer().getDob()) >= age1 && getAgeFromDOB(b.getCustomer().getDob()) <= age2) && b.getCar().getCarType().equals(type) && (b.getStatus().equals("Accepted") || b.getStatus().equals("Ongoing") || b.getStatus().equals("Completed"))){
                count++;
                    }
                }
        return count;
    }
    
    private int countRentalByPaymentAndGender(String payment, char gender){
        int count = 0;
        for(Receipt r: Utility.allReceipts){
            if(r.getPaymentType().equals(payment) && r.getCustomer().getGender() == gender){
                count++;
            }
        }
        return count;
    }

    private int countRentalByPaymentAndAge(String payment, int age1, int age2){
        int count = 0;
        for(Receipt r: Utility.allReceipts){
            if((getAgeFromDOB(r.getCustomer().getDob()) >= age1 && getAgeFromDOB(r.getCustomer().getDob()) <= age2) && r.getPaymentType().equals(payment)){
                count++;
                    }
                }
        return count;
    }

// Non-supporting functions.

    private JPanel getTypeAnalysisByGenderReport(){        
        int countSedanMale = countRentalByTypeAndGender("sedan", 'M');
        int countCoupeMale = countRentalByTypeAndGender("coupe", 'M');
        int countHatchbackMale = countRentalByTypeAndGender("hatchback", 'M');
        int countSuvMale = countRentalByTypeAndGender("suv", 'M');
        int countConvertibleMale = countRentalByTypeAndGender("convertible", 'M');
        int countMinivanMale = countRentalByTypeAndGender("minivan", 'M');

        int countSedanFemale = countRentalByTypeAndGender("sedan", 'F');
        int countCoupeFemale = countRentalByTypeAndGender("coupe", 'F');
        int countHatchbackFemale = countRentalByTypeAndGender("hatchback", 'F');
        int countSuvFemale = countRentalByTypeAndGender("suv", 'F');
        int countConvertibleFemale = countRentalByTypeAndGender("convertible", 'F');
        int countMinivanFemale = countRentalByTypeAndGender("minivan", 'F');
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        dataset.addValue(countSedanMale, "M", "sedan");
        dataset.addValue(countCoupeMale, "M", "coupe");
        dataset.addValue(countHatchbackMale, "M", "hatchback");
        dataset.addValue(countSuvMale, "M", "suv");
        dataset.addValue(countConvertibleMale, "M", "convertible");
        dataset.addValue(countMinivanMale, "M", "minivan");
        
        dataset.addValue(countSedanFemale, "F", "sedan");
        dataset.addValue(countCoupeFemale, "F", "coupe");
        dataset.addValue(countHatchbackFemale, "F", "hatchback");
        dataset.addValue(countSuvFemale, "F", "suv");
        dataset.addValue(countConvertibleFemale, "F", "convertible");
        dataset.addValue(countMinivanFemale, "F", "minivan");
        
        CategoryDataset mainDataset = dataset;
        
        JFreeChart chart = ChartFactory.createBarChart("Car Type Analysis By Gender", "Gender", "Car Type", mainDataset);
        
        ChartPanel p = new ChartPanel(chart);
        return p;
    }
    
    private JPanel getTypeAnalysisByAgeReport(){        
        int countSedan1834 = countRentalByTypeAndAge("sedan", 18, 34);
        int countCoupe1834 = countRentalByTypeAndAge("coupe", 18,34);
        int countHatchback1834 = countRentalByTypeAndAge("hatchback", 18,34);
        int countSuv1834 = countRentalByTypeAndAge("suv", 18,34);
        int countConvertible1834 = countRentalByTypeAndAge("convertible", 18,34);
        int countMinivan1834 = countRentalByTypeAndAge("minivan", 18,34);
        
        int countSedan3554 = countRentalByTypeAndAge("sedan", 35, 54);
        int countCoupe3554 = countRentalByTypeAndAge("coupe", 35,54);
        int countHatchback3554 = countRentalByTypeAndAge("hatchback", 35,54);
        int countSuv3554 = countRentalByTypeAndAge("suv", 35,54);
        int countConvertible3554 = countRentalByTypeAndAge("convertible", 35,54);
        int countMinivan3554= countRentalByTypeAndAge("minivan", 35,54);
        
        int countSedan5574 = countRentalByTypeAndAge("sedan", 55, 74);
        int countCoupe5574 = countRentalByTypeAndAge("coupe",  55, 74);
        int countHatchback5574 = countRentalByTypeAndAge("hatchback",  55, 74);
        int countSuv5574 = countRentalByTypeAndAge("suv",  55, 74);
        int countConvertible5574 = countRentalByTypeAndAge("convertible", 55, 74);
        int countMinivan5574 = countRentalByTypeAndAge("minivan", 55,74);
        
        int countSedan7594 = countRentalByTypeAndAge("sedan", 75, 94);
        int countCoupe7594 = countRentalByTypeAndAge("coupe",  75, 94);
        int countHatchback7594 = countRentalByTypeAndAge("hatchback",  75, 94);
        int countSuv7594 = countRentalByTypeAndAge("suv",  75, 94);
        int countConvertible7594 = countRentalByTypeAndAge("convertible", 75, 94);
        int countMinivan7594 = countRentalByTypeAndAge("minivan", 75,94);
        
        int countSedan100 = countRentalByTypeAndAge("sedan", 100, 150);
        int countCoupe100 = countRentalByTypeAndAge("coupe",  100,150);
        int countHatchback100 = countRentalByTypeAndAge("hatchback",  100,150);
        int countSuv100 = countRentalByTypeAndAge("suv",  100,150);
        int countConvertible100 = countRentalByTypeAndAge("convertible", 100,150);
        int countMinivan100 = countRentalByTypeAndAge("minivan", 100,150);
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        dataset.addValue(countSedan1834, "18 - 34", "sedan");
        dataset.addValue(countCoupe1834, "18 - 34", "coupe");
        dataset.addValue(countHatchback1834, "18 - 34", "hatchback");
        dataset.addValue(countSuv1834, "18 - 34", "suv");
        dataset.addValue(countConvertible1834, "18 - 34", "convertible");
        dataset.addValue(countMinivan1834, "18 - 34", "minivan");
        
        dataset.addValue(countSedan3554, "35 - 54", "sedan");
        dataset.addValue(countCoupe3554, "35 - 54", "coupe");
        dataset.addValue(countHatchback3554, "35 - 54", "hatchback");
        dataset.addValue(countSuv3554, "35 - 54", "suv");
        dataset.addValue(countConvertible3554, "35 - 54", "convertible");
        dataset.addValue(countMinivan3554, "35 - 54", "minivan");
        
        dataset.addValue(countSedan5574, "55 - 74", "sedan");
        dataset.addValue(countCoupe5574, "55 - 74", "coupe");
        dataset.addValue(countHatchback5574, "55 - 74", "hatchback");
        dataset.addValue(countSuv5574, "55 - 74", "suv");
        dataset.addValue(countConvertible5574, "55 - 74", "convertible");
        dataset.addValue(countMinivan5574, "55 - 74", "minivan");
        
        dataset.addValue(countSedan7594, "75 - 94", "sedan");
        dataset.addValue(countCoupe7594, "75 - 94", "coupe");
        dataset.addValue(countHatchback7594, "75 - 94", "hatchback");
        dataset.addValue(countSuv7594, "75 - 94", "suv");
        dataset.addValue(countConvertible7594, "75 - 94", "convertible");
        dataset.addValue(countMinivan7594, "75 - 94", "minivan");
        
        dataset.addValue(countSedan100, "100 - 150", "sedan");
        dataset.addValue(countCoupe100, "100 - 150", "coupe");
        dataset.addValue(countHatchback100, "100 - 150", "hatchback");
        dataset.addValue(countSuv100, "100 - 150", "suv");
        dataset.addValue(countConvertible100, "100 - 150", "convertible");
        dataset.addValue(countMinivan100, "100 - 150", "minivan");
        
        CategoryDataset mainDataset = dataset;
        
        JFreeChart chart = ChartFactory.createBarChart("Car Type Analysis By Age", "Age", "Car Type", mainDataset);
        
        ChartPanel p = new ChartPanel(chart);
        return p;
    }
    
    private JPanel getPaymentAnalysisByGenderReport(){        
    
        int countCardMale = countRentalByPaymentAndGender("Card", 'M');
        int countEWalletMale = countRentalByPaymentAndGender("E-Wallet", 'M');
        int countBankTransferMale = countRentalByPaymentAndGender("Bank Transfer", 'M');
        
        
        int countCardFemale = countRentalByPaymentAndGender("Card", 'F');
        int countEWalletFemale = countRentalByPaymentAndGender("E-Wallet", 'F');
        int countBankTransferFemale = countRentalByPaymentAndGender("Bank Transfer", 'F');

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        dataset.addValue(countCardMale, "M", "Card");
        dataset.addValue(countEWalletMale, "M", "E-Wallet");
        dataset.addValue(countBankTransferMale, "M", "Bank Transfer");
        
        dataset.addValue(countCardFemale, "F", "Card");
        dataset.addValue(countEWalletFemale, "F", "E-Wallet");
        dataset.addValue(countBankTransferFemale, "F", "Bank Transfer");
        
        CategoryDataset mainDataset = dataset;
        
        JFreeChart chart = ChartFactory.createBarChart("Payment Analysis By Gender", "Gender", "Payment Type", mainDataset);
        
        ChartPanel p = new ChartPanel(chart);
        return p;
    }

    private JPanel getPaymentAnalysisByAgeReport(){        
        int countCard1834 = countRentalByPaymentAndAge("Card", 18, 34);
        int countEWallet1834 = countRentalByPaymentAndAge("E-Wallet", 18,34);
        int countBankTransfer1834 = countRentalByPaymentAndAge("Bank Transfer", 18,34);
        
        int countCard3554 = countRentalByPaymentAndAge("Card", 35, 54);
        int countEWallet3554 = countRentalByPaymentAndAge("E-Wallet", 35,54);
        int countBankTransfer3554 = countRentalByPaymentAndAge("Bank Transfer", 35,54);
       
        int countCard5574 = countRentalByPaymentAndAge("Card", 55, 74);
        int countEWallet5574 = countRentalByPaymentAndAge("E-Wallet",  55, 74);
        int countBankTransfer5574 = countRentalByPaymentAndAge("Bank Transfer",  55, 74);
        
        int countCard7594 = countRentalByPaymentAndAge("Card", 75, 94);
        int countEWallet7594 = countRentalByPaymentAndAge("E-Wallet",  75, 94);
        int countBankTransfer7594 = countRentalByPaymentAndAge("Bank Transfer",  75, 94);
        
        int countCard100 = countRentalByPaymentAndAge("Card", 100, 150);
        int countEWallet100 = countRentalByPaymentAndAge("E-Wallet",  100,150);
        int countBankTransfer100 = countRentalByPaymentAndAge("Bank Transfer",  100,150);

        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        dataset.addValue(countCard1834, "18 - 34", "sedan");
        dataset.addValue(countEWallet1834, "18 - 34", "coupe");
        dataset.addValue(countBankTransfer1834, "18 - 34", "hatchback");
     
        
        dataset.addValue(countCard3554, "35 - 54", "sedan");
        dataset.addValue(countEWallet3554, "35 - 54", "coupe");
        dataset.addValue(countBankTransfer3554, "35 - 54", "hatchback");

        
        dataset.addValue(countCard5574, "55 - 74", "sedan");
        dataset.addValue(countEWallet5574, "55 - 74", "coupe");
        dataset.addValue(countBankTransfer5574, "55 - 74", "hatchback");

        
        dataset.addValue(countCard7594, "75 - 94", "sedan");
        dataset.addValue(countEWallet7594, "75 - 94", "coupe");
        dataset.addValue(countBankTransfer7594, "75 - 94", "hatchback");
     
        dataset.addValue(countCard100, "100 - 150", "sedan");
        dataset.addValue(countEWallet100, "100 - 150", "coupe");
        dataset.addValue(countBankTransfer100, "100 - 150", "hatchback");
        
        CategoryDataset mainDataset = dataset;
        
        JFreeChart chart = ChartFactory.createBarChart("Car PaymentAnalysis By Age", "Age", "PaymentType", mainDataset);
        
        ChartPanel p = new ChartPanel(chart);
        return p;
    }
   
   
    
    AdminReportPage(){
        root.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        root.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmChoice = JOptionPane.showConfirmDialog(root, "Are you sure you want to exit now? Any changes that are not updated will not be saved!", "Close Program", JOptionPane.YES_NO_OPTION);
                if(confirmChoice == 0){
                    Utility.writeToFile();
                    System.exit(0);
                }
            }
        }
        );
        
        root.setResizable(false);
        root.setSize(600,600);
        root.setLocationRelativeTo(null);
    }
    
    private JPanel getSalesReport(){
        
        root.setTitle("Car Rental - Sales Report");
        JPanel p = new JPanel();
        
        String colNames[] = {"Receipt ID", "Payment Type", "Booking ID", "Booking Status", "Date of Payment"};
        
        JTable table = new JTable();
        rentalTableModel = new DefaultTableModel(colNames, 0); 
        rowSorter = new TableRowSorter(rentalTableModel);
        table.setModel(rentalTableModel);
        table.setRowSorter(rowSorter);
        
        getReceiptData();
        
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setEnabled(false);
        table.setRowHeight(30);

        JScrollPane sp = new JScrollPane(table);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setPreferredSize(new Dimension(580,360));

        
        p.add(sp);
        
        return p;
    }
    
    private JPanel getBookingHistoryReport(String username){
        
        root.setTitle("Car Rental - Booking History Report");
        JPanel p = new JPanel();

        String colNames[] = {"Booking ID", "License Plate", "Car Name",  "Booking Status", "Start Date", "End Date"};

        JTable table = new JTable();
        rentalTableModel = new DefaultTableModel(colNames, 0); 
        rowSorter = new TableRowSorter(rentalTableModel);
        table.setModel(rentalTableModel);
        table.setRowSorter(rowSorter);
        
        getBookingData();
        
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setEnabled(false);
        table.setRowHeight(30);

        JScrollPane sp = new JScrollPane(table);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setPreferredSize(new Dimension(580,300));

        p.add(sp);

        
        return p;
    }
    
    public JPanel getFeedbackReport(){
        JPanel p = new JPanel();
        
        String subjectList[] = {"Payment", "Booking" , "Rental", "Application", "Service", "Feature Addition", "Other"};
        String avgRatings[] = {"0","0","0","0","0","0","0"};
        String noOfCustomers[] = {"0","0","0","0","0","0","0"};
        
        int ratingSum = 0;
        int customerCount = 0;
        int index = 0;
        
        for(String subject: subjectList){
            for(Feedback f: feedbacks){
                if(f.getSubjectMatter().equals(subject)){
                    ratingSum += f.getRating();
                    customerCount++;
                }
            }
            avgRatings[index] = String.valueOf(((float)ratingSum / (float)customerCount));
            noOfCustomers[index] = String.valueOf(customerCount);
            index++;
            
            ratingSum = 0;
            customerCount = 0;
        }
        
        
        // Creating the interface.
        String collection[][] = {subjectList, avgRatings, noOfCustomers};
        
        String[][] data = new String[7][3];
        String cols[] = {"Subject Matter", "Avg. Rating", "No. of Reviews"};
        // 
        
        JTable table = new JTable();
        rentalTableModel = new DefaultTableModel(cols, 0); 
        rowSorter = new TableRowSorter(rentalTableModel);
        table.setModel(rentalTableModel);
        table.setRowSorter(rowSorter);
        
        
        for(int k = 0; k < 7; k++){
            data[k][0] = collection[0][k];
            data[k][1] = collection[1][k];
            data[k][2] = collection[2][k];
            rentalTableModel.addRow(data[k]);
        }
        
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setEnabled(false);
        table.setRowHeight(30);
        
        JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        p.add(sp);
        
        
        return p;
    }
    
    private ArrayList<JPanel> getAllBookingHistoryReportPanels(String[] usernames){ // Remember to put username as the argument.
        ArrayList<JPanel> retList = new ArrayList<JPanel>();
        
        for(String s: usernames){
            retList.add(getBookingHistoryReport(s));
        }
        
        return retList;
    }
    
    private JPanel getAllBookingHistoryReports(ArrayList<JPanel> reports, String[] usernames){
        
        JPanel p = new JPanel();
        p.setLayout(null);
        
        JPanel p1 = new JPanel();
        p1.setBounds(0,0,570,25);
        
        JTextField tf = new JTextField();
        tf.setPreferredSize(new Dimension(100,20));
        
        JButton search = new JButton("Search Booking by Customer ID");
        search.setPreferredSize(new Dimension(250,20));
        
        JPanel p2 = new JPanel();
        p2.setBounds(0,25,570,400);
        
        // Adding functionality to the button.
        search.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String searchResult = tf.getText().toString();
                
                for(String str: usernames){
                    if(str.equals(searchResult)){
                        p2.removeAll();
                        
                        int index = getIndex(usernames, str);
                        p2.add(reports.get(index), BorderLayout.NORTH);
                        
                        p.invalidate();
                        p.validate();
                        p.repaint();
                        break;
                    }else{
                        JOptionPane.showMessageDialog(null, "Enter a Valid Customer ID.");
                        break;
                    }
                }
            }
        });
        
        p.add(p1); p1.add(tf);p1.add(search); p.add(p2);
        
        return p;
    }
    
    private JPanel getReportMenu(){
        root.setTitle("Car Rental - Reports");
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4,2,15,15));
        
        
        JButton report1 = new JButton("Sales Report");
        report1.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        final JFrame f = new JFrame("Reports - Sales Report");
                        f.setResizable(false);
                        f.add(getSalesReport());
                        
                        f.setSize(600,420);
                        f.setVisible(true);
                    }
            });
        
        p.add(report1);
        
        JButton report2 = new JButton("Booking History Report");
        
        report2.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        JFrame f = new JFrame("Reports - Booking History Report");
                        f.setResizable(false);
                        f.add(getAllBookingHistoryReports(getAllBookingHistoryReportPanels(getAllUsernames()), getAllUsernames()));
                        
                        f.setSize(600,420);
                        f.setVisible(true);
                        
                        
                        
                    }
            });
        
        p.add(report2);
        
        JButton report3 = new JButton("Feedback Report");
        report3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFrame f = new JFrame("Reports - Feedback Report");
                f.setResizable(false);
                
                f.add(getFeedbackReport());
                
                f.setSize(600,420);
                f.setVisible(true);
            }
        });
        
        p.add(report3);
        
        
            JButton report4 = new JButton("Car Type Analysis By Gender" );
    report4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFrame f = new JFrame("Reports - Car Type Analysis By Gender");
                f.setResizable(false);
                f.add(getTypeAnalysisByGenderReport());
                
                f.setSize(600,420);
                f.setVisible(true);
            }
        });
    
        p.add(report4);
        
        JButton report5 = new JButton("Car Type Analysis By Age" );
        report5.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JFrame f = new JFrame("Reports - Car Type Analysis By Age");
                    f.setResizable(false);

                    f.add(getTypeAnalysisByAgeReport());

                    f.setSize(600,420);
                    f.setVisible(true);
                }
            });

        p.add(report5);
        
        JButton report6 = new JButton("Payment Type Analysis By Gender" );
        report6.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JFrame f = new JFrame("Reports - Payment Type Analysis By Gender");
                    f.setResizable(false);

                    f.add(getPaymentAnalysisByGenderReport());

                    f.setSize(600,420);
                    f.setVisible(true);
                }
            });

        p.add(report6);
    
        JButton report7 = new JButton("Payment Type Analysis By Age" );
        report7.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JFrame f = new JFrame("Reports - Payment Type Analysis By Age");
                    f.setResizable(false);

                    f.add(getPaymentAnalysisByAgeReport());

                    f.setSize(600,420);
                    f.setVisible(true);
                }
            });

        p.add(report7);
        
        return p;
    }
    

    
    public JFrame getJFrame(){
        
       JPanel panel = getReportMenu();
       panel.setBounds(0,0,580,360);
       
       JButton back = new JButton("Back");
       back.setBounds(10,380, 100,30);
       back.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               root.setVisible(false);
               Program.adminHomepage.getJFrame().setVisible(true);
           }
       });
       
       root.add(panel);
       root.add(back);
       
       root.setLayout(null);
       return root;
   }    
           
}
