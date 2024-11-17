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

public class CustomerHomepage implements ActionListener{
    private JFrame f;
    private JPanel p1, p2;
    private JButton bookingTab, paymentTab, accountManagementTab, feedbackTab, logout;
    JLabel title;
    public CustomerHomepage(){
        f = new JFrame("Car Rental - Customer Homepage");
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
        p2 = new JPanel(new GridLayout(4,1,0,10));
        
        title = new JLabel("");
        title.setBounds(255, 15, 110, 20);
        
        bookingTab = new JButton("My Bookings");
        paymentTab = new JButton("My Payments");
        accountManagementTab = new JButton("My Account");
        feedbackTab = new JButton("Give your feedback");
        logout = new JButton("Logout");
        bookingTab.addActionListener(this);
        paymentTab.addActionListener(this);
        accountManagementTab.addActionListener(this);
        feedbackTab.addActionListener(this);
        logout.addActionListener(this);
        logout.setBounds(30, 500, 90, 30);
        
        p2.setBounds(30, 150, 260, 340);
        p2.add(bookingTab);
        p2.add(paymentTab);
        p2.add(accountManagementTab);
        p2.add(feedbackTab);
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
        title.setText("Welcome "+Program.loggedCustomer.getUsername()+"!");
    }
    
    public void removeLabels(){
        title.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try{
            if(ae.getSource() == bookingTab){
                f.setVisible(false);
                Program.customerBookingHomepage.getJFrame().setVisible(true);
            } else if(ae.getSource() == paymentTab){
                f.setVisible(false);
                Program.customerPaymentHomepage.getJFrame().setVisible(true);
            } else if(ae.getSource() == accountManagementTab){
                Program.customerAccountManagementPage.setLabels();
                removeLabels();
                f.setVisible(false);
                Program.customerAccountManagementPage.getJFrame().setVisible(true);
            } else if(ae.getSource() == feedbackTab){
                f.setVisible(false);
                Program.customerFeedbackPage.getJFrame().setVisible(true);
            } else if(ae.getSource() == logout){
                Program.loggedCustomer = null;
                f.setVisible(false);
                Program.loginPage.getJFrame().setVisible(true);
                Utility.writeToFile();
            }
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(f, "Invalid input! Try again");
        }
    }
    
}
