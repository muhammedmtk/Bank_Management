package bank_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Transactions extends JFrame {
    JButton deposit, cashWithDraw, miniStatement, pinChange ,fastCash, balanceEnQuiry, exit;
    String formNo;
    Transactions(String formNo){
        this.formNo = formNo;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("Please Select Your Transaction");
        text.setBounds(210,300,700,35);
        text.setFont(new Font("System",Font.BOLD,16));
        text.setForeground(Color.white);
        image.add(text);

        deposit= new JButton("Deposit");
        deposit.setBounds(170,415,150,30);
        deposit.addActionListener(this::actionPerformed);
        image.add(deposit);

        cashWithDraw= new JButton("Cash Withdraw");
        cashWithDraw.setBounds(355,415,150,30);
        cashWithDraw.addActionListener(this::actionPerformed);
        image.add(cashWithDraw);

        fastCash= new JButton("Fast Cash");
        fastCash.setBounds(170,450,150,30);
        fastCash.addActionListener(this::actionPerformed);
        image.add(fastCash);

        miniStatement= new JButton("Mini Statement");
        miniStatement.setBounds(355,450,150,30);
        miniStatement.addActionListener(this::actionPerformed);
        image.add(miniStatement);

        pinChange= new JButton("Pin Change");
        pinChange.setBounds(170,485,150,30);
        pinChange.addActionListener(this::actionPerformed);
        image.add(pinChange);

        balanceEnQuiry= new JButton("Balance Enquiry");
        balanceEnQuiry.setBounds(355,485,150,30);
        balanceEnQuiry.addActionListener(this::actionPerformed);
        image.add(balanceEnQuiry);

        exit= new JButton("Exit");
        exit.setBounds(355,520,150,30);
        exit.addActionListener(this::actionPerformed);
        image.add(exit);

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == exit){
            System.exit(0);
        } else if (ae.getSource() == deposit) {
            setVisible(false);
            new Deposit(formNo).setVisible(true);
        } else if (ae.getSource() == cashWithDraw) {
            setVisible(false);
            new Withdraw(formNo).setVisible(true);
        } else if(ae.getSource() == fastCash){
            setVisible(false);
            new FastCash(formNo).setVisible(true);
        } else if (ae.getSource() == pinChange) {
            setVisible(false);
            new PinChange(formNo).setVisible(true);
        }else if (ae.getSource() == balanceEnQuiry) {
        setVisible(false);
        new BalanceEnquiry(formNo).setVisible(true);
    } else if (ae.getSource() == miniStatement) {
        setVisible(false);
        new MiniStatement(formNo).setVisible(true);
    }
    }

    public static void main(String args[]){
//        new Transactions();
    }
}
