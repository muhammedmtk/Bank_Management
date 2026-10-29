package bank_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;

public class Deposit extends JFrame {

    JTextField amountField;
    JButton deposit, back;
    String formNo;
    Deposit(String formNo){
        this.formNo = formNo;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("Enter the amount you want to deposit");
        text.setForeground(Color.white);
        text.setFont(new Font("system", Font.BOLD, 16));
        text.setBounds(170,300,400,20);
        image.add(text);

        amountField = new JTextField();
        amountField.setFont(new Font("system", Font.BOLD, 16));
        amountField.setBounds(170,350,320,25);
        image.add(amountField);

        deposit = new JButton("Deposit");
        deposit.setBounds(355,485,150,30);
        deposit.addActionListener(this::actionPerformed);
        image.add(deposit);

        back = new JButton("Back");
        back.setBounds(355,520,150,30);
        back.addActionListener(this::actionPerformed);
        image.add(back);

        setSize(900,900);
        setLocation(300,0);
        setVisible(true);
    }

    private void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deposit) {
            String text = amountField.getText().trim();
            if(text.equals("")){
                JOptionPane.showMessageDialog(this, "Please enter the amount you want to deposit!");
            }
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be positive!");
                return;
            }
                try {
                    Conn c = new Conn();
                    PreparedStatement pst = c.con.prepareStatement("UPDATE accounts set balance =balance + ? where form_no = ?");
                    pst.setDouble(1, amount);
                    pst.setString(2, formNo);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Deposited " + amount + " EGP!");
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
                    setVisible(true);
                }

            } else if (ae.getSource() == back) {
                setVisible(false);
                new Transactions(formNo).setVisible(true);
            }
        }
    public static void main(String args[]){
        new Deposit("");
    }
}
