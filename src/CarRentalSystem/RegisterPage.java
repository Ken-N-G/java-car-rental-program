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

public class RegisterPage implements ActionListener{
    private JFrame f;
    private JPanel p;
    private JButton back, clear, register;
    private JTextField usernameInput, phoneInput, emailInput, dobInput, homeAddressInput;
    private JPasswordField passwordInput, confirmPasswordInput;
    private JRadioButton maleChoice, femaleChoice, otherChoice;
    private ButtonGroup genderBG;
    public RegisterPage(){
        f = new JFrame("Car Rental - Register");
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
        p.setLayout(null);
        p.setSize(600, 600);
        JLabel title = new JLabel("Customer Member Register");
        JLabel usernameLabel = new JLabel("username");
        JLabel passwordLabel = new JLabel("password");
        JLabel confirmPasswordLabel = new JLabel("confirm password");
        JLabel phoneLabel = new JLabel("phone");
        JLabel emailLabel = new JLabel("email");
        JLabel genderLabel = new JLabel("gender");
        JLabel dobLabel = new JLabel("date of birth");
        JLabel homeAddressLabel = new JLabel("home address");
        JLabel usernameRules = new JLabel("- Usernames must be unique, start with a letter, and cannot contain spaces");
        JLabel passwordRules = new JLabel("- Passwords must have at least 1 special character or number and cannot have spaces");
        JLabel phoneRules = new JLabel("- Phone numbers must be in the '60XXXXXXXXXX' format");
        JLabel emailRules = new JLabel("- Emails must be in a valid email format (e.g. example@mail.com)");
        JLabel dobRules = new JLabel("- Dates must be in the 'YYYY-MM-DD' format. You must be at least 18 y/o to register");
        JLabel homeAddressRules = new JLabel("- Addresses must be valid addresses");
        JLabel noBlankRules = new JLabel("- All fields must be filled in to register");
        
        title.setBounds(210, 15, 180, 20);
        usernameLabel.setBounds(30, 50, 110, 30);
        passwordLabel.setBounds(30, 90, 110, 30);
        confirmPasswordLabel.setBounds(30, 130, 110, 30);
        phoneLabel.setBounds(30, 170, 110, 30);
        emailLabel.setBounds(30, 210, 110, 30);
        genderLabel.setBounds(30, 250, 110, 30);
        dobLabel.setBounds(30, 290, 110, 30);
        homeAddressLabel.setBounds(30, 330, 110, 30);
        usernameRules.setBounds(30,410, 540, 20);
        passwordRules.setBounds(30, 430, 540, 20);
        phoneRules.setBounds(30,450, 540, 20);
        emailRules.setBounds(30, 470, 540, 20);
        dobRules.setBounds(30, 490, 540, 20);
        homeAddressRules.setBounds(30, 510, 540, 20);
        noBlankRules.setBounds(30, 530, 540, 20);
        
        usernameInput = new JTextField();
        passwordInput = new JPasswordField();
        confirmPasswordInput = new JPasswordField();
        phoneInput = new JTextField();
        emailInput = new JTextField();
        dobInput = new JTextField();
        homeAddressInput = new JTextField();
        passwordInput.setEchoChar('*');
        confirmPasswordInput.setEchoChar('*');
        maleChoice = new JRadioButton("Male");
        femaleChoice = new JRadioButton("Female");
        otherChoice = new JRadioButton("Other");
        genderBG = new ButtonGroup();
        
        genderBG.add(maleChoice);
        genderBG.add(femaleChoice);
        genderBG.add(otherChoice);
        
        maleChoice.setBounds(150, 250, 70, 30);
        femaleChoice.setBounds(325, 250, 70, 30);
        otherChoice.setBounds(510, 250, 70, 30);
        usernameInput.setBounds(150,50,420,30);
        passwordInput.setBounds(150,90,420,30);
        confirmPasswordInput.setBounds(150,130,420,30);
        phoneInput.setBounds(150, 170, 420, 30);
        emailInput.setBounds(150, 210, 420, 30);
        dobInput.setBounds(150, 290, 420, 30);
        homeAddressInput.setBounds(150, 330, 420, 30);
        
        back = new JButton("Back");
        register = new JButton("Register");
        clear = new JButton("Clear");
        
        back.setBounds(30, 370, 90, 20);
        register.setBounds(480, 370, 90, 20);
        clear.setBounds(255, 370, 90, 20);
        
        back.addActionListener(this);
        clear.addActionListener(this);
        register.addActionListener(this);
        
        p.add(back);
        p.add(register);
        p.add(clear);
        p.add(title);
        p.add(usernameLabel);
        p.add(passwordLabel);
        p.add(confirmPasswordLabel);
        p.add(phoneLabel);
        p.add(emailLabel);
        p.add(genderLabel);
        p.add(dobLabel);
        p.add(homeAddressLabel);
        p.add(usernameRules);
        p.add(passwordRules);
        p.add(phoneRules);
        p.add(emailRules);
        p.add(dobRules);
        p.add(homeAddressRules);
        p.add(noBlankRules);
        p.add(passwordInput);
        p.add(usernameInput);
        p.add(confirmPasswordInput);
        p.add(phoneInput);
        p.add(emailInput);
        p.add(maleChoice);
        p.add(femaleChoice);
        p.add(otherChoice);
        p.add(dobInput);
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
                f.setVisible(false);
                Program.loginPage.getJFrame().setVisible(true);
            } else if(ae.getSource() == clear){
                clearInputs();
            } else if(ae.getSource() == register){
                if(Utility.isName(usernameInput.getText()) == false || Utility.checkCustomer(usernameInput.getText()) != null){
                    throw new Exception();
                }
                String username = usernameInput.getText();
                if(new String(passwordInput.getPassword()).equals(new String(confirmPasswordInput.getPassword())) == false || new String(passwordInput.getPassword()).isEmpty() == true){
                    throw new Exception();
                }
                String password = new String(passwordInput.getPassword());
                if(Utility.isPhone(Long.parseLong(phoneInput.getText())) == false){
                    throw new Exception();
                }
                long phone = Long.parseLong(phoneInput.getText());
                if(Utility.isMail(emailInput.getText()) == false){
                    throw new Exception();
                }
                String email = emailInput.getText();
                char gender = '-';
                if(maleChoice.isSelected() == true){
                    gender = 'M';
                } else if(femaleChoice.isSelected() == true){
                    gender = 'F';
                } else if(otherChoice.isSelected() == true){
                    gender = 'O';
                } else if(genderBG.getSelection() == null){
                    throw new Exception();
                }
                LocalDate dob = LocalDate.parse(dobInput.getText(), Utility.f);
                int numberOfYears = Utility.yearsBetweenDates(dob, Utility.cd);
                if(Utility.isDate(dobInput.getText()) == false || numberOfYears < 18 || numberOfYears > 150){
                    throw new Exception();
                }
                if(homeAddressInput.getText().isEmpty()){
                    throw new Exception();
                }
                
                String homeAddress = homeAddressInput.getText();
                Customer c = new Customer(username, password, phone, email, gender, dob, Utility.cd, homeAddress, false);
                System.out.println(dob);
                Utility.allCustomers.add(c);
                JOptionPane.showMessageDialog(f, "Thank you " + username + " for registering with us! Your account will go through an approval process before being activated");
                clearInputs();
                f.setVisible(false);
                Program.loginPage.getJFrame().setVisible(true);
            }
            
        }catch(Exception e){
            clearInputs();
            JOptionPane.showMessageDialog(f, "Invalid input! One of your inputs was invalid or was empty");
        }
    }
}
