package CarRentalSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class AdminViewCustomerPage implements FocusListener, ActionListener{
    private JFrame f;
    private JPanel p1, p2;
    private JButton back,passbtn;
    private JTextField searchInput;
    private static JTable table;
    private static DefaultTableModel customerTableModel;
    private JScrollPane scrollPane;
    private TableRowSorter rowSorter;
    private static boolean passwordRevealed = false;
    
    public static String idToForward; // A variable made to be used in the AdminUpdateAdminPage.

  private static String repeatString(String s, int r){
        
        String retString = "";
        
        for(int k = 0; k < r; k++){
            retString += s;
        }
        
        return retString;
    }
  
    
    public void refreshCustomerTable(boolean revealPassword){
        
         customerTableModel.setRowCount(0);
         passwordRevealed = revealPassword;  
         
           Object[] rowData = new Object[2];
        for(Customer customer: Utility.allCustomers){
            rowData[0] = customer.getUsername();
            if(revealPassword == true){
                rowData[1] = customer.getPassword();
            }else{
                rowData[1] = repeatString("*", customer.getPassword().length());
            }
            customerTableModel.addRow(rowData);
        }
            
            customerTableModel = (DefaultTableModel) table.getModel();
            customerTableModel.fireTableDataChanged();
    }
    
    public AdminViewCustomerPage(){
        String[] columnNames = {"Username", "Password"};
        
        f = new JFrame("Car Rental - View Customer");
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
        
        JLabel title = new JLabel("View Customer");
        title.setBounds(240, 15, 120, 20);
        
        
        back = new JButton("Back");
        passbtn = new JButton("Toggle Pin");
        back.setBounds(30, 400, 90, 30);
        passbtn.setBounds(30, 330, 150, 30);
        back.addActionListener(this);

        passbtn.addActionListener(this);
        
        searchInput = new JTextField("Search Account...");
        searchInput.addActionListener(this);
        searchInput.addFocusListener(this);
        searchInput.setBounds(30, 0, 540, 30);
        
        table = new JTable();
        customerTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        rowSorter = new TableRowSorter(customerTableModel);
        table.setModel(customerTableModel);
        table.setRowSorter(rowSorter);
        addDataToJTable();
        setJTable();
        
        scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        table.setFillsViewportHeight(true);
        scrollPane.setBounds(30, 50, 540, 200);
        
        
        
        p1.setLayout(null);
        p1.setBounds(0, 100, 600, 500);
        p1.add(searchInput);
        p1.add(scrollPane);
        p1.add(back);
        p1.add(passbtn);
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
        
        Object[] rowData = new Object[2];
        for(Customer customer : Utility.allCustomers){
            rowData[0] = customer.getUsername();
            rowData[1] = repeatString("*",customer.getPassword().length());
            customerTableModel.addRow(rowData);
        }
    }
    
    public void setJTable(){
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.getColumnModel().setColumnSelectionAllowed(false);
        for(int x = 0;x < 2;x++){
            table.getColumnModel().getColumn(x).setPreferredWidth(250);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == back){
            f.setVisible(false);
            Program.adminHomepage.getJFrame().setVisible(true);
            
        } else if(ae.getSource() == searchInput){
            rowSorter.setRowFilter(RowFilter.regexFilter(searchInput.getText()));
        }else if(ae.getSource() == passbtn){
               refreshCustomerTable(!(passwordRevealed));
            }
    }

    @Override
    public void focusGained(FocusEvent fe) {
        if(searchInput.getText().equals("Search Account...")){
            searchInput.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent fe) {
        if(searchInput.getText().equals("")){
            searchInput.setText("Search Account...");
        }
    }
}
