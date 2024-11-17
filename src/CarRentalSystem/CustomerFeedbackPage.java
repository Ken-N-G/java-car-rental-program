package CarRentalSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

public class CustomerFeedbackPage implements ActionListener{
    private JFrame f;
    private JPanel p;
    private JButton clear, create, back;
    private JTextArea commentInput;
    private JRadioButton ratingOneChoice, ratingTwoChoice, ratingThreeChoice, ratingFourChoice,
            ratingFiveChoice, smPaymentChoice, smBookingChoice, smRentalChoice, smApplicationChoice,
            smServiceChoice, smFeatureAdditionChoice, smOtherChoice;
    private ButtonGroup ratingBG, smBG;
    public CustomerFeedbackPage(){
        f = new JFrame("Car Rental - Customer Feedback");
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
        
        JLabel title = new JLabel("Customer Feedback");
        JLabel subjectMannerLabel = new JLabel("Choose a subject manner");
        JLabel chooseRatingLabel = new JLabel("How statisfied are you with this field?");
        JLabel commentLabel = new JLabel("Enter a comment");
        JLabel satisfactionLeastLabel = new JLabel("Very Unsatisfied");
        JLabel satisfactionVeryLabel = new JLabel("Very Satisfied");
        JLabel satisfactionNeutralLabel = new JLabel("Neutral");
        satisfactionLeastLabel.setBounds(30, 170, 100, 20);
        satisfactionVeryLabel.setBounds(485, 170, 100, 20);
        satisfactionNeutralLabel.setBounds(280, 170, 45, 20);
        title.setBounds(240, 15, 200, 20);
        subjectMannerLabel.setBounds(30, 50, 300, 20);
        chooseRatingLabel.setBounds(30, 150, 300, 20);
        commentLabel.setBounds(30, 220, 300, 20);
        
        clear = new JButton("Clear");
        create = new JButton("Create");
        back = new JButton("Back");
        clear.addActionListener(this);
        create.addActionListener(this);
        back.addActionListener(this);
        clear.setBounds(255, 530, 90, 30);
        create.setBounds(480, 530, 90, 30);
        back.setBounds(30, 530, 90, 30);
        
        commentInput = new JTextArea();
        commentInput.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        commentInput.setBounds(30, 260, 540, 240);
        
        smApplicationChoice = new JRadioButton("Application");
        smBookingChoice = new JRadioButton("Booking");
        smFeatureAdditionChoice = new JRadioButton("Feature Addition");
        smOtherChoice = new JRadioButton("Other");
        smPaymentChoice = new JRadioButton("Payment");
        smRentalChoice = new JRadioButton("Rental");
        smServiceChoice = new JRadioButton("Service");
        ratingOneChoice = new JRadioButton("One");
        ratingTwoChoice = new JRadioButton("Two");
        ratingThreeChoice = new JRadioButton("Three");
        ratingFourChoice = new JRadioButton("Four");
        ratingFiveChoice = new JRadioButton("Five");
        ratingOneChoice.setBounds(30, 190, 50, 30);
        ratingTwoChoice.setBounds(145, 190, 50, 30);
        ratingThreeChoice.setBounds(270, 190, 60, 30);
        ratingFourChoice.setBounds(400, 190, 60, 30);
        ratingFiveChoice.setBounds(520, 190, 50, 30);
        smApplicationChoice.setBounds(30, 70, 90, 30);
        smBookingChoice.setBounds(120, 70, 80, 30);
        smFeatureAdditionChoice.setBounds(200, 70, 120, 30);
        smOtherChoice.setBounds(320, 70, 60, 30);
        smPaymentChoice.setBounds(30, 120, 90, 30);
        smRentalChoice.setBounds(120, 120, 70, 30);
        smServiceChoice.setBounds(200, 120, 90, 30);
        
        smBG = new ButtonGroup();
        smBG.add(smApplicationChoice);
        smBG.add(smBookingChoice);
        smBG.add(smFeatureAdditionChoice);
        smBG.add(smOtherChoice);
        smBG.add(smPaymentChoice);
        smBG.add(smRentalChoice);
        smBG.add(smServiceChoice);
        
        ratingBG = new ButtonGroup();
        ratingBG.add(ratingOneChoice);
        ratingBG.add(ratingTwoChoice);
        ratingBG.add(ratingThreeChoice);
        ratingBG.add(ratingFourChoice);
        ratingBG.add(ratingFiveChoice);
        
        p.add(title);
        p.add(subjectMannerLabel);
        p.add(chooseRatingLabel);
        p.add(commentLabel);
        p.add(satisfactionLeastLabel);
        p.add(satisfactionVeryLabel);
        p.add(satisfactionNeutralLabel);
        p.add(clear);
        p.add(create);
        p.add(back);
        p.add(commentInput);
        p.add(smApplicationChoice);
        p.add(smBookingChoice);
        p.add(smFeatureAdditionChoice);
        p.add(smOtherChoice);
        p.add(smPaymentChoice);
        p.add(smRentalChoice);
        p.add(smServiceChoice);
        p.add(ratingOneChoice);
        p.add(ratingTwoChoice);
        p.add(ratingThreeChoice);
        p.add(ratingFourChoice);
        p.add(ratingFiveChoice);
        p.setSize(600, 600);
        p.setLayout(null);
        f.add(p);
        f.setSize(600,600);
        
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
    }

    public JFrame getJFrame(){
        return f;
    }
    
    public void clearInputs(){
        ratingBG.clearSelection();
        smBG.clearSelection();
        commentInput.setText(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        try{
            if(ae.getSource() == back){
                clearInputs();
                f.setVisible(false);
                Program.customerHomepage.getJFrame().setVisible(true);
            } else if(ae.getSource() == clear){
                clearInputs();
            } else if(ae.getSource() == create){
                if(smBG.getSelection() == null || ratingBG.getSelection() == null || commentInput.getText().isEmpty() == true){
                    throw new Exception();  
                }
                int feedbackNo;
                String subjectMatter = "-";
                int rating = 0;
                String comment;
                if(smApplicationChoice.isSelected() == true){
                    subjectMatter = "Application";
                } else if(smBookingChoice.isSelected() == true){
                    subjectMatter = "Booking";
                } else if(smFeatureAdditionChoice.isSelected() == true){
                    subjectMatter = "Feature Addition";
                } else if(smOtherChoice.isSelected() == true){
                    subjectMatter = "Other";
                } else if(smPaymentChoice.isSelected() == true){
                    subjectMatter = "Payment";
                } else if(smRentalChoice.isSelected() == true){
                    subjectMatter = "Rental";
                } else if(smServiceChoice.isSelected() == true){
                    subjectMatter = "Service";
                }
                if(ratingOneChoice.isSelected() == true){
                    rating = 1;
                } else if(ratingTwoChoice.isSelected() == true){
                    rating = 2;
                } else if(ratingThreeChoice.isSelected() == true){
                    rating = 3;
                } else if(ratingFourChoice.isSelected() == true){
                    rating = 4;
                } else if (ratingFiveChoice.isSelected() == true){
                    rating = 5;
                }
                comment = commentInput.getText();
//                Place holder number
                feedbackNo = Utility.allFeedbacks.size() + 60000001;
                Feedback feedback = new Feedback(feedbackNo, Program.loggedCustomer, rating, subjectMatter, comment);
                Utility.allFeedbacks.add(feedback);
                Program.loggedCustomer.getMyFeedbacks().add(feedback);
                JOptionPane.showMessageDialog(f,"Thank you for your feedback!");
                clearInputs();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(f,"You cannot leave a field empty!");
        }
    }
}
