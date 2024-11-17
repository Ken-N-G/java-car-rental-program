package CarRentalSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class AdminAccountManagementPage implements ActionListener, FocusListener, MouseListener{
    private JFrame f;
    private JPanel p1, p2;
    private JButton addbtn, back, delbtn, updbtn,passbtn;
    private JTextField searchInput;
    private static JTable table;
    private static DefaultTableModel accountTableModel;
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
    
    private static void deleteAdmin(String username){
        if(username.equals(Program.loggedAdmin.getUsername())){
            JOptionPane.showMessageDialog(null, "Cannot delete.\nYou are currently logged in as " + Program.loggedAdmin.getUsername() + ".");
        }else{
            for(int k = 0; k < Utility.allAdmins.size(); k++){
                Admin admin = Utility.allAdmins.get(k);
                if(admin.getUsername().equals(username)){
                    Utility.allAdmins.remove(admin);
                }
            }
        }
    }
    
    public void refreshAdminTable(boolean revealPassword){
        
         accountTableModel.setRowCount(0);
         passwordRevealed = revealPassword;  
         
           Object[] rowData = new Object[2];
        for(Admin admin: Utility.allAdmins){
            rowData[0] = admin.getUsername();
            if(revealPassword == true){
                rowData[1] = admin.getPassword();
            }else{
                rowData[1] = repeatString("*", admin.getPassword().length());
            }
            accountTableModel.addRow(rowData);
        }
            
            accountTableModel = (DefaultTableModel) table.getModel();
            
            accountTableModel.fireTableDataChanged();
            delbtn.setEnabled(false);
            updbtn.setEnabled(false);
    }
    
    public AdminAccountManagementPage(){
        String[] columnNames = {"Username", "Password"};
        
        f = new JFrame("Car Rental - Account Management");
        p1 = new JPanel();
        p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        
        JLabel title = new JLabel("Account Management");
        title.setBounds(240, 15, 120, 20);
        
        addbtn = new JButton("Add");
        back = new JButton("Back");
        delbtn = new JButton("Delete");
        updbtn = new JButton("Update");
        passbtn = new JButton("Toggle Pin");
        back.setBounds(30, 400, 90, 30);
        delbtn.setBounds(210, 270, 150, 30);
        addbtn.setBounds(30, 270, 150, 30);
        updbtn.setBounds(390, 270, 150, 30);
        passbtn.setBounds(30, 330, 150, 30);
        back.addActionListener(this);
        delbtn.addActionListener(this);
        addbtn.addActionListener(this);
        updbtn.addActionListener(this);
        passbtn.addActionListener(this);
        
        delbtn.setEnabled(false);
        updbtn.setEnabled(false);
        
        searchInput = new JTextField("Search Account...");
        searchInput.addActionListener(this);
        searchInput.addFocusListener(this);
        searchInput.setBounds(30, 0, 540, 30);
        
        table = new JTable();
        accountTableModel = new DefaultTableModel(columnNames, 0) {
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
                    default:
                        throw new IllegalArgumentException("Invalid column accessed!");
                }
            }
            
        };
        rowSorter = new TableRowSorter(accountTableModel);
        table.setModel(accountTableModel);
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
        p1.add(passbtn);
        p2.setBounds(30, 0, 540, 100);
        p2.add(title);
        f.add(p1);
        f.add(p2);
        
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
        f.setSize(600, 600);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    
    public JFrame getJFrame(){
        return f;
    }
    
    public void addDataToJTable(){
        
        Object[] rowData = new Object[2];
        for(Admin admin : Utility.allAdmins){
            rowData[0] = admin.getUsername();
            rowData[1] = repeatString("*",admin.getPassword().length());
            accountTableModel.addRow(rowData);
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
            Program.adminAddAdminPage.getJFrame().setVisible(true);
            
            
        } else if(ae.getSource() == searchInput){
            rowSorter.setRowFilter(RowFilter.regexFilter(searchInput.getText()));
        } else if(ae.getSource() == delbtn){
            String tempId = String.valueOf(table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()),0));
            deleteAdmin(tempId);
            
           // Updating the table.
          refreshAdminTable(false);
            
            }else if(ae.getSource() == updbtn){
                idToForward = String.valueOf(table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()),0));
                Program.adminUpdateAdminPage.refresh();
                f.setVisible(false);
                Program.adminUpdateAdminPage.getJFrame().setVisible(true);
            }else if(ae.getSource() == passbtn){
               refreshAdminTable(!(passwordRevealed));
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
            JOptionPane.showMessageDialog(f,"Please select one account");
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(table.getSelectedRowCount() > 1){
            JOptionPane.showMessageDialog(f,"Please select one account");
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
