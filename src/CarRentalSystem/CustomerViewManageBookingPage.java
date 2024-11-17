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

public class CustomerViewManageBookingPage implements ActionListener, FocusListener, MouseListener{
    private JFrame f;
    private JPanel p1, p2;
    private JButton back, cancelSelectedBooking;
    private JTextField searchInput;
    private JTable bookings;
    private DefaultTableModel bookingTableModel;
    private JScrollPane scrollPane;
    private TableRowSorter rowSorter;

    
    public CustomerViewManageBookingPage(){
        String[] columnNames = {"Booking No.", "Car License Plate", "Status", "Fee (RM)", "Date Booked", "Start Date", "End Date"};
        
        f = new JFrame("Car Rental - Customer View Booking Page");
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
        
        JLabel title = new JLabel("Customer View Booking Page");
        title.setBounds(240, 15, 120, 20);
        
        back = new JButton("Back");
        back.setBounds(30, 400, 90, 30);
        back.addActionListener(this);
        cancelSelectedBooking = new JButton("Cancel Selected Booking");
        cancelSelectedBooking.setBounds(200, 260, 200, 30);
        cancelSelectedBooking.addActionListener(this);
        cancelSelectedBooking.setEnabled(false);
        
        searchInput = new JTextField("Search Bookings...");
        searchInput.addActionListener(this);
        searchInput.addFocusListener(this);
        searchInput.setBounds(30, 0, 540, 30);
        
        bookings = new JTable();
        bookings.addMouseListener(this);
        bookingTableModel = new DefaultTableModel(columnNames, 0){
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
                        return Integer.class;
                    case 4:
                        return LocalDate.class;
                    case 5:
                        return LocalDate.class;
                    case 6:
                        return LocalDate.class;
                    default:
                        throw new IllegalArgumentException("Invalid column accessed!");
                }
            }
        };
        rowSorter = new TableRowSorter(bookingTableModel);
        bookings.setModel(bookingTableModel);
        bookings.setRowSorter(rowSorter);
        setJTable();
        
        scrollPane = new JScrollPane(bookings, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        bookings.setFillsViewportHeight(true);
        scrollPane.setBounds(30, 50, 540, 200);
        
        p1.setLayout(null);
        p1.setBounds(0, 100, 600, 500);
        p1.add(searchInput);
        p1.add(scrollPane);
        p1.add(back);
        p1.add(cancelSelectedBooking);
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
        Object[] rowData = new Object[7];
        for(Booking b : Program.loggedCustomer.getMyBookings()){
            rowData[0] = b.getBookingNo();
            rowData[1] = b.getCar().getLicensePlate();
            rowData[2] = b.getStatus();
            rowData[3] = b.getFee();
            rowData[4] = b.getDateBooked();
            rowData[5] = b.getStartDate();
            rowData[6] = b.getEndDate();
            bookingTableModel.addRow(rowData);
        }
    }
    
    public void removeDataFromJTable(){
        bookingTableModel.setRowCount(0);
    }
    
    public void setJTable(){
        bookings.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        bookings.getTableHeader().setReorderingAllowed(false);
        bookings.getTableHeader().setResizingAllowed(false);
        bookings.getColumnModel().setColumnSelectionAllowed(false);
        for(int x = 0;x < 7;x++){
            bookings.getColumnModel().getColumn(x).setPreferredWidth(150);
        }
    }   
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == back){
            removeDataFromJTable();
            f.setVisible(false);
            cancelSelectedBooking.setEnabled(false);
            Program.customerBookingHomepage.getJFrame().setVisible(true);
        } else if(ae.getSource() == searchInput){
            rowSorter.setRowFilter(RowFilter.regexFilter(searchInput.getText()));
        } else if(ae.getSource() == cancelSelectedBooking){
            Booking chosenBooking = Utility.checkBooking((int)bookings.getModel().getValueAt(bookings.convertRowIndexToModel(bookings.getSelectedRow()), 0));
            if(chosenBooking.getStatus().equals("Finished") || chosenBooking.getStatus().equals("Cancelled")){
                JOptionPane.showMessageDialog(f, "You cannot cancel a booking that has finished or been cancelled!");
            } else {
                int choice = JOptionPane.showConfirmDialog(f, "Are you sure you want to cancel booking " + chosenBooking.getBookingNo() + "?", "Confirm Cancellation", JOptionPane.YES_NO_OPTION);
                if(choice == 0){
                    for(Booking b : Utility.allBookings){
                        if(b.getBookingNo() == chosenBooking.getBookingNo()){
                            b.setStatus("Cancelled");
                        }
                    }
                    for(Booking b : Program.loggedCustomer.getMyBookings()){
                        if(b.getBookingNo() == chosenBooking.getBookingNo()){
                            b.setStatus("Cancelled");
                        }
                    }
                    removeDataFromJTable();
                    addDataToJTable();
                } else if(choice == 1){
                    JOptionPane.showMessageDialog(f, "Cancellation aborted!");
                }
            }
            cancelSelectedBooking.setEnabled(false);
            bookings.clearSelection();
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
        if(bookings.getSelectedRowCount() == 1){
            cancelSelectedBooking.setEnabled(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
       if(bookings.getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(f,"Please select one booking to cancel");
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(bookings.getSelectedRowCount() > 1){
            JOptionPane.showMessageDialog(f,"Please select one booking to pay");
            cancelSelectedBooking.setEnabled(false);
            bookings.clearSelection();
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
