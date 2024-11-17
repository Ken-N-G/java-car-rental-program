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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class CustomerMakePaymentPage implements ActionListener, FocusListener, MouseListener{
    private JFrame f;
    private JPanel p1, p2;
    private JButton back, pay, clear;
    private JTextField searchInput;
    private JTable bookings;
    private DefaultTableModel bookingTableModel;
    private JScrollPane scrollPane;
    private TableRowSorter rowSorter;
    private JLabel paymentMethod;
    private JRadioButton card, eWallet, bankTransfer;
    private ButtonGroup paymentMethodBG;
    private Booking chosenBooking;

    
    public CustomerMakePaymentPage(){
        String[] columnNames = {"Booking No.", "Car License Plate", "Status", "Fee (RM)", "Date Booked", "Start Date", "End Date"};
        f = new JFrame("Car Rental - Customer Payment Page");
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
        
        JLabel title = new JLabel("Customer Payment Page");
        title.setBounds(240, 15, 120, 20);
        
        paymentMethod = new JLabel("Payment Method:");
        paymentMethod.setBounds(30, 360, 100, 30);
        
        back = new JButton("Back");
        back.setBounds(30, 400, 90, 30);
        back.addActionListener(this);
        pay = new JButton("Pay");
        pay.setBounds(440, 400, 130, 30);
        pay.addActionListener(this);
        pay.setEnabled(false);
        clear = new JButton("Clear");
        clear.setBounds(255, 400, 90, 30);
        clear.addActionListener(this);
        
        card = new JRadioButton("Debit/Credit");
        eWallet = new JRadioButton("E-Wallet");
        bankTransfer = new JRadioButton("Bank Transfer");
        paymentMethodBG = new ButtonGroup();
        paymentMethodBG.add(card);
        paymentMethodBG.add(eWallet);
        paymentMethodBG.add(bankTransfer);
        card.setBounds(150, 360, 100, 30);
        eWallet.setBounds(310, 360, 100, 30);
        bankTransfer.setBounds(460, 360, 110, 30);
        
        searchInput = new JTextField("Search Unpaid Bookings...");
        searchInput.addActionListener(this);
        searchInput.addFocusListener(this);
        searchInput.setBounds(30, 0, 540, 30);
        
        bookings = new JTable();
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
        bookings.addMouseListener(this);
        scrollPane = new JScrollPane(bookings, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        bookings.setFillsViewportHeight(true);
        scrollPane.setBounds(30, 50, 540, 200);
        
        p1.setLayout(null);
        p1.setBounds(0, 100, 600, 500);
        p1.add(card);
        p1.add(eWallet);
        p1.add(bankTransfer);
        p1.add(searchInput);
        p1.add(scrollPane);
        p1.add(back);
        p1.add(pay);
        p1.add(clear);
        p1.add(paymentMethod);
        p2.setBounds(30, 0, 540, 100);
        p2.add(title);
        f.add(p1);
        f.add(p2);
        
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setSize(600, 600);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(false);
    }
    
    public JFrame getJFrame(){
        return f;
    }
    
    public void addDataToJTable(){
        Object[] rowData = new Object[7];
        for(Booking b : Program.loggedCustomer.getMyBookings()){
            if(!b.getStatus().equals("Awaiting Payment")){
                continue;
            }
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
    
    public void clearInputs(){
        chosenBooking = null;
        searchInput.setText("");
        bookings.clearSelection();
        paymentMethodBG.clearSelection();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        try{
            if(ae.getSource() == back){
                removeDataFromJTable();
                f.setVisible(false);
                Program.customerPaymentHomepage.getJFrame().setVisible(true);
            } else if(ae.getSource() == pay){
                for(Booking b : Program.loggedCustomer.getMyBookings()){
                    if(b.getBookingNo() == (int)bookings.getModel().getValueAt(bookings.convertRowIndexToModel(bookings.getSelectedRow()), 0)){
                        chosenBooking = b;
                    }
                }
                if(paymentMethodBG.getSelection() == null){
                    throw new Exception();
                }
                int choice = JOptionPane.showConfirmDialog(f, "Are you sure you want to pay for this booking?", "Confirm Payment", JOptionPane.YES_NO_OPTION);
                if(choice == 0){
                    int receiptNo = Utility.allReceipts.size() + 70000001;
                    String paymentType = "";
                    if(card.isSelected() == true){
                        paymentType = "Card";
                    } else if(eWallet.isSelected() == true){
                        paymentType = "E-Wallet";
                    } else if(bankTransfer.isSelected() == true){
                        paymentType = "Bank Transfer";
                    }
                    Booking booking = chosenBooking;
                    Customer customer = Program.loggedCustomer;
                    LocalDate datePaid = Utility.cd;
                    Receipt receipt = new Receipt(receiptNo, paymentType, booking, customer, datePaid);
                    Program.loggedCustomer.getMyReceipts().add(receipt);
                    Utility.allReceipts.add(receipt);
                    JOptionPane.showMessageDialog(f, "Your payment for " + chosenBooking.getBookingNo() + " has been received! Your receipt number is " + receipt.getReceiptNo());
                    bookings.clearSelection();
                    for(Booking b : Program.loggedCustomer.getMyBookings()){
                        if(b.getBookingNo() == chosenBooking.getBookingNo()){
                            b.setStatus("Accepted");
                        }
                    }
                    for(Booking b : Utility.allBookings){
                        if(b.getBookingNo() == chosenBooking.getBookingNo()){
                            b.setStatus("Accepted");
                        }
                    }
                    removeDataFromJTable();
                    addDataToJTable();
                } else if(choice == 1){
                    JOptionPane.showMessageDialog(f, "Payment cancelled!");
                }
                clearInputs();
                pay.setEnabled(false);
            } else if(ae.getSource() == searchInput){
                rowSorter.setRowFilter(RowFilter.regexFilter(searchInput.getText()));
            } else if(ae.getSource() == clear){
                clearInputs();
                pay.setEnabled(false);
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(f, "Invalid input! Please enter all fields");
        }
    }

    @Override
    public void focusGained(FocusEvent fe) {
        if(searchInput.getText().equals("Search Unpaid Bookings...")){
            searchInput.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent fe) {
        if(searchInput.getText().equals("")){
            searchInput.setText("Search Unpaid Bookings...");
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(bookings.getSelectedRowCount() == 1){
            pay.setEnabled(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(bookings.getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(f,"Please select one booking to pay");
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(bookings.getSelectedRowCount() > 1){
            JOptionPane.showMessageDialog(f,"Please select one booking to pay");
            pay.setEnabled(false);
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