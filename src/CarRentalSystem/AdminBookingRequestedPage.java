package CarRentalSystem;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.*;


public class AdminBookingRequestedPage implements ActionListener, FocusListener, MouseListener{
    private JFrame f;
    private JPanel p1, p2;
    private JButton accept, back, reject;
    private JTextField searchInput;
    private JTable table;
    private DefaultTableModel bookingTableModel;
    private JScrollPane scrollPane;
    private TableRowSorter rowSorter;

        public static void rejectBooking(int bookingno){
        
        for(Booking booking: Utility.allBookings){
            if(booking.getBookingNo() == bookingno){
                booking.setStatus("Rejected");
            }
        }
    }
    
        public static void acceptBooking(int bookingno){
        
        for(Booking booking: Utility.allBookings){
            if(booking.getBookingNo() == bookingno){
                booking.setStatus("Awaiting Payment");
            }
        }
    }
    
    public void refreshBookingTable(){
        bookingTableModel.setRowCount(0);
           
           Object[] rowData = new Object[6];
        for(Booking booking : Utility.allBookings){
                if(booking.getStatus().equals("Awaiting Confirmation")){
                rowData[0] = booking.getBookingNo();
                rowData[1] = booking.getStatus();
                rowData[2] = booking.getCustomer().getUsername();
                rowData[3] = booking.getDateBooked();
                rowData[4] = booking.getStartDate();
                rowData[5] = booking.getEndDate();
                bookingTableModel.addRow(rowData);
            }
        }
            
            bookingTableModel = (DefaultTableModel) table.getModel();
            
            bookingTableModel.fireTableDataChanged();
            
            accept.setEnabled(false);
            reject.setEnabled(false);
    }
    
    public AdminBookingRequestedPage(){
        String[] columnNames = {"Booking No.", "Status", "Customer Username", "Date Booked", "Start Date", "End Date"};
        
        f = new JFrame("Car Rental - Booking Requests Page");
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
        
        JLabel title = new JLabel("Booking Requests Page");
        title.setBounds(240, 15, 120, 20);
        
        accept = new JButton("Accept");
        back = new JButton("Back");
        reject = new JButton("Reject");
        back.setBounds(30, 400, 90, 30);
        reject.setBounds(210, 270, 150, 30);
        accept.setBounds(30, 270, 150, 30);
        back.addActionListener(this);
        reject.addActionListener(this);
        accept.addActionListener(this);
        
        accept.setEnabled(false);
        reject.setEnabled(false);
        
        searchInput = new JTextField("Search Bookings...");
        searchInput.addActionListener(this);
        searchInput.addFocusListener(this);
        searchInput.setBounds(30, 0, 540, 30);
        
        table = new JTable();
        bookingTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
            
            @Override
            public Class getColumnClass(int column){
                switch(column){
                    case 0:
                        return Integer.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return LocalDate.class;
                    case 4:
                        return LocalDate.class;
                    case 5:
                        return LocalDate.class;
                    default:
                        throw new IllegalArgumentException("Invalid column accessed!");
                }
            }
            
        };
        rowSorter = new TableRowSorter(bookingTableModel);
        table.setModel(bookingTableModel);
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
        p1.add(accept);
        p1.add(back);
        p1.add(reject);
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
        Object[] rowData = new Object[6];
        for(Booking booking : Utility.allBookings){
                if(booking.getStatus().equals("Awaiting Confirmation")){
                rowData[0] = booking.getBookingNo();
                rowData[1] = booking.getStatus();
                rowData[2] = booking.getCustomer().getUsername();
                rowData[3] = booking.getDateBooked();
                rowData[4] = booking.getStartDate();
                rowData[5] = booking.getEndDate();
                bookingTableModel.addRow(rowData);
            }
        }
    }
    
    public void setJTable(){
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.getColumnModel().setColumnSelectionAllowed(false);
        for(int x = 0;x < 6;x++){
            table.getColumnModel().getColumn(x).setPreferredWidth(110);
        }
    }
    
    public void clearInputs(){
        table.clearSelection();
        accept.setEnabled(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == back){
            clearInputs();
            f.setVisible(false);
            Program.adminHomepage.getJFrame().setVisible(true);
        } else if(ae.getSource() == accept){
            
            String tempId = String.valueOf(table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()),0));
            acceptBooking(Integer.parseInt(tempId));
            
           refreshBookingTable();
           
            
        } else if(ae.getSource() == searchInput){
            rowSorter.setRowFilter(RowFilter.regexFilter(searchInput.getText()));
        } else if(ae.getSource() == reject){
            String tempId = String.valueOf(table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()),0));
            rejectBooking(Integer.valueOf(tempId));
            
           refreshBookingTable();
            
        }
    }

    @Override
    public void focusGained(FocusEvent fe) {
        if(searchInput.getText().equals("Search Bookings...")){
            searchInput.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent fe) {
        if(searchInput.getText().equals("")){
            searchInput.setText("Search Bookings...");
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(table.getSelectedRowCount() == 1){
            accept.setEnabled(true);
            reject.setEnabled(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(table.getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(f,"Please select one car to rent");
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(table.getSelectedRowCount() > 1){
            JOptionPane.showMessageDialog(f,"Please select one car to rent");
            accept.setEnabled(false);
            table.clearSelection();
        } else if(table.getSelectedRow() == 1){
            accept.setEnabled(true);
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
