import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DisplayProjects extends JPanel {
    private JPanel swipePanel;
    private int xPosition = 350; // Initial position (off-screen)
    private JButton nextButton;
    private JButton backButton;

    public DisplayProjects() {
        setLayout(null); // Use absolute positioning
        setPreferredSize(new Dimension(1800, 1000));
        setBackground(Color.black); // Soft pastel background

        // Create and add the "Current Projects" label
        JLabel currentProjectsLabel = new JLabel("Current Projects");
        currentProjectsLabel.setFont(new Font("Arial", Font.BOLD, 36));
        currentProjectsLabel.setForeground(Color.white); // Deep blue-gray
        currentProjectsLabel.setBounds(515, 90, 400, 50);
        add(currentProjectsLabel);

        // Initial swipe panel
        swipePanel = createSwipePanel(new Color(0xFFB6C1)); // Light pink panel
        swipePanel.setBounds(xPosition, 150, 600, 650); // Larger dimensions
        add(swipePanel);

        // "Next" button
        nextButton = createButton("Next", new Color(0x27AE60)); // Green theme
        nextButton.setBounds(700, 825, 120, 50);
        add(nextButton);

        // "Back" button
        backButton = createButton("Back", new Color(0xffc2d1)); // Blue theme
        backButton.setBounds(500, 825, 120, 50);
        add(backButton);

        // "Next" button action listener
        nextButton.addActionListener(e -> {
            // Move the current panel to the right and create a new one
            Timer swipeOutTimer = new Timer(10, (ActionEvent evt) -> {
                if (xPosition < 900) { // Move current panel to the right
                    xPosition += 10;
                    swipePanel.setBounds(xPosition, 150, 600, 650);
                } else {
                    ((Timer) evt.getSource()).stop(); // Stop the animation
                    remove(swipePanel); // Remove the old panel
                    createNewSwipePanel(); // Create a new swipe panel
                }
            });
            swipeOutTimer.start();
        });

        // "Back" button action listener
        backButton.addActionListener(e -> {
            // Move the current panel to the left and bring back the previous one
            Timer swipeInTimer = new Timer(10, (ActionEvent evt) -> {
                if (xPosition > 200) { // Move current panel to the left
                    xPosition -= 10;
                    swipePanel.setBounds(xPosition, 150, 600, 650);
                } else {
                    ((Timer) evt.getSource()).stop(); // Stop the animation
                }
            });
            swipeInTimer.start();
        });
    }

    private JPanel createSwipePanel(Color backgroundColor) {
        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setLayout(null);
        panel.setBorder(new LineBorder(Color.WHITE, 5, true)); // Rounded border

        JLabel titleLabel = new JLabel("Project Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32)); // Larger font size
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(185, 30, 300, 50); // Adjusted position
        panel.add(titleLabel);

        JButton selectButton = createButton("Send Request", new Color(0x8E44AD)); // Purple button
        selectButton.setBounds(200, 550, 200, 50); // Adjusted size and position
        panel.add(selectButton);

        return panel;
    }

    private void createNewSwipePanel() {
        // Reset xPosition for the new panel (starts off-screen to the left)
        xPosition = 200;

        // Create a new swipe panel with a random pastel color
        Color[] pastelColors = {new Color(0xFFB6C1), new Color(0xB0E57C), new Color(0x87CEEB)};
        Color randomColor = pastelColors[(int) (Math.random() * pastelColors.length)];
        swipePanel = createSwipePanel(randomColor);
        swipePanel.setBounds(xPosition, 150, 600, 650); // Larger dimensions
        add(swipePanel);

        repaint();

        // Animate the new panel swiping in
        Timer swipeInTimer = new Timer(10, (ActionEvent evt) -> {
            if (xPosition < 350) { // Target position
                xPosition += 10;
                swipePanel.setBounds(xPosition, 150, 600, 650);
            } else {
                ((Timer) evt.getSource()).stop(); // Stop the animation
            }
        });
        swipeInTimer.start();
    }

    private JButton createButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(backgroundColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setBorder(new LineBorder(Color.WHITE, 2, true)); // Rounded border
        return button;
    }

    public void swipeDisplayMain() {
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Project Showcase");
            mainFrame.setSize(1800, 1000);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setLayout(new BorderLayout());

            // Set a gradient background panel
            JPanel gradientPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    GradientPaint gradient = new GradientPaint(0, 0, new Color(0xffc2d1), getWidth(), getHeight(), new Color(0xffc2d1));
                    g2d.setPaint(gradient);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            };
            gradientPanel.setLayout(new BorderLayout());

            // Create and add the vertical banner on the left
            JPanel bannerPanel = createBannerPanel();
            gradientPanel.add(bannerPanel, BorderLayout.WEST);

            // Add the DisplayProjects panel to the main frame
            gradientPanel.add(this, BorderLayout.CENTER);
            mainFrame.add(gradientPanel);

            mainFrame.setVisible(true);
        });
    }

    private JPanel createBannerPanel() {
        // Create a container panel to hold the banner panel with a gap on the left
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.setBackground(Color.black); // Match the background color
        containerPanel.setPreferredSize(new Dimension(300, 1000)); // Increase width to account for the gap

        // Create the banner panel
        JPanel bannerPanel = new JPanel();
        bannerPanel.setLayout(new BoxLayout(bannerPanel, BoxLayout.Y_AXIS));
        bannerPanel.setBackground(Color.black); // Rich blue
        bannerPanel.setPreferredSize(new Dimension(250, 1000));

        bannerPanel.add(Box.createVerticalStrut(30));

        JLabel bannerLabel = new JLabel("Requests");
        bannerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        bannerLabel.setForeground(Color.WHITE);
        bannerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bannerPanel.add(bannerLabel);

        JScrollPane sentRequestsScrollPane = createScrollPane("Sent Requests");
        JScrollPane acceptedRequestsScrollPane = createScrollPane("Accepted Requests");
        JScrollPane declinedRequestsScrollPane = createScrollPane("Declined Requests");

        bannerPanel.add(Box.createVerticalStrut(10));
        bannerPanel.add(sentRequestsScrollPane);
        bannerPanel.add(Box.createVerticalStrut(40));
        bannerPanel.add(acceptedRequestsScrollPane);
        bannerPanel.add(Box.createVerticalStrut(40));
        bannerPanel.add(declinedRequestsScrollPane);
        bannerPanel.add(Box.createVerticalStrut(60));

        // Add a margin to the left by wrapping the banner panel inside the container panel
        containerPanel.add(bannerPanel, BorderLayout.CENTER);
        containerPanel.setBorder(BorderFactory.createEmptyBorder(0, 75, 10, 10)); // Add a 20-pixel gap on the left

        return containerPanel;
    }

    private JScrollPane createScrollPane(String sectionTitle) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(0xffc2d1)); // Light gray background
        panel.setBorder(BorderFactory.createEmptyBorder());

        for (int i = 1; i <= 10; i++) {
            JLabel label = new JLabel(sectionTitle + " " + i);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(200, 250));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return scrollPane;
    }

    public static void main(String[] args) {
        DisplayProjects dp = new DisplayProjects();
        dp.swipeDisplayMain();
    }
}
