package com.itep.VisitorManagementSystem;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainMenu extends JFrame {
    private JButton viewUserButton, signOutButton, viewVisitorButton;

    public AdminMainMenu() {
        setTitle("Admin Main Menu");
        setSize(800, 600); // Larger window
        setLayout(null);
        getContentPane().setBackground(Color.cyan);
        setLocationRelativeTo(null);
        
        JLabel loginLabel = new JLabel("Admin Menu");
        loginLabel.setFont(new Font("Raleway",Font.BOLD,22));
        loginLabel.setBounds(330, 30, 150, 80);
        add(loginLabel);

        viewUserButton = new JButton("View Employees");
        viewUserButton.setBounds(130, 250, 150, 50);
        viewUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewUser();
            }
        });
        add(viewUserButton);
        
        viewVisitorButton = new JButton("View Visitors");
        viewVisitorButton.setBounds(330, 250, 150, 50);
        viewVisitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewVisitors();
            }
        });
        add(viewVisitorButton);

        signOutButton = new JButton("Sign Out");
        signOutButton.setBounds(530, 250, 150, 50);
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginForm();
                dispose();
            }
        });
        add(signOutButton);

        setVisible(true);
    }
}

