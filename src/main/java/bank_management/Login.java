package bank_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.net.URL;

import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {
    JButton Login, SignUp, Clear;
    JTextField cardTextField;
    JPasswordField pinTextField;
    String formNo;

    Login(){
        setTitle("Automated Teller Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Welcome to ATM");
        titleLabel.setFont(new Font("Oswald", Font.BOLD, 38));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(150, 50, 500, 60);
        add(titleLabel);

        JLabel cardNumber = new JLabel("Card No:");
        cardNumber.setFont(new Font("Segoe UI", Font.BOLD, 24));
        cardNumber.setBounds(170, 160, 150, 45);
        add(cardNumber);

        cardTextField = new JTextField();
        cardTextField.setBounds(320, 160, 310, 45);
        cardTextField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        cardTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        add(cardTextField);

        JLabel pinNumber = new JLabel("Pin:");
        pinNumber.setFont(new Font("Segoe UI", Font.BOLD, 24));
        pinNumber.setBounds(170, 230, 150, 45);
        add(pinNumber);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(320, 230, 310, 45);
        pinTextField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        pinTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        add(pinTextField);

        Login = new JButton("SIGN IN");
        Login.setBounds(320,300,130,40);
        Login.setBackground(Color.BLACK);
        Login.setForeground(Color.WHITE);
        Login.addActionListener(this::actionPerformed);
        add(Login);

        Clear = new JButton("CLEAR");
        Clear.setBounds(500,300,130,40);
        Clear.setBackground(Color.BLACK);
        Clear.setForeground(Color.WHITE);
        Clear.addActionListener(this::actionPerformed);
        add(Clear);

        SignUp = new JButton("SIGN UP");
        SignUp.setBounds(320,360,310,40);
        SignUp.setBackground(Color.BLACK);
        SignUp.setForeground(Color.WHITE);
        SignUp.addActionListener(this::actionPerformed);
        add(SignUp);


        URL url = getClass().getResource("/icons/logo.jpg");
        if(url == null){
            System.out.println("Image not found! Check the path.");
        }
        else{
            ImageIcon i1 = new ImageIcon(
                    new ImageIcon(url)
                            .getImage()
                            .getScaledInstance(100,100,Image.SCALE_SMOOTH)
            );
            JLabel label = new JLabel(i1);
            label.setBounds(130,30,80,80);

            setLayout(null);
            add(label);
        }
        setSize(800,480);
        setLocationRelativeTo(null);
        cardTextField.requestFocusInWindow();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Login)
        {
            String cardNumber = cardTextField.getText().trim();;
            String pinNumber = new String(pinTextField.getPassword());
            if (cardNumber.isEmpty() || pinNumber.isEmpty()){
                JOptionPane.showMessageDialog(this,"Enter Card Number and Pin");
                return;
            }
            try{
                Conn conn = new Conn();
                PreparedStatement pst = conn.con.prepareStatement(
                        "SELECT c.form_no, c.pin_hash, c.status, c.salt, s.name, a.balance FROM cards c JOIN signup s ON c.form_no = s.formNo JOIN accounts a ON c.form_no = a.form_no WHERE c.card_number = ?"
                );
                pst.setString(1,cardNumber);
                ResultSet rs = pst.executeQuery();

                if(!rs.next()){
                    JOptionPane.showMessageDialog(this, "Invalid Card Number!");
                }

                String status = rs.getString("status");
                if(!"ACTIVE".equals(status)){
                    JOptionPane.showMessageDialog(this,
                            "Card is " + status + ". Contact your bank.");
                    return;
                }

                String storedHash = rs.getString("pin_hash");
                String salt = rs.getString("salt");
                String enteredHash = PinEncryptor.hashPin(pinNumber, salt);
                formNo = rs.getString("form_no");

                if(!enteredHash.equals(storedHash)){
                    JOptionPane.showMessageDialog(this, "Wrong PIN!");
                    return;
                }

                String name = rs.getString("name");
                double balance = rs.getDouble("balance");
                String formNo = rs.getString("form_no");
                JOptionPane.showMessageDialog(this, "Welcome, " + name + "!");

                setVisible(false);
                new Transactions(formNo).setVisible(true);
//                new AtmScreen(formNo, name, balance).setVisible(true);
            }catch (Exception ae){
                ae.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ae.getMessage());
            }
        }
        else if(e.getSource() == Clear)
        {
            cardTextField.setText("");
            pinTextField.setText("");
        }
        else if(e.getSource() == SignUp)
        {
            setVisible(false);
            new SignUp().setVisible(true);
        }
    }
}
