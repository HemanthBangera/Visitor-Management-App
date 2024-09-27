package com.itep.VisitorManagementSystem;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CheckOutVisitorForm extends JFrame {
    private JTextField nameField, phoneField;
    private JButton checkOutButton;

    public CheckOutVisitorForm() {
        setTitle("Check-Out Visitor");
        setSize(600, 400); // Larger window size
        setLayout(null);
        getContentPane().setBackground(Color.cyan);
        setLocationRelativeTo(null);
        
        JLabel loginLabel = new JLabel("Check-Out Visitor");
        loginLabel.setFont(new Font("Raleway",Font.BOLD,22));
        loginLabel.setBounds(210, 10, 200, 100);
        add(loginLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(130, 100, 150, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(280, 100, 150, 30);
        add(nameField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(130, 150, 150, 30);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(280, 150, 150, 30);
        add(phoneField);

        checkOutButton = new JButton("Check Out");
        checkOutButton.setBounds(210, 200, 120, 30);
        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkOutVisitor();
            }
        });
        add(checkOutButton);

        setVisible(true);
    }

    private void checkOutVisitor() {
        String name = nameField.getText();
        String phone = phoneField.getText();

        try (Connection conn = new DatabaseHandler().connect()) {
            String sql = "UPDATE visitors SET check_out_time = NOW() WHERE name = ? AND phone = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, phone);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Visitor checked out successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Visitor not found or already checked out!");
            }
            dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

