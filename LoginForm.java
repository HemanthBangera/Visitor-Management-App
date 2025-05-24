
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, forgotPasswordButton, signOutButton;
    private JCheckBox showPasswordCheckBox;

    public LoginForm() {
        setTitle("Login");
        setSize(600, 400); // Larger window size
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.cyan);
        setLayout(null);
        
        JLabel loginLabel = new JLabel("Login Form");
        loginLabel.setFont(new Font("Raleway",Font.BOLD,22));
        loginLabel.setBounds(230, 10, 150, 80);
        add(loginLabel);
        
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(150, 100, 150, 30);
        add(usernameLabel);
        

        usernameField = new JTextField();
        usernameField.setBounds(260, 100, 150, 30);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(150, 150, 150, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(260, 150, 150, 30);
        add(passwordField);

        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setBounds(260, 190, 150, 30);
        showPasswordCheckBox.setBackground(Color.cyan);
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                }
            }
        });
        add(showPasswordCheckBox);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 250, 100, 30);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 loginUser();
            }
            
        });
        add(loginButton);

        forgotPasswordButton = new JButton("Forgot Password");
        forgotPasswordButton.setBounds(220, 250, 150, 30);
        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ForgotPasswordForm();
            }
        });
        add(forgotPasswordButton);

        signOutButton = new JButton("Register");
        signOutButton.setBounds(390, 250, 100, 30);
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrationForm(); // For demonstration; normally, this would be a redirect to the login screen
            }
        });
        add(signOutButton);

        setVisible(true);
    }

    private void loginUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try (Connection conn = new DatabaseHandler().connect()) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                JOptionPane.showMessageDialog(this, "Login successful!");

                if (role.equals("admin")) {
                    new AdminMainMenu();
                } else {
                    new UserMainMenu();
                }
               // dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

