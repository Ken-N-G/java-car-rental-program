package CarRentalSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage implements ActionListener{
    private JFrame f;
    private JPanel p;
    private JButton login, clear, register;
    private JTextField usernameInput;
    private JPasswordField passwordInput;
//    screen componenets are made and added to panel and frame in constructor
    public LoginPage(){
        f = new JFrame("Car Rental - Login");
        f.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmChoice = JOptionPane.showConfirmDialog(f, "Are you sure you want to exit now? "
                        + "Any changes that are not updated will not be saved!", "Close Program", JOptionPane.YES_NO_OPTION);
                if(confirmChoice == 0){
                    Utility.writeToFile();
                    System.exit(0);
                }
            }
        }
        );
        
        p = new JPanel();
        
        p.setLayout(null);
        p.setSize(600,600);
        
        JLabel title = new JLabel("Car Rental System");
        JLabel usernameLabel = new JLabel("username");
        JLabel passwordLabel = new JLabel("password");
        
        title.setBounds(255, 100, 110, 20);
        usernameLabel.setBounds(135, 229, 80, 30);
        passwordLabel.setBounds(135, 279, 80, 30);
        
        usernameInput = new JTextField();
        passwordInput = new JPasswordField();
        
        usernameInput.setBounds(210,230,250,30);
        passwordInput.setBounds(210,280,250,30);
        
        login = new JButton("Login");
        register = new JButton("Register");
        clear = new JButton("Clear");
        
        login.setBounds(370, 370, 90, 20);
        register.setBounds(140, 370,90, 20);
        clear.setBounds(255, 370, 90, 20);
        
        login.addActionListener(this);
        clear.addActionListener(this);
        register.addActionListener(this);
        
        p.add(login);
        p.add(register);
        p.add(clear);
        p.add(title);
        p.add(passwordLabel);
        p.add(passwordInput);
        p.add(usernameLabel);
        p.add(usernameInput);
        f.add(p);
        
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try{
//            login checks if username field matches a user in the user records and the checks if password field matches
            if(ae.getSource() == login){
                if(Utility.checkAdmin(usernameInput.getText()) != null){
                    if(Utility.checkAdmin(usernameInput.getText()).getPassword().equals(new String(passwordInput.getPassword()))){
                        Program.loggedAdmin = Utility.checkAdmin(usernameInput.getText());
                        JOptionPane.showMessageDialog(f,"Successful login! Welcome " + Program.loggedAdmin.getUsername());
                        clearInputs();
                        Program.adminHomepage.setLabels();
                        f.setVisible(false);
                        Program.adminHomepage.getJFrame().setVisible(true);
                    } else{
                        throw new Exception();
                    }
                } else if(Utility.checkCustomer(usernameInput.getText()) != null && Utility.checkCustomer(usernameInput.getText()).isRegistered() == true){
                    if(Utility.checkCustomer(usernameInput.getText()).getPassword().equals(new String(passwordInput.getPassword()))){
                        Program.loggedCustomer = Utility.checkCustomer(usernameInput.getText());
                        JOptionPane.showMessageDialog(f,"Successful login! Welcome " + Program.loggedCustomer.getUsername());
                        clearInputs();
                        Program.customerHomepage.setLabels();
                        f.setVisible(false);
                        Program.customerHomepage.getJFrame().setVisible(true);
                    } else{
                        throw new Exception();
                    }
                } else{
                    throw new Exception();
                }
//                clears fields of input
            } else if(ae.getSource() == clear){
                clearInputs();
//                sends user to the register page
            } else if(ae.getSource() == register){
                clearInputs();
                f.setVisible(false);
                Program.registerPage.getJFrame().setVisible(true);
            }
//            clears both text fields and displays error message
        }catch(Exception e){
            clearInputs();
            JOptionPane.showMessageDialog(f,"Invalid input! Your login credentials were either wrong, empty, or your account has not been verified by the Admin");
        }
    }
    
    public JFrame getJFrame() {
        return f;
    }
    
    public void clearInputs(){
        usernameInput.setText(null);
        passwordInput.setText(null);
    }
}
