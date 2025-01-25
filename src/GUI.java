import javax.swing.*;

public class GUI {
    public GUI() {

    }

    public static void displayWelcomeMessage() {
        JOptionPane.showMessageDialog(null, "Welcome to our app!");
    }

    public static void main(String[] args) {
        displayWelcomeMessage();
    }
}
