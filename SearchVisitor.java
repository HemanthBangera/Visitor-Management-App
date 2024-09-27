package com.itep.VisitorManagementSystem;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchVisitor extends JFrame {

    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultArea;

    public SearchVisitor() {
        setTitle("Search Visitor");
        setSize(400, 300);
        setLayout(null);
        getContentPane().setBackground(Color.cyan);
        setLocationRelativeTo(null);

        JLabel searchLabel = new JLabel("Name/Phone:");
        searchLabel.setBounds(20, 20, 100, 30);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(120, 20, 150, 30);
        add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(280, 20, 80, 30);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchVisitor();
            }
        });
        add(searchButton);

        resultArea = new JTextArea();
        resultArea.setBounds(20, 60, 340, 180);
        resultArea.setBackground(Color.cyan);
        add(resultArea);

        setVisible(true);
    }

    private void searchVisitor() {
        String keyword = searchField.getText();

        try (Connection conn = new DatabaseHandler().connect()) {
            String sql = "SELECT * FROM visitors WHERE name LIKE ? OR phone LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            StringBuilder resultText = new StringBuilder();
            while (rs.next()) {
                resultText.append("ID: ").append(rs.getInt("id"))
                        .append(", Name: ").append(rs.getString("name"))
                        .append(", Phone: ").append(rs.getString("phone"))
                        .append(", Reason: ").append(rs.getString("reason_for_visit"))
                        .append(", Check-In: ").append(rs.getTimestamp("check_in_time"))
                        .append("\n");
            }
            resultArea.setText(resultText.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
		new SearchVisitor();
	}
}


