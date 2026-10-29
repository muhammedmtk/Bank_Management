package bank_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PinChange extends JFrame {

    JPasswordField currentPinField, newPinField, confirmPinField;
    JButton changeBtn, back;
    String formNo;

    PinChange(String formNo) {
        this.formNo = formNo;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD, 20));
        text.setBounds(330, 270, 300, 30);
        image.add(text);

        JLabel l1 = new JLabel("Current PIN:");
        l1.setForeground(Color.white);
        l1.setFont(new Font("System", Font.BOLD, 16));
        l1.setBounds(170, 330, 150, 25);
        image.add(l1);

        currentPinField = new JPasswordField();
        currentPinField.setFont(new Font("System", Font.BOLD, 16));
        currentPinField.setBounds(330, 330, 200, 25);
        image.add(currentPinField);

        JLabel l2 = new JLabel("New PIN:");
        l2.setForeground(Color.white);
        l2.setFont(new Font("System", Font.BOLD, 16));
        l2.setBounds(170, 380, 150, 25);
        image.add(l2);

        newPinField = new JPasswordField();
        newPinField.setFont(new Font("System", Font.BOLD, 16));
        newPinField.setBounds(330, 380, 200, 25);
        image.add(newPinField);

        JLabel l3 = new JLabel("Confirm New PIN:");
        l3.setForeground(Color.white);
        l3.setFont(new Font("System", Font.BOLD, 16));
        l3.setBounds(170, 430, 160, 25);
        image.add(l3);

        confirmPinField = new JPasswordField();
        confirmPinField.setFont(new Font("System", Font.BOLD, 16));
        confirmPinField.setBounds(330, 430, 200, 25);
        image.add(confirmPinField);

        changeBtn = new JButton("CHANGE PIN");
        changeBtn.setBounds(330, 490, 200, 30);
        changeBtn.addActionListener(this::actionPerformed);
        image.add(changeBtn);

        back = new JButton("BACK");
        back.setBounds(330, 530, 200, 30);
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

        if (ae.getSource() == changeBtn) {
            String currentPin = new String(currentPinField.getPassword());
            String newPin = new String(newPinField.getPassword());
            String confirmPin = new String(confirmPinField.getPassword());

            if (!newPin.equals(confirmPin)) {
                JOptionPane.showMessageDialog(this, "New PIN and Confirm PIN do not match!");
                return;
            }
            if (!newPin.matches("\\d{4}")) {
                JOptionPane.showMessageDialog(this, "New PIN must be exactly 4 digits!");
                return;
            }

            try {
                Conn c = new Conn();

                PreparedStatement ps = c.con.prepareStatement(
                        "SELECT pin_hash, salt FROM cards WHERE form_no = ?");
                ps.setString(1, formNo);
                ResultSet rs = ps.executeQuery();

                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "Card not found!");
                    return;
                }

                String storedHash = rs.getString("pin_hash");
                String oldSalt = rs.getString("salt");
                String currentHash = PinEncryptor.hashPin(currentPin, oldSalt);

                if (!currentHash.equals(storedHash)) {
                    JOptionPane.showMessageDialog(this, "Current PIN is wrong!");
                    return;
                }

                String newSalt = PinEncryptor.generateSalt();
                String newHash = PinEncryptor.hashPin(newPin, newSalt);

                PreparedStatement upd = c.con.prepareStatement(
                        "UPDATE cards SET pin_hash = ?, salt = ? WHERE form_no = ?");
                upd.setString(1, newHash);
                upd.setString(2, newSalt);
                upd.setString(3, formNo);
                upd.executeUpdate();

                JOptionPane.showMessageDialog(this, "PIN changed successfully!");
                setVisible(false);
                new Transactions(formNo).setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }
}