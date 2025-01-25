import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI {
    private User user;
    public GUI() {
        this.user = null;
    }

    public void showSignUpDialog() {
        JFrame frame = new JFrame("Sign Up");
        JPanel signUpPanel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        JLabel enterNameLabel = new JLabel("Enter your full name (no spaces):");
        JTextField enterNameTextField = new JTextField(20);

        JLabel enterEmailLabel = new JLabel("Enter your email:");

        JTextField enterEmailTextField = new JTextField(20);
        JLabel enterPasswordLabel = new JLabel("Enter your password:");
        JTextField enterPasswordTextField = new JTextField(20);

        JLabel enterConfirmPasswordLabel = new JLabel("Confirm your password:");
        JTextField enterConfirmPasswordTextField = new JTextField(20);

        JLabel enterLanguagesDialogLabel = new JLabel("Enter the programming languages you are proficient in:");
        JTextField enterLanguagesTextField = new JTextField(20);

        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.addActionListener(e -> {
            String username = enterNameTextField.getText();
            String email = enterEmailTextField.getText();
            String password = enterPasswordTextField.getText();
            if (!enterConfirmPasswordTextField.getText().equals(password)) {
                JOptionPane.showMessageDialog(null, "Error! Passwords do not match");
            }
            String languages = enterLanguagesTextField.getText();
            User newUser = new User(username, email, password, languages);
            this.user = newUser;
        });

        cancelButton.addActionListener(e -> {
            frame.dispose();
        });

        signUpPanel.add(enterNameLabel);
        signUpPanel.add(enterNameTextField);
        signUpPanel.add(enterEmailLabel);
        signUpPanel.add(enterEmailTextField);
        signUpPanel.add(enterPasswordLabel);
        signUpPanel.add(enterPasswordTextField);
        signUpPanel.add(enterConfirmPasswordLabel);
        signUpPanel.add(enterConfirmPasswordTextField);
        signUpPanel.add(enterLanguagesDialogLabel);
        signUpPanel.add(enterLanguagesTextField);
        signUpPanel.add(confirmButton);
        signUpPanel.add(cancelButton);
        frame.add(signUpPanel);
        frame.setVisible(true);
    }
    public void displayWelcomeMessage() {
        JOptionPane.showMessageDialog(null, "Welcome to our app!");
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.displayWelcomeMessage();
    }
}