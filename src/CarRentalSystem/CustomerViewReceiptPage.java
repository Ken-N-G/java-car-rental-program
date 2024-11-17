package CarRentalSystem;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

public class CustomerViewReceiptPage implements ActionListener, FocusListener{
    private JFrame f;
    private JPanel p1, p2;
    private JButton back;
    private JTextField searchInput;
    private JTable receipts;
    private DefaultTableModel receiptTableModel;
    private JScrollPane scrollPane;
    private TableRowSorter rowSorter;

    
    public CustomerViewReceiptPage(){
        String[] columnNames = {"Receipt No.", "Payment Type", "Fee (RM)", "Booking No.", "Date Paid"};
        f = new JFrame("Car Rental - Customer View Receipt Page");
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
        
        JLabel title = new JLabel("Customer View Receipt Page");
        title.setBounds(240, 15, 120, 20);
        
        back = new JButton("Back");
        back.setBounds(30, 400, 90, 30);
        back.addActionListener(this);
        
        searchInput = new JTextField("Search Receipts...");
        searchInput.addActionListener(this);
        searchInput.addFocusListener(this);
        searchInput.setBounds(30, 0, 540, 30);
        
        receipts = new JTable();
        receiptTableModel = new DefaultTableModel(columnNames, 0){
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
                        return Integer.class;
                    case 3:
                        return Integer.class;
                    case 4:
                        return LocalDate.class;
                    default:
                        throw new IllegalArgumentException("Invalid column accessed!");
                }
            }
        };
        rowSorter = new TableRowSorter(receiptTableModel);
        receipts.setModel(receiptTableModel);
        receipts.setRowSorter(rowSorter);
        setJTable();
        
        scrollPane = new JScrollPane(receipts, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        receipts.setFillsViewportHeight(true);
        scrollPane.setBounds(30, 50, 540, 200);
        
        p1.setLayout(null);
        p1.setBounds(0, 100, 600, 500);
        p1.add(searchInput);
        p1.add(scrollPane);
        p1.add(back);
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
        Object[] rowData = new Object[5];
        for(Receipt r : Program.loggedCustomer.getMyReceipts()){
            rowData[0] = r.getReceiptNo();
            rowData[1] = r.getPaymentType();
            rowData[2] = r.getBooking().getFee();
            rowData[3] = r.getBooking().getBookingNo();
            rowData[4] = r.getDatePaid();
            receiptTableModel.addRow(rowData);
        }
    }
    
    public void removeDataFromJTable(){
        receiptTableModel.setRowCount(0);
    }
    
    public void setJTable(){
        receipts.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        receipts.getTableHeader().setReorderingAllowed(false);
        receipts.getTableHeader().setResizingAllowed(false);
        receipts.getColumnModel().setColumnSelectionAllowed(false);
        for(int x = 0;x < 5;x++){
            receipts.getColumnModel().getColumn(x).setPreferredWidth(150);
        }
    }   
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == back){
            removeDataFromJTable();
            f.setVisible(false);
            Program.customerPaymentHomepage.getJFrame().setVisible(true);
        } else if(ae.getSource() == searchInput){
            rowSorter.setRowFilter(RowFilter.regexFilter(searchInput.getText()));
        }
    }

    @Override
    public void focusGained(FocusEvent fe) {
        if(searchInput.getText().equals("Search Receipts...")){
            searchInput.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent fe) {
        if(searchInput.getText().equals("")){
            searchInput.setText("Search Receipts...");
        }
    }
}
