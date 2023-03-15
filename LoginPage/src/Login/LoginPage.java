package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JFrame implements ActionListener {

    JLabel userLabel, passwordLabel;
    JTextField userTextField;
    JPasswordField passwordField;
    JButton loginButton;

    public LoginPage() {
        userLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        userTextField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        setLayout(new GridLayout(3, 2));
        add(userLabel);
        add(userTextField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel());
        add(loginButton);

        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = userTextField.getText();
        String password = new String(passwordField.getPassword());

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "Neeraj@2001");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username=? AND pass=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                // Navigate to the next page or perform any other action here
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!");
            }

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
