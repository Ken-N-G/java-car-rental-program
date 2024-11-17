package CarRentalSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AdminHomepage implements ActionListener{
    private JFrame f;
    private JPanel p1, p2;
    private JButton customerRequestTab, rentalTab, bookingRequestedTab, accountManagementTab, reportTab, logout, viewCustomerTab;
    JLabel title;
    public AdminHomepage(){
        f = new JFrame("Car Rental - Admin Homepage");
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
        p2 = new JPanel(new GridLayout(6,1,0,10));
        
        title = new JLabel("");
        title.setBounds(255, 15, 110, 20);
        
        customerRequestTab = new JButton("Customer Register Request");
        rentalTab = new JButton("Rentals");
        bookingRequestedTab = new JButton("Bookings Requested");
        accountManagementTab = new JButton("Admin Account Management");
        reportTab = new JButton("Reports");
        viewCustomerTab = new JButton("View Customers");
        logout = new JButton("Logout");
        logout.setBounds(30, 500, 90, 30);
        
        customerRequestTab.addActionListener(this);
        rentalTab.addActionListener(this);
        bookingRequestedTab.addActionListener(this);
        accountManagementTab.addActionListener(this);
        reportTab.addActionListener(this);
        viewCustomerTab.addActionListener(this);
        logout.addActionListener(this);
        logout.setBounds(30, 500, 90, 30);
        
        p2.setBounds(30, 150, 260, 340);
        p2.add(customerRequestTab);
        p2.add(rentalTab);
        p2.add(bookingRequestedTab);
        p2.add(accountManagementTab);
        p2.add(reportTab);
        p2.add(viewCustomerTab);
        f.add(p2);
        p1.setLayout(null);
        p1.setSize(600,600);
        p1.add(title);
        p1.add(logout);
        f.add(p1);
        
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
    }

    public JFrame getJFrame() {
        return f;
    }
    
    public void setLabels(){
        title.setText("Welcome "+Program.loggedAdmin.getUsername()+"!");
    }
    
    public void removeLabels(){
        title.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try{
            if(ae.getSource() == rentalTab){
                Program.adminRentalPage.refreshRentalTable();
                f.setVisible(false);
                Program.adminRentalPage.getJFrame().setVisible(true);
            } else if(ae.getSource() == bookingRequestedTab){
                Program.adminBookingRequestedPage.refreshBookingTable();
                f.setVisible(false);
                Program.adminBookingRequestedPage.getJFrame().setVisible(true);
            } else if(ae.getSource() == accountManagementTab){
                Program.adminAccountManagementPage.refreshAdminTable(false);
                f.setVisible(false);
                Program.adminAccountManagementPage.getJFrame().setVisible(true);
            } else if(ae.getSource() == reportTab){
                f.setVisible(false);
                Program.adminReportPage.getJFrame().setVisible(true);
            }else if(ae.getSource() == viewCustomerTab){
                Program.adminViewCustomerPage.refreshCustomerTable(false);
                f.setVisible(false);
                Program.adminViewCustomerPage.getJFrame().setVisible(true);
            }else if(ae.getSource() == logout){
                Program.loggedAdmin = null;
                f.setVisible(false);
                Program.loginPage.getJFrame().setVisible(true);
                Utility.writeToFile();
            }else if(ae.getSource() == customerRequestTab){
                Program.adminRegisterationRequestedPage.refreshCustomerTable();
                f.setVisible(false);
                Program.adminRegisterationRequestedPage.getJFrame().setVisible(true);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(f, "Invalid input! Try again");
        }
    }
    
}
