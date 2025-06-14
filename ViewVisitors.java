import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewVisitors extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JTable visitorTable;
    private DefaultTableModel tableModel;

    public ViewVisitors() {
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
                searchVisitors();
            }
        });
        add(searchButton);

        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Phone", "Reason", "Check-In Time", "Check-Out Time"}, 0);
        visitorTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(visitorTable);
        scrollPane.setBounds(50, 80, 700, 400);
        add(scrollPane);

        setVisible(true);
    }

    private void searchVisitors() {
    String searchName = searchField.getText().trim(); // Trim whitespace
    tableModel.setRowCount(0); // Clear previous rows

    try (Connection conn = new DatabaseHandler().connect()) {
        String sql;
        PreparedStatement stmt;

        if (!searchName.isEmpty()) {
            sql = "SELECT * FROM visitors WHERE name LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + searchName + "%");
        } else {
            sql = "SELECT * FROM visitors";
            stmt = conn.prepareStatement(sql);
        }

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String phone = rs.getString("phone");
            String reason = rs.getString("reason_for_visit");
            String checkInTime = rs.getString("checkInTime");
            String checkOutTime = rs.getString("checkOutTime");

            tableModel.addRow(new Object[]{id, name, phone, reason, checkInTime, checkOutTime});
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
    }
}

}

