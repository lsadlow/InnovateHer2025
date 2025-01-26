import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;

public class GUI implements Runnable {
    private User user;
    private DataInputStream in;
    private DataOutputStream out;

    public GUI(DataInputStream in, DataOutputStream out) {
        this.user = null;
        this.in = in;
        this.out = out;
    }

    public void run() {

    }

    public void showCreateProjectDialog() {
        JFrame frame = new JFrame("Create Project");
        JPanel createProjectPanel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createProjectPanel.setLayout(new BoxLayout(createProjectPanel, BoxLayout.Y_AXIS));
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        JLabel enterProjectNameLabel = new JLabel("Enter the name of your project:");
        JTextField enterProjectNameTextField = new JTextField(10);

        JLabel enterProjectDescriptionLabel = new JLabel("Enter a description of your project:");
        JTextField enterProjectDescriptionTextField = new JTextField(10);

        JLabel enterProjectLanguagesLabel = new JLabel("Enter the languages the project might use (with a space between each):");
        JTextField enterProjectLanguagesTextField = new JTextField(10);

        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");
        confirmButton.addActionListener(e -> {

        });
        cancelButton.addActionListener(e -> {
            frame.dispose();
        });
        createProjectPanel.add(enterProjectNameLabel);
        createProjectPanel.add(enterProjectNameTextField);
        createProjectPanel.add(enterProjectDescriptionLabel);
        createProjectPanel.add(enterProjectDescriptionTextField);
        createProjectPanel.add(enterProjectLanguagesLabel);
        createProjectPanel.add(enterProjectLanguagesTextField);
        createProjectPanel.add(confirmButton);
        createProjectPanel.add(cancelButton);
        frame.add(createProjectPanel);
        frame.setVisible(true);
    }
}
