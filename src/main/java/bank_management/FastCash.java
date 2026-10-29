package bank_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FastCash extends JFrame {

    JButton btn100, btn200, btn300, btn400, btn500, btn1000, back;
    String formNo;

    FastCash(String formNo) {
        this.formNo = formNo;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Select Withdraw Amount");
        text.setBounds(210, 300, 700, 35);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setForeground(Color.white);
        image.add(text);

        btn100 = new JButton("100 EGP");
        btn100.setBounds(170, 415, 150, 30);
        btn100.addActionListener(this::actionPerformed);
        image.add(btn100);

        btn200 = new JButton("200 EGP");
        btn200.setBounds(355, 415, 150, 30);
        btn200.addActionListener(this::actionPerformed);
        image.add(btn200);

        btn300 = new JButton("300 EGP");
        btn300.setBounds(170, 450, 150, 30);
        btn300.addActionListener(this::actionPerformed);
        image.add(btn300);

        btn400 = new JButton("400 EGP");
        btn400.setBounds(355, 450, 150, 30);
        btn400.addActionListener(this::actionPerformed);
        image.add(btn400);

        btn500 = new JButton("500 EGP");
        btn500.setBounds(170, 485, 150, 30);
        btn500.addActionListener(this::actionPerformed);
        image.add(btn500);

        btn1000 = new JButton("1000 EGP");
        btn1000.setBounds(355, 485, 150, 30);
        btn1000.addActionListener(this::actionPerformed);
        image.add(btn1000);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this::actionPerformed);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(formNo).setVisible(true);
            return;
        }


        String amountText = ((JButton) ae.getSource()).getText().replace(" EGP", "");
        double balanceAmount;
        try {
            balanceAmount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            return;
        }

        try {
            Conn c = new Conn();

            PreparedStatement ps = c.con.prepareStatement(
                    "SELECT balance FROM accounts WHERE form_no = ?");
            ps.setString(1, formNo);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "Account not found!");
                return;
            }
            double balance = rs.getDouble("balance");

            if (balanceAmount > balance) {
                JOptionPane.showMessageDialog(this, "Not enough balance!");
                return;
            }

            PreparedStatement upd = c.con.prepareStatement(
                    "UPDATE accounts SET balance = balance - ? WHERE form_no = ?");
            upd.setDouble(1, balanceAmount);
            upd.setString(2, formNo);
            upd.executeUpdate();

            JOptionPane.showMessageDialog(this, "Withdraw " + amountText + " EGP!");
            setVisible(false);
            new Transactions(formNo).setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}