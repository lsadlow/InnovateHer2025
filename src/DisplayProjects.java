import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class DisplayProjects extends JPanel {
    private JPanel swipePanel;
    private int xPosition = 350; // Initial position (off-screen)
    private JButton nextButton;
    private JButton backButton;
    private Client client;
    private DataOutputStream output;
    private DataInputStream bfr;
    private Image backgroundImage; // Declare an Image for the background



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public DisplayProjects() {
        // Load the background image
        backgroundImage = new ImageIcon("img_10.png").getImage();

        setLayout(null); // Use absolute positioning
        setPreferredSize(new Dimension(1800, 1000));

        // Add "Current Projects" label
        JLabel currentProjectsLabel = new JLabel("Current Projects");
        currentProjectsLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        currentProjectsLabel.setForeground(Color.WHITE);
        currentProjectsLabel.setBounds(515, 75, 400, 50);
        add(currentProjectsLabel);

        // Initialize swipe panel
        xPosition = 350; // Initial position (on-screen)
        swipePanel = createSwipePanel(new Color(0x29465b));
        swipePanel.setBounds(xPosition, 150, 600, 650);
        add(swipePanel);

        // Initialize "Next" button
        nextButton = createButton("Next", new Color(0x27AE60));
        nextButton.setBounds(700, 825, 120, 50);
        add(nextButton);

        // Initialize "Back" button
        backButton = createButton("Back", new Color(0xffc2d1));
        backButton.setBounds(500, 825, 120, 50);
        add(backButton);

        // Set up button listeners
        setupButtonListeners();
    }

    private void setupButtonListeners() {
        // "Next" button action listener
        nextButton.addActionListener(e -> {
            Timer swipeOutTimer = new Timer(10, evt -> {
                if (xPosition < 900) { // Move current panel to the right
                    xPosition += 10;
                    swipePanel.setBounds(xPosition, 150, 600, 650);
                    repaint();
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
            Timer swipeInTimer = new Timer(10, evt -> {
                if (xPosition > 200) { // Move current panel to the left
                    xPosition -= 7;
                    swipePanel.setBounds(xPosition, 150, 600, 650);
                    repaint();
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
        JTextArea projectDetailsArea = new JTextArea("Name: Market Research AI-Bot\n\nMajor: Computer Science\n\nSkills Requires: Python, ML, NLP\n\nDescription: In this project we aim to create a chat bot AI that on a single voice prompt of a bsiness idea, does the market research for you that will be crucial to startjgn a abusiness.");
        projectDetailsArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        projectDetailsArea.setForeground(new Color(0xfef8c9));
        projectDetailsArea.setBackground(backgroundColor);
        projectDetailsArea.setEditable(false);
        projectDetailsArea.setLineWrap(true);
        projectDetailsArea.setWrapStyleWord(true);
        projectDetailsArea.setBounds(50, 100, 500, 400);
        panel.add(projectDetailsArea);

        JLabel titleLabel = new JLabel("  Details");
        titleLabel.setFont(new Font("Monospaced", Font.PLAIN, 30)); // Larger font size
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(175, 30, 300, 50); // Adjusted position
        panel.add(titleLabel);

        JButton selectButton = createButton("Send Request", Color.white); // Purple button
        selectButton.setBounds(200, 550, 200, 50); // Adjusted size and position
        selectButton.setForeground(Color.white);
        selectButton.setBorder(BorderFactory.createEtchedBorder(Color.white , Color.white));
        selectButton.setOpaque(false);

        panel.add(selectButton);

        return panel;
    }

    private void createNewSwipePanel() {
        // Reset xPosition for the new panel (starts off-screen to the left)
        xPosition = 200;

        // Create a new swipe panel with a random dark color
        // Create a new swipe panel with a random dark color
        Color[] darkColors = {
                new Color(0x2C3E50), // Dark blue-gray
                new Color(0x34495E), // Steel blue
                new Color(0x1C2833), // Blue-black
                new Color(0x2E4053), // Charcoal blue
                new Color(0x17202A), // Midnight blue-black
                new Color(0x3C3B6E), // Deep indigo
                 // Rich dark red
        };
        Color randomColor = darkColors[(int) (Math.random() * darkColors.length)];
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
        button.setFont(new Font("Monospaced", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(backgroundColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setBorder(new LineBorder(new Color(0x111111), 2, true)); // Rounded border
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
        containerPanel.setBackground(new Color(0x306da8)); // Match the background color
        containerPanel.setPreferredSize(new Dimension(300, 1000)); // Increase width to account for the gap


        // Create the banner panel
        JPanel bannerPanel = new JPanel();
        bannerPanel.setLayout(new BoxLayout(bannerPanel, BoxLayout.Y_AXIS));
        bannerPanel.setBackground(new Color(0xfe6bae)); // Rich blue
        bannerPanel.setPreferredSize(new Dimension(150, 1000));


        bannerPanel.add(Box.createVerticalStrut(30));

        JLabel bannerLabel = new JLabel("Requests");
        bannerLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        bannerLabel.setForeground(Color.WHITE);
        bannerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        bannerLabel.setOpaque(false);
        bannerPanel.add(bannerLabel);

        ImageIcon front = new ImageIcon("img_3.png") ;
        Image frontImage = front.getImage().getScaledInstance(1800 , 990 , Image.SCALE_SMOOTH) ;
        ImageIcon frontIcon = new ImageIcon(frontImage) ;


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
        containerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Add a 20-pixel gap on the left

        return containerPanel;
    }

    private JScrollPane createScrollPane(String sectionTitle) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(0xffc2d1)); // Light gray background
        panel.setBorder(BorderFactory.createEmptyBorder());


        for (int i = 1; i <= 10; i++) {
            JLabel label = new JLabel(sectionTitle + " " + i);
            label.setFont(new Font("Monospaced", Font.PLAIN, 14));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(150, 250));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        return scrollPane;
    }



    public static void main(String[] args) {
        DisplayProjects dp = new DisplayProjects();
        dp.swipeDisplayMain();
    }
}
