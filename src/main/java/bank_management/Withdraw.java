package bank_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Withdraw extends JFrame {

    JTextField amountField;
    JButton withdraw, back;
    String formNo;

    Withdraw(String formNo) {
        this.formNo = formNo;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Enter the amount you want to withdraw");
        text.setForeground(Color.white);
        text.setFont(new Font("system", Font.BOLD, 16));
        text.setBounds(170, 300, 400, 20);
        image.add(text);

        amountField = new JTextField();
        amountField.setFont(new Font("system", Font.BOLD, 16));
        amountField.setBounds(170, 350, 320, 25);
        image.add(amountField);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(355, 485, 150, 30);
        withdraw.addActionListener(this::actionPerformed);
        image.add(withdraw);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this::actionPerformed);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setVisible(true);
    }

    private void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == withdraw) {

            String text = amountField.getText().trim();
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the amount!");
                return;
            }

            double amount;
            try {
                amount = Double.parseDouble(text);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Enter a valid number!");
                return;
            }

            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be positive!");
                return;
            }

            try {
                Conn c = new Conn();

                PreparedStatement pst = c.con.prepareStatement(
                        "SELECT balance FROM accounts WHERE form_no = ?");
                pst.setString(1, formNo);
                ResultSet rs = pst.executeQuery();

                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "Account not found!");
                    return;
                }
                double balance = rs.getDouble("balance");

                if (amount > balance) {
                    JOptionPane.showMessageDialog(this, "Not enough balance!");
                    return;
                }

                PreparedStatement upd = c.con.prepareStatement(
                        "UPDATE accounts SET balance = balance - ? WHERE form_no = ?");
                upd.setDouble(1, amount);
                upd.setString(2, formNo);
                upd.executeUpdate();

                JOptionPane.showMessageDialog(this, "Withdraw " + amount + " EGP!");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }

        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(formNo).setVisible(true);
        }
    }
}