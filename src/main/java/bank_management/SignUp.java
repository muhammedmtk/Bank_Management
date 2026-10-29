package bank_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import com.toedter.calendar.JDateChooser;

public class SignUp extends JFrame{
    long random;
    JTextField nameField,fatherNameField,emailField,addressField,cityField,stateField,pinCodeField;
    JRadioButton male, female, single, married, other;
    JButton nextButton;
    JDateChooser dateChooser;
    SignUp(){
        setLayout(null);
        setSize(850,850);
        setLocation(350,10);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);

        Random ran = new Random();
        random = Math.abs((ran.nextLong() % 9000L) + 1000L);


        JLabel FormNo = new JLabel("Application Form No: " + random);
        FormNo.setBounds(100, 20, 600, 50);
        FormNo.setFont(new Font("Raleway", Font.BOLD, 32));
        FormNo.setHorizontalAlignment(SwingConstants.CENTER);
        add(FormNo);

        JLabel PersonDetails = new JLabel("Page 1: Personal Details");
        PersonDetails.setBounds(150, 90, 500, 45);
        PersonDetails.setFont(new Font("Raleway", Font.BOLD, 25));
        PersonDetails.setHorizontalAlignment(SwingConstants.CENTER);
        add(PersonDetails);

        JLabel Name = new JLabel("Name");
        Name.setBounds(100, 160, 150, 40);
        Name.setFont(new Font("Raleway", Font.BOLD, 20));
        add(Name);

        nameField = new JTextField();
        nameField.setBounds(260, 160, 400, 40);
        nameField.setFont(new Font("Raleway", Font.PLAIN, 20));
        nameField.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        nameField.setBackground(Color.WHITE);
        nameField.setForeground(Color.BLACK);
        add(nameField);

        JLabel FatherName = new JLabel("Father's Name");
        FatherName.setBounds(100, 220, 250, 40);
        FatherName.setFont(new Font("Raleway", Font.BOLD, 20));
        add(FatherName);

        fatherNameField = new JTextField();
        fatherNameField.setBounds(260, 220, 400, 40);
        fatherNameField.setFont(new Font("Raleway", Font.PLAIN, 20));
        fatherNameField.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        fatherNameField.setBackground(Color.WHITE);
        fatherNameField.setForeground(Color.BLACK);
        add(fatherNameField);

        JLabel Gender = new JLabel("Gender");
        Gender.setBounds(100, 280, 200, 40);
        Gender.setFont(new Font("Raleway", Font.BOLD, 20));
        add(Gender);

        male = new JRadioButton("Male");
        male.setBounds(310, 280, 100, 40);
        male.setFont(new Font("Raleway", Font.PLAIN, 18));
        male.setBackground(Color.WHITE);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(430, 280, 120, 40);
        female.setFont(new Font("Raleway", Font.PLAIN, 18));
        female.setBackground(Color.WHITE);
        add(female);

        ButtonGroup groupGender = new ButtonGroup();
        groupGender.add(male);
        groupGender.add( female);

        JLabel dobLabel = new JLabel("Date of Birth");
        dobLabel.setBounds(100, 340, 200, 40);
        dobLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(dobLabel);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(260, 340, 400, 40);
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        add(dateChooser);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(100, 400, 200, 40);
        emailLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(260, 400, 400, 40);
        emailField.setFont(new Font("Raleway", Font.PLAIN, 20));
        emailField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        add(emailField);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setBounds(100, 460, 200, 40);
        addressLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(260, 460, 400, 40);
        addressField.setFont(new Font("Raleway", Font.PLAIN, 20));
        addressField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        add(addressField);


        JLabel cityLabel = new JLabel("City");
        cityLabel.setBounds(100, 520, 200, 40);
        cityLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(cityLabel);

        cityField = new JTextField();
        cityField.setBounds(260, 520, 400, 40);
        cityField.setFont(new Font("Raleway", Font.PLAIN, 20));
        cityField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        add(cityField);

        JLabel stateLabel = new JLabel("State");
        stateLabel.setBounds(100, 580, 200, 40);
        stateLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(stateLabel);

        stateField = new JTextField();
        stateField.setBounds(260, 580, 400, 40);
        stateField.setFont(new Font("Raleway", Font.PLAIN, 20));
        stateField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        add(stateField);

        JLabel pinCodeLabel = new JLabel("Pin Code");
        pinCodeLabel.setBounds(100, 640, 200, 40);
        pinCodeLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(pinCodeLabel);

        pinCodeField = new JTextField();
        pinCodeField.setBounds(260, 640, 400, 40);
        pinCodeField.setFont(new Font("Raleway", Font.PLAIN, 20));
        pinCodeField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        add(pinCodeField);

        JLabel martialStatus = new JLabel("Martial Status");
        martialStatus.setBounds(100, 700, 200, 40);
        martialStatus.setFont(new Font("Raleway", Font.BOLD, 20));
        add(martialStatus);

        single = new JRadioButton("Single");
        single.setBounds(310, 700, 120, 40);
        single.setFont(new Font("Raleway", Font.PLAIN, 18));
        single.setBackground(Color.WHITE);
        add(single);

        married = new JRadioButton("Married");
        married.setBounds(430, 700, 100, 40);
        married.setFont(new Font("Raleway", Font.PLAIN, 18));
        married.setBackground(Color.WHITE);
        add(married);

        other = new JRadioButton("Other");
        other.setBounds(550, 700, 100, 40);
        other.setFont(new Font("Raleway", Font.PLAIN, 18));
        other.setBackground(Color.WHITE);
        add(other);

        ButtonGroup groupMartialStatus = new ButtonGroup();
        groupMartialStatus.add(single);
        groupMartialStatus.add(married);
        groupMartialStatus.add(other);

        nextButton = new JButton("NEXT");
        nextButton.setBounds(350, 770, 200, 40);
        nextButton.setFont(new Font("Raleway", Font.BOLD, 24));
        nextButton.setBackground(Color.BLACK);
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusPainted(false);
        nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nextButton.addActionListener(this::actionPerformed);
        add(nextButton);

        revalidate();
        repaint();
    }

    public void actionPerformed(ActionEvent ae) {
        String formNo = ""+ random;
        String name = nameField.getText();
        String fname = fatherNameField.getText();
        String email = emailField.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String pinCode = pinCodeField.getText();
        String gender = null;
        if(male.isSelected()){
            gender = "Male";
        } else if (female.isSelected()){
            gender = "Female";
        }

        String martial = null;
        if(single.isSelected()){
            martial = "Single";
        } else if (married.isSelected()) {
            martial = "Married";
        } else if (other.isSelected()) {
            martial = "Other";
        }

        try {
            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name is Required");
            } else if (fname.equals("")) {
                JOptionPane.showMessageDialog(null, "Father's Name is Required");
            } else if (dob.equals("")) {
                JOptionPane.showMessageDialog(null, "Date of Birth is Required");
            } else {
                Conn c = new Conn();
                PreparedStatement pst = c.con.prepareStatement(
                        "INSERT INTO signup VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                );
                pst.setString(1, formNo);
                pst.setString(2, name);
                pst.setString(3, fname);
                pst.setString(4, email);
                pst.setString(5, dob);
                pst.setString(6, gender);
                pst.setString(7, martial);
                pst.setString(8, address);
                pst.setString(9, city);
                pst.setString(10, state);
                pst.setString(11, pinCode);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Saved Successfully");

                setVisible(false);
                new SignUpTwo(formNo).setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error In Saving Data");
        }

        }

    }
