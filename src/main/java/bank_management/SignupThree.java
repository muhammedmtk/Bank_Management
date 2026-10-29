package bank_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class SignupThree extends JFrame implements ActionListener {

    String receivedFormNo;
    JTextField cardNumberDisplay;
    JPasswordField pinField, confirmPinField;
    JComboBox<Integer> expiryMonth, expiryYear;
    JButton nextButton;

    public SignupThree(String formNo) {
        this.receivedFormNo = formNo;

        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 3");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel pageTitle = new JLabel("Page 3: Card Issuance");
        pageTitle.setBounds(100, 60, 700, 45);
        pageTitle.setFont(new Font("Raleway", Font.BOLD, 25));
        add(pageTitle);

        JLabel cardLabel = new JLabel("Card Number:");
        cardLabel.setBounds(100, 150, 200, 40);
        cardLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(cardLabel);

        cardGenerator CardGenerator = null;
        String generatedCard = cardGenerator.generateCardNumber();
        cardNumberDisplay = new JTextField(generatedCard);
        cardNumberDisplay.setBounds(310, 150, 400, 40);
        cardNumberDisplay.setFont(new Font("Raleway", Font.PLAIN, 20));
        cardNumberDisplay.setEditable(false);  // ⚠️ Not typed by the customer
        cardNumberDisplay.setBackground(new Color(240, 240, 240));
        cardNumberDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        add(cardNumberDisplay);

        JLabel pinLabel = new JLabel("Set Your PIN (4 digits):");
        pinLabel.setBounds(100, 220, 250, 40);
        pinLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(pinLabel);

        pinField = new JPasswordField();
        pinField.setBounds(360, 220, 200, 40);
        pinField.setFont(new Font("Raleway", Font.PLAIN, 20));
        pinField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        add(pinField);

        JLabel confirmLabel = new JLabel("Confirm PIN:");
        confirmLabel.setBounds(100, 290, 200, 40);
        confirmLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(confirmLabel);

        confirmPinField = new JPasswordField();
        confirmPinField.setBounds(310, 290, 200, 40);
        confirmPinField.setFont(new Font("Raleway", Font.PLAIN, 20));
        confirmPinField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        add(confirmPinField);

        JLabel expiryLabel = new JLabel("Expiry Date:");
        expiryLabel.setBounds(100, 360, 200, 40);
        expiryLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(expiryLabel);

        expiryMonth = new JComboBox<>();
        for (int i = 1; i <= 12; i++) expiryMonth.addItem(i);
        expiryMonth.setBounds(310, 360, 100, 40);
        expiryMonth.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(expiryMonth);

        JLabel slash = new JLabel("/");
        slash.setBounds(420, 360, 20, 40);
        slash.setFont(new Font("Raleway", Font.BOLD, 20));
        add(slash);

        expiryYear = new JComboBox<>();
        int currentYear = LocalDate.now().getYear();
        for (int i = currentYear; i <= currentYear + 10; i++) expiryYear.addItem(i);
        expiryYear.setBounds(450, 360, 100, 40);
        expiryYear.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(expiryYear);
        
        nextButton = new JButton("ISSUE CARD");
        nextButton.setBounds(350, 450, 250, 45);
        nextButton.setFont(new Font("Raleway", Font.BOLD, 24));
        nextButton.setBackground(new Color(0, 100, 0));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusPainted(false);
        nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nextButton.addActionListener(this);
        add(nextButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == nextButton) {

            String pin = new String(pinField.getPassword());
            String confirmPin = new String(confirmPinField.getPassword());

            if (pin.length() != 4) {
                JOptionPane.showMessageDialog(this, "PIN must be exactly 4 digits!");
                return;
            }
            if (!pin.matches("\\d{4}")) {
                JOptionPane.showMessageDialog(this, "PIN must contain digits only!");
                return;
            }
            if (!pin.equals(confirmPin)) {
                JOptionPane.showMessageDialog(this, "PINs do not match!");
                return;
            }

            try {
                Conn c = new Conn();

                String salt = PinEncryptor.generateSalt();
                String pinHash = PinEncryptor.hashPin(pin, salt);

                String cvv = String.format("%03d", new java.util.Random().nextInt(1000));
                String cvvSalt = PinEncryptor.generateSalt();
                String cvvHash = PinEncryptor.hashPin(cvv, cvvSalt);

                int month = (Integer) expiryMonth.getSelectedItem();
                int year = (Integer) expiryYear.getSelectedItem();
                String expiryDate = year + "-" + String.format("%02d", month) + "-01";

                PreparedStatement pst = c.con.prepareStatement(
                        "INSERT INTO cards (form_no, card_number, pin_hash, salt, cvv_hash, cvv_salt, expiry_date) VALUES (?, ?, ?, ?, ?, ?, ?)"
                );
                pst.setString(1, receivedFormNo);
                pst.setString(2, cardNumberDisplay.getText());
                pst.setString(3, pinHash);
                pst.setString(4, salt);
                pst.setString(5, cvvHash);
                pst.setString(6, cvvSalt);
                pst.setString(7, expiryDate);
                pst.executeUpdate();

                PreparedStatement maxPst = c.con.prepareStatement(
                        "SELECT COALESCE(MAX(CAST(account_number AS UNSIGNED)), 1000000000) + 1 FROM accounts"
                );
                ResultSet maxRs = maxPst.executeQuery();
                maxRs.next();
                String newAccountNumber = maxRs.getString(1);

                PreparedStatement accPst = c.con.prepareStatement(
                        "INSERT INTO accounts (account_number, form_no, balance, account_type) VALUES (?, ?, 0.00, ?)"
                );
                accPst.setString(1, newAccountNumber);
                accPst.setString(2, receivedFormNo);
                accPst.setString(3, "Savings");
                accPst.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Card issued!\nCard: " + cardNumberDisplay.getText() +
                                "\nAccount: " + newAccountNumber +
                                "\nBalance: 0.00 EGP");

                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }
    }
