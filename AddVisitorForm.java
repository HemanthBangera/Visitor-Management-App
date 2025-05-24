import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class AddVisitorForm extends JFrame {
    private JTextField nameField, phoneField, reasonField;
    private JButton addButton;

    public AddVisitorForm() {
    	setTitle("Check In Visitor");
        setSize(600, 400); // Larger window size
        setLayout(null);
        getContentPane().setBackground(Color.cyan);
        setLocationRelativeTo(null);
        
        JLabel loginLabel = new JLabel("Check-In Visitor");
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

        JLabel reasonLabel = new JLabel("Reason for Visit:");
        reasonLabel.setBounds(130, 200, 150, 30);
        add(reasonLabel);

        reasonField = new JTextField();
        reasonField.setBounds(280, 200, 150, 30);
        add(reasonField);

        addButton = new JButton("Add Visitor");
        addButton.setBounds(210, 250, 120, 30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVisitor();
            }
        });
        add(addButton);

        setVisible(true);
    }
 // Mobile number validation method using regex
    public static boolean isValidMobile(String mobile) {
        // Regex pattern for a 10-digit mobile number
        String mobileRegex = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(mobileRegex);
        return pattern.matcher(mobile).matches();
    }
    private void addVisitor() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String reason = reasonField.getText();
        
        try (Connection conn = new DatabaseHandler().connect()) {
            String sql = "INSERT INTO visitors (name, phone, reason_for_visit,checkInTime) VALUES (?, ?, ?, NOW())";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setString(3, reason);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Visitor added successfully!");
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
