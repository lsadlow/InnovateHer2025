import javax.swing.*;
import java.awt.*;

public class GUI {
    public GUI() {

    }

    public void showSignUpDialog() {
        JFrame frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        JLabel enterNameLabel = new JLabel("Enter your full name:");
        JTextField enterNameTextField = new JTextField(20);

        JLabel enterEmailLabel = new JLabel("Enter your email:");

        JTextField enterEmailTextField = new JTextField(20);
        JLabel enterPasswordLabel = new JLabel("Enter your password:");

        JTextField enterPasswordTextField = new JTextField(20);

        JLabel enterConfirmPasswordLabel = new JLabel("Confirm your password:");

        JLabel enterLanguagesDialogLabel = new JLabel("Enter the programming languages you are proficient in:");
        JTextField enterLanguagesTextField = new JTextField(20);
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
