package CarRentalSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CustomerAccountManagementPage implements ActionListener{
    private JFrame f;
    private JPanel p;
    private JButton clear, update, back;
    private JTextField usernameInput, phoneInput, emailInput, dobInput, homeAddressInput;
    private JPasswordField passwordInput, confirmPasswordInput;
    private JRadioButton maleChoice, femaleChoice, otherChoice;
    private ButtonGroup genderBG;
    private JLabel customerUsername, customerPassword, customerPhone, customerEmail, customerGender, customerDob, customerHomeAddress;
    
    public CustomerAccountManagementPage(){
        f = new JFrame("Car Rental - Customer Account Management");
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
        
        p = new JPanel();
        JLabel title = new JLabel("Customer Account Management");
        JLabel usernameLabel = new JLabel("username");
        JLabel newUsernameLabel = new JLabel("new username");
        JLabel passwordLabel = new JLabel("password");
        JLabel newPasswordLabel = new JLabel("new password");
        JLabel confirmNewPassLabel = new JLabel("confirm password");
        JLabel phoneLabel = new JLabel("phone");
        JLabel newPhoneLabel = new JLabel("new phone");
        JLabel emailLabel = new JLabel("email");
        JLabel newEmailLabel = new JLabel("new email");
        JLabel gender = new JLabel("gender");
        JLabel newGender = new JLabel("new gender");
        JLabel dob = new JLabel("date of birth");
        JLabel newDob = new JLabel("new date of birth");
        JLabel homeAddress = new JLabel("home address");
        JLabel newHomeAddress = new JLabel("new home address");
        customerUsername = new JLabel("");
        customerPassword = new JLabel("");
        customerPhone = new JLabel("");
        customerEmail = new JLabel("");
        customerGender = new JLabel("");
        customerDob = new JLabel("");
        customerHomeAddress = new JLabel("");
        customerUsername.setBounds(190, 50, 110, 20);
        customerPassword.setBounds(190, 110, 110, 20);
        customerPhone.setBounds(190, 200, 110, 20);
        customerEmail.setBounds(190, 260, 110, 20);
        customerGender.setBounds(190, 320, 110, 20);
        customerDob.setBounds(190, 380, 110, 20);
        customerHomeAddress.setBounds(190, 440, 110, 20);
        usernameLabel.setBounds(30, 50, 110, 20);
        newUsernameLabel.setBounds(60, 80, 110, 20);
        passwordLabel.setBounds(30, 110, 110, 20);
        newPasswordLabel.setBounds(60, 140, 110, 20);
        confirmNewPassLabel.setBounds(60, 170, 110, 20);
        phoneLabel.setBounds(30, 200, 110, 20);
        newPhoneLabel.setBounds(60, 230, 110, 20);
        emailLabel.setBounds(30, 260, 110, 20);
        newEmailLabel.setBounds(60, 290, 110, 20);
        gender.setBounds(30, 320, 110, 20);
        newGender.setBounds(60, 350, 110, 20);
        dob.setBounds(30, 380, 110, 20);
        newDob.setBounds(60, 410, 110, 20);
        homeAddress.setBounds(30, 440, 110, 20);
        newHomeAddress.setBounds(60, 470, 110, 20);
        title.setBounds(210, 15, 200, 20);
        
        clear = new JButton("Clear");
        update = new JButton("Update");
        back = new JButton("Back");
        back.addActionListener(this);
        update.addActionListener(this);
        clear.addActionListener(this);
        clear.setBounds(255, 530, 90, 30);
        update.setBounds(480, 530, 90, 30);
        back.setBounds(30, 530, 90, 30);
        
        usernameInput = new JTextField();
        passwordInput = new JPasswordField();
        confirmPasswordInput = new JPasswordField();
        phoneInput = new JTextField();
        emailInput = new JTextField();
        dobInput = new JTextField();
        homeAddressInput = new JTextField();
        usernameInput.setBounds(190, 75, 380, 30);
        phoneInput.setBounds(190, 225, 380, 30);
        emailInput.setBounds(190, 285, 380, 30);
        dobInput.setBounds(190, 405, 380, 30);
        homeAddressInput.setBounds(190, 465, 380, 30);
        passwordInput.setBounds(190, 135, 380, 30);
        confirmPasswordInput.setBounds(190, 165, 380, 30);
        
        maleChoice = new JRadioButton("Male");
        femaleChoice = new JRadioButton("Female");
        otherChoice = new JRadioButton("Other");
        genderBG = new ButtonGroup();
        genderBG.add(maleChoice);
        genderBG.add(femaleChoice);
        genderBG.add(otherChoice);
        maleChoice.setBounds(190, 345, 70, 30);
        femaleChoice.setBounds(350, 345, 70, 30);
        otherChoice.setBounds(510, 345, 70, 30);
        
        p.setLayout(null);
        p.setSize(600,600);
        p.add(maleChoice);
        p.add(femaleChoice);
        p.add(otherChoice);
        p.add(title);
        p.add(usernameLabel);
        p.add(newUsernameLabel);
        p.add(passwordLabel);
        p.add(newPasswordLabel);
        p.add(confirmNewPassLabel);
        p.add(phoneLabel);
        p.add(newPhoneLabel);
        p.add(emailLabel);
        p.add(newEmailLabel);
        p.add(gender);
        p.add(newGender);
        p.add(dob);
        p.add(newDob);
        p.add(homeAddress);
        p.add(newHomeAddress);
        p.add(customerUsername);
        p.add(customerPassword);
        p.add(customerPhone);
        p.add(customerEmail);
        p.add(customerGender);
        p.add(customerDob);
        p.add(customerHomeAddress);
        p.add(clear);
        p.add(update);
        p.add(back);
        p.add(usernameInput);
        p.add(passwordInput);
        p.add(confirmPasswordInput);
        p.add(dobInput);
        p.add(phoneInput);
        p.add(emailInput);
        p.add(homeAddressInput);
        f.add(p);
        f.setSize(600, 600);
        
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
    }
    
    public JFrame getJFrame() {
        return f;
    }
    
    public void setLabels(){
        customerUsername.setText(Program.loggedCustomer.getUsername());
        customerPassword.setText(Program.loggedCustomer.getPassword());
        customerPhone.setText(Long.toString(Program.loggedCustomer.getPhone()));
        customerEmail.setText(Program.loggedCustomer.getEmail());
        customerGender.setText(Character.toString(Program.loggedCustomer.getGender()));
        customerDob.setText(Program.loggedCustomer.getDob().toString());
        customerHomeAddress.setText(Program.loggedCustomer.getHomeAddress());
    }
    
    public void removeLabels(){
        customerUsername.setText("");
        customerPassword.setText("");
        customerPhone.setText("");
        customerEmail.setText("");
        customerGender.setText("");
        customerDob.setText("");
        customerHomeAddress.setText("");
    }

    public void clearInputs(){
        usernameInput.setText(null);
        passwordInput.setText(null);
        confirmPasswordInput.setText(null);
        phoneInput.setText(null);
        emailInput.setText(null);
        genderBG.clearSelection();
        dobInput.setText(null);
        homeAddressInput.setText(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        try{
            if(ae.getSource() == back){
                clearInputs();
                removeLabels();
                Program.customerHomepage.setLabels();
                f.setVisible(false);
                Program.customerHomepage.getJFrame().setVisible(true);
            } else if(ae.getSource() == clear){
                clearInputs();
            } else if(ae.getSource() == update){
                if(usernameInput.getText().isEmpty() == false && Utility.isName(usernameInput.getText()) == true && Utility.checkCustomer(usernameInput.getText()) == null){
                    Program.loggedCustomer.setUsername(usernameInput.getText());
                }
                if(new String(passwordInput.getPassword()).equals(new String(confirmPasswordInput.getPassword())) == true && new String(passwordInput.getPassword()).isEmpty() == false){
                    Program.loggedCustomer.setPassword(new String(passwordInput.getPassword()));
                }
                if(phoneInput.getText().isEmpty() == false && Utility.isPhone(Long.parseLong(phoneInput.getText())) == true){
                    Program.loggedCustomer.setPhone(Long.parseLong(phoneInput.getText()));
                }
                if(Utility.isMail(emailInput.getText()) == true && emailInput.getText().isEmpty() == false){
                    Program.loggedCustomer.setEmail(emailInput.getText());
                }
                if(maleChoice.isSelected() == true){
                    Program.loggedCustomer.setGender('M');
                } else if(femaleChoice.isSelected() == true){
                    Program.loggedCustomer.setGender('F');
                } else if(otherChoice.isSelected() == true){
                    Program.loggedCustomer.setGender('O');
                }
                if(dobInput.getText().isEmpty() == false && Utility.isDate(dobInput.getText()) == true){
                    Program.loggedCustomer.setDob(LocalDate.parse(dobInput.getText(), Utility.f));
                }
                if(homeAddressInput.getText().isEmpty() == false){
                    Program.loggedCustomer.setHomeAddress(homeAddressInput.getText());
                }
                clearInputs();
                setLabels();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(f, "Invalid input!");
        }
    } 
}
