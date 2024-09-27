package com.itep.VisitorManagementSystem;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ForgotPasswordForm extends JFrame {
    private JTextField emailField;
    private JButton resetButton;

    public ForgotPasswordForm() {
        setTitle("Forgot Password");
        setSize(400, 250);
        setLayout(null);
        getContentPane().setBackground(Color.cyan);
        setLocationRelativeTo(null);
        
        JLabel loginLabel = new JLabel("Forgot Password");
        loginLabel.setFont(new Font("Raleway",Font.BOLD,18));
        loginLabel.setBounds(130,-25, 200, 100);
        add(loginLabel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 70, 150, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 70, 180, 30);
        add(emailField);

        resetButton = new JButton("Reset Password");
        resetButton.setBounds(120, 140, 150, 30);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetPassword();
            }
        });
        add(resetButton);

        setVisible(true);
    }

    private void resetPassword() {
        String email = emailField.getText();

        try (Connection conn = new DatabaseHandler().connect()) {
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String newPassword = JOptionPane.showInputDialog(this, "Enter new password:");
                String updateSql = "UPDATE users SET password = ? WHERE email = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setString(1, newPassword);
                updateStmt.setString(2, email);
                updateStmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Password reset successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Email not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


