
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class RegistrationForm extends JFrame {
    private JTextField usernameField, emailField;
    private JPasswordField passwordField, confirmPasswordField;
    private JComboBox<String> roleComboBox;
    private JButton registerButton;

    public RegistrationForm() {
        setTitle("Register");
        setSize(600, 400); // Larger window size
        setLayout(null);
        getContentPane().setBackground(Color.cyan);
        setLocationRelativeTo(null);
        
        JLabel loginLabel = new JLabel("Registration");
        loginLabel.setFont(new Font("Raleway",Font.BOLD,22));
        loginLabel.setBounds(210, -30, 200, 100);
        add(loginLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(130, 50, 150, 30);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(280, 50, 150, 30);
        add(usernameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(130, 100, 150, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(280, 100, 150, 30);
        add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(130, 150, 150, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(280, 150, 150, 30);
        add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Re-enter Password:");
        confirmPasswordLabel.setBounds(130, 200, 150, 30);
        add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(280, 200, 150, 30);
        add(confirmPasswordField);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(130, 250, 150, 30);
        add(roleLabel);

        roleComboBox = new JComboBox<>(new String[]{"user", "admin"});
        roleComboBox.setBounds(280, 250, 150, 30);
        add(roleComboBox);

        registerButton = new JButton("Register");
        registerButton.setBounds(210, 300, 120, 30);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        add(registerButton);

        setVisible(true);
    }

 // email validation method using regex
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    
 
    private void registerUser() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String role = (String) roleComboBox.getSelectedItem();
        
        if (!isValidEmail(email)) {
        	JOptionPane.showMessageDialog(this, "Email is Invalid!");
        }
        else
        {

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!");
            return;
        }
        else if(password.equals(null)){
        	JOptionPane.showMessageDialog(this, "Password should not be Empty!");
        }
        else {

        try (Connection conn = new DatabaseHandler().connect()) {
            String sql = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, role);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "User registered successfully!");
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
        }
        }
    }
}
