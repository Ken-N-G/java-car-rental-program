package CarRentalSystem;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class CustomerRentalsPage implements ActionListener, FocusListener, MouseListener{
    private JFrame f;
    private JPanel p1, p2;
    private JButton createBookingFromSelection, back, toCreateBooking;
    private JTextField searchInput;
    private JTable rentals;
    private DefaultTableModel rentalTableModel;
    private JScrollPane scrollPane;
    private TableRowSorter rowSorter;

    
    public CustomerRentalsPage(){
        String[] columnNames = {"Plate No.", "Manufacturer", "Model", "Model Year", "Type", "Color", "Rate (per day)", "Mileage (km)", "Fuel Capacity (L)", "Date Added"};
        
        f = new JFrame("Car Rental - Customer Rental Page");
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
        
        p1 = new JPanel();
        p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        
        JLabel title = new JLabel("Customer Rental Page");
        title.setBounds(240, 15, 120, 20);
        
        createBookingFromSelection = new JButton("Create a Booking from this selection");
        back = new JButton("Back");
        toCreateBooking = new JButton("Create a booking");
        back.setBounds(30, 400, 90, 30);
        toCreateBooking.setBounds(440, 400, 130, 30);
        createBookingFromSelection.setBounds(30, 270, 540, 30);
        createBookingFromSelection.setEnabled(false);
        back.addActionListener(this);
        toCreateBooking.addActionListener(this);
        createBookingFromSelection.addActionListener(this);
        
        searchInput = new JTextField("Search Rentals...");
        searchInput.addActionListener(this);
        searchInput.addFocusListener(this);
        searchInput.setBounds(30, 0, 540, 30);
        
        rentals = new JTable();
        rentalTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
            
            @Override
            public Class getColumnClass(int column){
                switch(column){
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return Integer.class;
                    case 4:
                        return String.class;
                    case 5:
                        return String.class;
                    case 6:
                        return Integer.class;
                    case 7:
                        return Integer.class;
                    case 8:
                        return Integer.class;
                    case 9:
                        return LocalDate.class;
                    default:
                        throw new IllegalArgumentException("Invalid column accessed!");
                }
            }
        };
        rowSorter = new TableRowSorter(rentalTableModel);
        rentals.setModel(rentalTableModel);
        rentals.setRowSorter(rowSorter);
        addDataToJTable();
        setJTable();
        rentals.addMouseListener(this);
        
        scrollPane = new JScrollPane(rentals, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        rentals.setFillsViewportHeight(true);
        scrollPane.setBounds(30, 50, 540, 200);
        
        p1.setLayout(null);
        p1.setBounds(0, 100, 600, 500);
        p1.add(searchInput);
        p1.add(scrollPane);
        p1.add(createBookingFromSelection);
        p1.add(back);
        p1.add(toCreateBooking);
        p2.setBounds(30, 0, 540, 100);
        p2.add(title);
        f.add(p1);
        f.add(p2);
        
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setSize(600, 600);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
    }
    
    public JFrame getJFrame(){
        return f;
    }
    
    public void addDataToJTable(){
        Object[] rowData = new Object[10];
        for(Rental r : Utility.allRentals){
            rowData[0] = r.getLicensePlate();
            rowData[1] = r.getCarManufacturer();
            rowData[2] = r.getCarModel();
            rowData[3] = r.getModelYear();
            rowData[4] = r.getCarType();
            rowData[5] = r.getCarColor();
            rowData[6] = r.getRate();
            rowData[7] = r.getMileage();
            rowData[8] = r.getFuelCapacity();
            rowData[9] = r.getDateAdded();
            rentalTableModel.addRow(rowData);
        }
    }
    
    public void setJTable(){
        rentals.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        rentals.getTableHeader().setReorderingAllowed(false);
        rentals.getTableHeader().setResizingAllowed(false);
        rentals.getColumnModel().setColumnSelectionAllowed(false);
        for(int x = 0;x < 10;x++){
            rentals.getColumnModel().getColumn(x).setPreferredWidth(110);
        }
    }
    
    public void clearInputs(){
        rentals.clearSelection();
        createBookingFromSelection.setEnabled(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == back){
            clearInputs();
            f.setVisible(false);
            Program.customerBookingHomepage.getJFrame().setVisible(true);
        } else if(ae.getSource() == createBookingFromSelection){
            Rental chosenCar = Utility.checkRental(rentals.getModel().getValueAt(rentals.convertRowIndexToModel(rentals.getSelectedRow()),0).toString());
            Program.customerCreateBookingPage.setChosenCar(chosenCar);
            Program.customerCreateBookingPage.getCarChoices().setSelectedItem(chosenCar.getLicensePlate());
            rentals.clearSelection();
            f.setVisible(false);
            Program.customerCreateBookingPage.getJFrame().setVisible(true);
            createBookingFromSelection.setEnabled(false);
        } else if(ae.getSource() == searchInput){
            rowSorter.setRowFilter(RowFilter.regexFilter(searchInput.getText()));
        } else if(ae.getSource() == toCreateBooking){
            f.setVisible(false);
            Program.customerCreateBookingPage.getJFrame().setVisible(true);
        }
    }

    @Override
    public void focusGained(FocusEvent fe) {
        if(searchInput.getText().equals("Search Rentals...")){
            searchInput.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent fe) {
        if(searchInput.getText().equals("")){
            searchInput.setText("Search Rentals...");
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(rentals.getSelectedRowCount() == 1){
            createBookingFromSelection.setEnabled(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(rentals.getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(f,"Please select one car to rent");
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(rentals.getSelectedRowCount() > 1){
            JOptionPane.showMessageDialog(f,"Please select one car to rent");
            createBookingFromSelection.setEnabled(false);
            rentals.clearSelection();
        } else if(rentals.getSelectedRow() == 1){
            createBookingFromSelection.setEnabled(true);
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
