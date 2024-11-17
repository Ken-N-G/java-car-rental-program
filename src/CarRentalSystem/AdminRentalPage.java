package CarRentalSystem;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

public class AdminRentalPage implements ActionListener, FocusListener, MouseListener{
    private JFrame f;
    private JPanel p1, p2;
    private JButton addbtn, back, delbtn, updbtn;
    private JTextField searchInput;
    private static JTable table;
    private static DefaultTableModel rentalTableModel;
    private JScrollPane scrollPane;
    private TableRowSorter rowSorter;
    
    public static String idToForward; // A variable made to be used in the AdminUpdateRentalPage.

    
    public void refreshRentalTable(){
        
         rentalTableModel.setRowCount(0);
           
           Object[] rowData = new Object[10];
           ArrayList<Rental> rentalList = Utility.allRentals;
        for(int k = 0; k < rentalList.size(); k++){
            Rental rental = rentalList.get(k);
            rowData[0] = rental.getLicensePlate();
            rowData[1] = rental.getCarType();
            rowData[2] = rental.getCarColor();
            rowData[3] = rental.getCarManufacturer();
            rowData[4] = rental.getCarModel();
            rowData[5] = rental.getDateAdded();
            rowData[6] = rental.getFuelCapacity();
            rowData[7] = rental.getModelYear();
            rowData[8] = rental.getMileage();
            rowData[9] = rental.getRate();
            rentalTableModel.addRow(rowData);
        }
            
            rentalTableModel = (DefaultTableModel) table.getModel();
            
            rentalTableModel.fireTableDataChanged();
            updbtn.setEnabled(false);
    }
    
    public AdminRentalPage(){
        String[] columnNames = {"License Plate No..", "Type", "Color", "Manufacturer", "Model", "Date Added", "Fuel Cap.", "Model Year", "Mileage", "Rate"};
        
        f = new JFrame("Car Rental - Rentals Page");
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
        
        JLabel title = new JLabel("Rentals Page");
        title.setBounds(240, 15, 120, 20);
        
        addbtn = new JButton("Add");
        back = new JButton("Back");
        delbtn = new JButton("Delete");
        updbtn = new JButton("Update");
        back.setBounds(30, 400, 90, 30);
        delbtn.setBounds(210, 270, 150, 30);
        addbtn.setBounds(30, 270, 150, 30);
        updbtn.setBounds(390, 270, 150, 30);
        back.addActionListener(this);
        delbtn.addActionListener(this);
        addbtn.addActionListener(this);
        updbtn.addActionListener(this);
        
        addbtn.setEnabled(true);
        delbtn.setEnabled(false);
        updbtn.setEnabled(false);
        
        searchInput = new JTextField("Search Rentals...");
        searchInput.addActionListener(this);
        searchInput.addFocusListener(this);
        searchInput.setBounds(30, 0, 540, 30);
        
        table = new JTable();
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
                        return String.class;
                    case 4:
                        return String.class;
                    case 5:
                        return LocalDate.class;
                    case 6:
                        return Integer.class;
                    case 7:
                        return Integer.class;
                    case 8:
                        return Integer.class;
                    case 9:
                        return Integer.class;
                    default:
                        throw new IllegalArgumentException("Invalid column accessed!");
                }
            }
            
        };
        rowSorter = new TableRowSorter(rentalTableModel);
        table.setModel(rentalTableModel);
        table.setRowSorter(rowSorter);
        addDataToJTable();
        setJTable();
        table.addMouseListener(this);
        
        scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        table.setFillsViewportHeight(true);
        scrollPane.setBounds(30, 50, 540, 200);
        
        
        
        p1.setLayout(null);
        p1.setBounds(0, 100, 600, 500);
        p1.add(searchInput);
        p1.add(scrollPane);
        p1.add(addbtn);
        p1.add(back);
        p1.add(delbtn);
        p1.add(updbtn);
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
        for(Rental rental : Utility.allRentals){
            rowData[0] = rental.getLicensePlate();
            rowData[1] = rental.getCarType();
            rowData[2] = rental.getCarColor();
            rowData[3] = rental.getCarManufacturer();
            rowData[4] = rental.getCarModel();
            rowData[5] = rental.getDateAdded();
            rowData[6] = rental.getFuelCapacity();
            rowData[7] = rental.getModelYear();
            rowData[8] = rental.getMileage();
            rowData[9] = rental.getRate();
            rentalTableModel.addRow(rowData);
        }
    }
    
    public void setJTable(){
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.getColumnModel().setColumnSelectionAllowed(false);
        for(int x = 0;x < 9;x++){
            table.getColumnModel().getColumn(x).setPreferredWidth(110);
        }
    }
    
    public void clearInputs(){
        table.clearSelection();
        delbtn.setEnabled(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == back){
            clearInputs();
            f.setVisible(false);
            Program.adminHomepage.getJFrame().setVisible(true);
            
        } else if(ae.getSource() == addbtn){
            f.setVisible(false);
            Program.adminAddRentalPage.getJFrame().setVisible(true);
            
            
        } else if(ae.getSource() == searchInput){
            rowSorter.setRowFilter(RowFilter.regexFilter(searchInput.getText()));
        } else if(ae.getSource() == delbtn){
            String tempId = String.valueOf(table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()),0));
            Utility.deleteRental(tempId);
            
          refreshRentalTable();
            
            }else if(ae.getSource() == updbtn){
                idToForward = String.valueOf(table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()),0));
                f.setVisible(false);
                Program.adminUpdateRentalPage.refresh();
                Program.adminUpdateRentalPage.getJFrame().setVisible(true);
                
                
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
        if(table.getSelectedRowCount() == 1){
            addbtn.setEnabled(true);
            delbtn.setEnabled(true);
            updbtn.setEnabled(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(table.getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(f,"Please select one car");
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(table.getSelectedRowCount() > 1){
            JOptionPane.showMessageDialog(f,"Please select one car");
            addbtn.setEnabled(false);
            table.clearSelection();
        } else if(table.getSelectedRow() == 1){
            addbtn.setEnabled(true);
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
