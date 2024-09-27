package com.itep.VisitorManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewUser extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JTable UserTable;
    private DefaultTableModel tableModel;

    public ViewUser() {
        setTitle("View Visitors");
        setSize(800, 600); // Larger window size
        setLayout(null);
        getContentPane().setBackground(Color.cyan);
        setLocationRelativeTo(null);

        JLabel searchLabel = new JLabel("Search by Name:");
        searchLabel.setBounds(50, 30, 150, 30);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(150, 30, 200, 30);
        add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(370, 30, 100, 30);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchUser();
            }
        });
        add(searchButton);

        tableModel = new DefaultTableModel(new String[]{"Username", "Password", "Email"}, 0);
        UserTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(UserTable);
        scrollPane.setBounds(50, 80, 700, 400);
        add(scrollPane);

        setVisible(true);
    }

    private void searchUser() {
        String searchName = searchField.getText();
        tableModel.setRowCount(0);

        if(searchName!="") {
        try (Connection conn = new DatabaseHandler().connect()) {
            String sql = "SELECT * FROM users WHERE username LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + searchName + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");

                tableModel.addRow(new Object[]{username, password, email});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       }
        else {
        	try (Connection conn = new DatabaseHandler().connect()) {
        		String sql = "SELECT * FROM user WHERE username LIKE ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, "%" + searchName + "%");
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String email = rs.getString("email");

                    tableModel.addRow(new Object[]{username, password, email});
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
