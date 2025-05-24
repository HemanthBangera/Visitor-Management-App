
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMainMenu extends JFrame {
    private JButton addVisitorButton, viewVisitorButton, checkOutButton, signOutButton;

    public UserMainMenu() {
        setTitle("User Main Menu");
        setSize(800, 600); // Larger window
        setLayout(null);
        getContentPane().setBackground(Color.cyan);
        setLocationRelativeTo(null);
        
        JLabel loginLabel = new JLabel("Visitor Management System");
        loginLabel.setFont(new Font("Raleway",Font.BOLD,30));
        loginLabel.setBounds(200, 50, 400, 80);
        add(loginLabel);

        addVisitorButton = new JButton("Check In Visitor");
        addVisitorButton.setBounds(100, 250, 150, 50);
        addVisitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddVisitorForm();
            }
        });
        add(addVisitorButton);

        checkOutButton = new JButton("Check Out Visitor");
        checkOutButton.setBounds(250, 250, 150, 50);
        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckOutVisitorForm();
            }
        });
        add(checkOutButton);
        
        viewVisitorButton = new JButton("View Visitors");
        viewVisitorButton.setBounds(400, 250, 150, 50);
        viewVisitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewVisitors();
            }
        });
        add(viewVisitorButton);

        signOutButton = new JButton("Sign Out");
        signOutButton.setBounds(550, 250, 150, 50);
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
