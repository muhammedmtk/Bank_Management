package bank_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;

public class BalanceEnquiry extends JFrame {

    private static final int AREA_X = 170;
    private static final int AREA_W = 370;
    private static final int TITLE_Y = 280;
    private static final int CONTENT_Y = 340;
    private static final int BUTTON_Y = 490;

    private final String formNo;

    BalanceEnquiry(String formNo) {
        this.formNo = formNo;
        setLayout(null);


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel title = new JLabel("BALANCE ENQUIRY", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("System", Font.BOLD, 20));
        title.setBounds(AREA_X, TITLE_Y, AREA_W, 30);
        image.add(title);

        JLabel balanceLabel = new JLabel("Loading...", SwingConstants.CENTER);
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setFont(new Font("System", Font.BOLD, 18));
        balanceLabel.setBounds(AREA_X, CONTENT_Y, AREA_W, 30);
        image.add(balanceLabel);

        JButton back = new JButton("BACK");
        back.setBounds(AREA_X + (AREA_W - 150) / 2, BUTTON_Y, 150, 30);
        back.addActionListener(this::actionPerformed);
        image.add(back);

        try {
            Conn c = new Conn();
            PreparedStatement ps = c.con.prepareStatement(
                    "SELECT balance FROM accounts WHERE form_no = ?");
            ps.setString(1, formNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                balanceLabel.setText(String.format(Locale.US,
                        "Your Balance: %,.2f EGP", rs.getDouble("balance")));
            } else {
                balanceLabel.setText("Account not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            balanceLabel.setText("Error loading balance");
        }

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(formNo).setVisible(true);
    }
}