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

public class CustomerPaymentHomepage implements ActionListener {
    private JFrame f;
    private JPanel p1, p2;
    private JButton viewReceiptsTab, makePaymentsTab, back;
    
    public CustomerPaymentHomepage(){
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
        p2 = new JPanel(new GridLayout(2,1,0,80));
        
        JLabel title = new JLabel("Payment Menu");
        title.setBounds(255, 15, 90, 30);
        
        viewReceiptsTab = new JButton("View Receipts");
        makePaymentsTab = new JButton("Make Payments");
        back = new JButton("Back");
        viewReceiptsTab.addActionListener(this);
        makePaymentsTab.addActionListener(this);
        back.addActionListener(this);
        back.setBounds(30, 500, 90, 30);
        
        f.setSize(600, 600);
        p2.setBounds(30, 150, 540, 340);
        p2.add(viewReceiptsTab);
        p2.add(makePaymentsTab);
        f.add(p2);
        p1.setSize(600, 600);
        p1.add(title);
        p1.add(back);
        
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        p1.setLayout(null);
        f.add(p1);
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
            } else if(ae.getSource() == viewReceiptsTab){
                Program.customerViewReceiptPage.addDataToJTable();
                f.setVisible(false);
                Program.customerViewReceiptPage.getJFrame().setVisible(true);
            } else if(ae.getSource() == makePaymentsTab){
                Program.customerMakePaymentPage.addDataToJTable();
                f.setVisible(false);
                Program.customerMakePaymentPage.getJFrame().setVisible(true);
            }
        }catch(Exception e) {
            JOptionPane.showMessageDialog(f, "Invalid input! Try again");
        }
    }
}
