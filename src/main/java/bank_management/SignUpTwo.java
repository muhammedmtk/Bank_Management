package bank_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class SignUpTwo extends JFrame{
    JComboBox<String> categoryCombo;
    JTextField occupationField, employerField, mobileField, nationalIdField, nationalityField;
    JComboBox<String> incomeCombo, qualificationCombo, purposeCombo;
    JRadioButton resident, nonResident;
    JTextField socialInsuranceField, taxRegField;
    JButton nextButton;
    String formNo;

    SignUpTwo(String formNo){
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");
        setSize(900, 1050);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        this.formNo = formNo;

        JLabel pageTitle = new JLabel("Page 2: Financial & Employment Details");
        pageTitle.setBounds(100, 60, 700, 45);
        pageTitle.setFont(new Font("Raleway", Font.BOLD, 25));
        add(pageTitle);

        JLabel categoryLabel = new JLabel("Account Type:");
        categoryLabel.setBounds(100, 150, 200, 40);
        categoryLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(categoryLabel);

        String[] accountTypes = {"Savings Account", "Current Account",
                "Fixed Deposit", "Certificate of Deposit",
                "Salary Account"};
        categoryCombo = new JComboBox<>(accountTypes);
        categoryCombo.setBounds(310, 150, 400, 40);
        categoryCombo.setFont(new Font("Raleway", Font.PLAIN, 20));
        categoryCombo.setBackground(Color.WHITE);
        add(categoryCombo);

        JLabel occupationLabel = new JLabel("Occupation:");
        occupationLabel.setBounds(100, 220, 200, 40);
        occupationLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(occupationLabel);

        occupationField = new JTextField();
        occupationField.setBounds(310, 220, 400, 40);
        occupationField.setFont(new Font("Raleway", Font.PLAIN, 20));
        occupationField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        occupationField.setBackground(Color.WHITE);
        add(occupationField);

        JLabel employerLabel = new JLabel("Employer:");
        employerLabel.setBounds(100, 290, 200, 40);
        employerLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(employerLabel);

        employerField = new JTextField();
        employerField.setBounds(310, 290, 400, 40);
        employerField.setFont(new Font("Raleway", Font.PLAIN, 20));
        employerField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        employerField.setBackground(Color.white);
        add(employerField);

        JLabel incomeLabel = new JLabel("Monthly Income:");
        incomeLabel.setBounds(100, 360, 200, 40);
        incomeLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(incomeLabel);

        String[] incomeRanges = {"Below 5,000 EGP", "5,000 - 10,000 EGP",
                "10,000 - 20,000 EGP", "20,000 - 50,000 EGP",
                "Above 50,000 EGP"};
        incomeCombo = new JComboBox<>(incomeRanges);
        incomeCombo.setBounds(310, 360, 400, 40);
        incomeCombo.setFont(new Font("Raleway", Font.PLAIN, 20));
        incomeCombo.setBackground(Color.WHITE);
        add(incomeCombo);

        JLabel qualificationLabel = new JLabel("Qualification:");
        qualificationLabel.setBounds(100, 430, 200, 40);
        qualificationLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(qualificationLabel);

        String[] qualifications = {"Undergraduate", "Bachelor's Degree",
                "Master's Degree", "PhD",
                "Diploma", "High School", "Below High School"};
        qualificationCombo = new JComboBox<>(qualifications);
        qualificationCombo.setBounds(310, 430, 400, 40);
        qualificationCombo.setFont(new Font("Raleway", Font.PLAIN, 20));
        qualificationCombo.setBackground(Color.WHITE);
        add(qualificationCombo);

        JLabel mobileLabel = new JLabel("Mobile Number:");
        mobileLabel.setBounds(100, 500, 200, 40);
        mobileLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(mobileLabel);

        mobileField = new JTextField();
        mobileField.setBounds(310, 500, 400, 40);
        mobileField.setFont(new Font("Raleway", Font.PLAIN, 20));
        mobileField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        mobileField.setBackground(Color.WHITE);
        mobileField.setToolTipText("Example:01276051294");
        add(mobileField);

        JLabel nidLabel = new JLabel("National ID:");
        nidLabel.setBounds(100, 570, 200, 40);
        nidLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(nidLabel);

        nationalIdField = new JTextField();
        nationalIdField.setBounds(310, 570, 400, 40);
        nationalIdField.setFont(new Font("Raleway", Font.PLAIN, 20));
        nationalIdField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        nationalIdField.setBackground(Color.WHITE);
        nationalIdField.setToolTipText("Example: 29909893892819");
        add(nationalIdField);

        JLabel nationalLabel = new JLabel("Nationality:");
        nationalLabel.setBounds(100, 640, 200, 40);
        nationalLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(nationalLabel);

        nationalityField = new JTextField();
        nationalityField.setBounds(310, 640, 400, 40);
        nationalityField.setFont(new Font("Raleway", Font.PLAIN, 20));
        nationalityField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        nationalityField.setBackground(Color.WHITE);
        nationalityField.setText("Egyptian");
        add(nationalityField);

        JLabel purposeLabel = new JLabel("Purpose:");
        purposeLabel.setBounds(100, 710, 200, 40);
        purposeLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(purposeLabel);

        String[] purposes = {"Salary", "Business", "Savings",
                "Investment", "Education", "Other"};
        purposeCombo = new JComboBox<>(purposes);
        purposeCombo.setBounds(310, 710, 400, 40);
        purposeCombo.setFont(new Font("Raleway", Font.PLAIN, 20));
        purposeCombo.setBackground(Color.WHITE);
        add(purposeCombo);

        JLabel residentLabel = new JLabel("Resident Status:");
        residentLabel.setBounds(100, 780, 200, 40);
        residentLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(residentLabel);

        resident = new JRadioButton("Resident");
        resident.setBounds(310, 780, 120, 40);
        resident.setFont(new Font("Raleway", Font.PLAIN, 18));
        resident.setBackground(Color.WHITE);
        resident.setSelected(true);
        add(resident);

        nonResident = new JRadioButton("Non-Resident");
        nonResident.setBounds(450, 780, 150, 40);
        nonResident.setFont(new Font("Raleway", Font.PLAIN, 18));
        nonResident.setBackground(Color.WHITE);
        add(nonResident);

        ButtonGroup resGroup = new ButtonGroup();
        resGroup.add(resident);
        resGroup.add(nonResident);

        JLabel socialInsuranceLabel = new JLabel("Social Insurance No.:");
        socialInsuranceLabel.setBounds(100, 850, 220, 40);
        socialInsuranceLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(socialInsuranceLabel);

        socialInsuranceField = new JTextField();
        socialInsuranceField.setBounds(330, 850, 380, 40);
        socialInsuranceField.setFont(new Font("Raleway", Font.PLAIN, 20));
        socialInsuranceField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        socialInsuranceField.setBackground(Color.WHITE);
        add(socialInsuranceField);

        JLabel taxLabel = new JLabel("Tax Card No.:");
        taxLabel.setBounds(100, 920, 200, 40);
        taxLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(taxLabel);

        taxRegField = new JTextField();
        taxRegField.setBounds(310, 920, 400, 40);
        taxRegField.setFont(new Font("Raleway", Font.PLAIN, 20));
        taxRegField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        taxRegField.setBackground(Color.WHITE);
        add(taxRegField);

        nextButton = new JButton("NEXT");
        nextButton.setBounds(350, 990, 200, 45);
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
        if(ae.getSource() == nextButton){
            if (nationalIdField.getText().length() != 14) {
                JOptionPane.showMessageDialog(null, "nationalId must be 14 numbers");
                return;
            }
            if (mobileField.getText().length() < 11) {
                JOptionPane.showMessageDialog(null, "mobile number must be 11 numbers");
                return;
            }

            String accountType = (String) categoryCombo.getSelectedItem();
            String occupation = occupationField.getText();
            String employer = employerField.getText();
            String income = (String) incomeCombo.getSelectedItem();
            String qualification = (String) qualificationCombo.getSelectedItem();
            String mobile = mobileField.getText();
            String nationalId = nationalIdField.getText();
            String nationality = nationalityField.getText();
            String purpose = (String) purposeCombo.getSelectedItem();
            String resStatus = resident.isSelected() ? "Resident" : "Non-Resident";
            String socialIns = socialInsuranceField.getText();
            String taxCard = taxRegField.getText();

            try {
                    Conn c = new Conn();
                    PreparedStatement pst = c.con.prepareStatement(
                            "INSERT INTO signupTwo VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)"
                    );
                    pst.setString(1, formNo);
                    pst.setString(2, accountType);
                    pst.setString(3, occupation);
                    pst.setString(4, employer);
                    pst.setString(5, income);
                    pst.setString(6, qualification);
                    pst.setString(7, mobile);
                    pst.setString(8, nationalId);
                    pst.setString(9, nationality);
                    pst.setString(10, purpose);
                    pst.setString(11, resStatus);
                    pst.setString(12, socialIns);
                    pst.setString(13, taxCard);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Saved Successfully");
                setVisible(false);
                new SignupThree(formNo).setVisible(true);
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error In Saving Data");
            }
        }
    }

}

