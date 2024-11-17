
package CarRentalSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdminAddAdminPage {
    
    private JFrame f = new JFrame();
    private JPanel p = new JPanel();
    
    private JTextField username_tf = new JTextField();
    private JPasswordField password_tf = new JPasswordField();
    

    public AdminAddAdminPage(){
        f.setTitle("Car Rental - Add Rental");
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        p.setLayout(new GridLayout(6,2));
        
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
        
        JLabel username = new JLabel("Username:");
        username_tf = new JTextField();
        
        JLabel password = new JLabel("Password:");
        password_tf = new JPasswordField();
        
        
        JButton submit = new JButton("Submit");
        JButton clear = new JButton("Clear");
        
        JButton back = new JButton("Back");
        
        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                submitAdminDetails();
            }
        });
        
        clear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                clearAdminDetails();
            }
        });
        
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.setVisible(false);
                Program.adminAccountManagementPage.getJFrame().setVisible(true);
            }
        });
        
        // Adding to Panel.
        p.add(username);
        p.add(username_tf);
        p.add(password);
        p.add(password_tf);
       
        p.add(new JSeparator());p.add(new JSeparator()); // Adding a horizontal line.
        p.add(submit);
        p.add(clear);
        p.add(new JSeparator());p.add(new JSeparator());
        p.add(back);
        
        
        p.setPreferredSize(new Dimension(570,570));
        f.add(p);
        f.setSize(600,600);
        f.setLocationRelativeTo(null);
        
    }
    
    

    
    public void clearAdminDetails(){
        
    username_tf.setText("");
    password_tf.setText("");
    }
    
    private void submitAdminDetails(){
        
        if(Utility.checkAdmin(username_tf.getText()) != null){
            JOptionPane.showMessageDialog(null, "Duplicate Admin not allowed");
            clearAdminDetails();
        }else if(Utility.isAdminUsername(username_tf.getText()) == false){
            JOptionPane.showMessageDialog(null, "Invalid Admin Name.\n1. Admin name must be in the format Admin[Number], ex: Admin, Admin123, Admin234\n2. It must be 11 character or less");
        }else{
            Admin a = new Admin(username_tf.getText(), password_tf.getText());
            Utility.allAdmins.add(a);
        }
        Program.adminAccountManagementPage.refreshAdminTable(false);
        
        clearAdminDetails();
        f.setVisible(false);
        Program.adminAccountManagementPage.getJFrame().setVisible(true);
    }
    
    public JFrame getJFrame(){
        return f;
    }
    
}
