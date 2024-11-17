package CarRentalSystem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CustomerBookingHomepage implements ActionListener {
    private JFrame f;
    private JPanel p1, p2;
    private JButton viewRentalsTab, createBookingsTab, viewBookingsTab, back;
    
    public CustomerBookingHomepage (){
        f = new JFrame("Car Rental - Customer Booking Homepage");
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
        p2 = new JPanel(new GridLayout(3,1,0,30));
        
        JLabel title = new JLabel("Booking Menu");
        title.setBounds(260, 15, 80, 30);
        
        viewRentalsTab = new JButton("View Rentals");
        viewBookingsTab = new JButton("View & Manage Bookings");
        createBookingsTab = new JButton("Create a Booking");
        back = new JButton("Back");
        viewRentalsTab.addActionListener(this);
        viewBookingsTab.addActionListener(this);
        createBookingsTab.addActionListener(this);
        back.addActionListener(this);
        back.setBounds(30, 500, 90, 30);
        
        f.setSize(600, 600);
        p2.setBounds(30, 150, 540, 340);
        p2.add(viewRentalsTab);
        p2.add(createBookingsTab);
        p2.add(viewBookingsTab);
        f.add(p2);
        p1.setSize(600, 600);
        p1.add(title);
        p1.add(back);
        p1.setLayout(null);
        f.add(p1);
        
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
    }
    
    public JFrame getJFrame(){
        return f;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try{
            if(ae.getSource() == back) {
                f.setVisible(false);
                Program.customerHomepage.getJFrame().setVisible(true);
            } else if(ae.getSource() == viewRentalsTab){
                f.setVisible(false);
                Program.customerRentalsPage.getJFrame().setVisible(true);
            } else if(ae.getSource() == createBookingsTab){
                f.setVisible(false);
                Program.customerCreateBookingPage.getJFrame().setVisible(true);
            } else if(ae.getSource() == viewBookingsTab){
                Program.customerViewBookingPage.addDataToJTable();
                f.setVisible(false);
                Program.customerViewBookingPage.getJFrame().setVisible(true);
            }
        }catch(Exception e) {
            JOptionPane.showMessageDialog(f, "Invalid input! Try again");
        }
    }
}
