import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;

public class HomePage {

    private Client client;
    private DataOutputStream output;
    private DataInputStream bfr;

    public HomePage(Client client) {
        this.client = client;
        this.output = client.getDos();
        this.bfr = client.getDis();
    }



    public void showHomePage(User user) {
        // create JFrame
        JFrame frame = new JFrame("Home Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000, 1000);
        frame.getContentPane().setBackground(new Color(0x053e71));
        frame.setLayout(new FlowLayout());


        JPanel basePanel = new JPanel();
        basePanel.setLayout(null);
        basePanel.setPreferredSize(new Dimension(2000, 1000));
        basePanel.setBackground(new Color(0x053e71));

        ImageIcon front = new ImageIcon("img_10.png");
        Image frontImage = front.getImage().getScaledInstance(1750, 990, Image.SCALE_SMOOTH);
        ImageIcon frontIcon = new ImageIcon(frontImage);

        // labels & buttons
        JLabel label = new JLabel("Welcome " + user.getUsername(), JLabel.CENTER);
        label.setLayout(null);

        label.setBackground(new Color(0x053e71));
        label.setFont(new Font("Monospaced", Font.PLAIN, 63));
        label.setForeground(Color.white);
        label.setOpaque(true);
        label.setBounds(100, 50, 400, 50); // Adjusted position and size


        label.setBounds(0, 0, 2000, 1000); // Ensure bounds do not cover header
        label.setIcon(frontIcon);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setIconTextGap(-200);

        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        basePanel.setLayout(null); // Ensure absolute positioning
        basePanel.add(label);      // Add label first

        // Contacts
        JPanel contactsPanel = new JPanel();
        contactsPanel.setLayout(new GridLayout(2, 1, 0, 0));
        contactsPanel.setBounds(470, 250, 250, 450);
        contactsPanel.setOpaque(false);
        JLabel contactsLabel = new JLabel("Notifications");
        contactsLabel.setForeground(Color.white);
        contactsLabel.setFont(new Font("Monospaced", Font.PLAIN, 30));
        contactsLabel.setBackground(Color.blue);
        contactsLabel.setOpaque(false); // makes label background transparent
        contactsLabel.setBorder(BorderFactory.createEmptyBorder());
        contactsLabel.setPreferredSize(new Dimension(100, 10));
        contactsPanel.add(contactsLabel);
        //buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 0, 15));
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(200, 400));
        JButton viewProjectsButton = new JButton("View Projects");
        viewProjectsButton.setBackground(Color.white);
        viewProjectsButton.setBorder(BorderFactory.createEtchedBorder());
        viewProjectsButton.setFocusable(false);
        viewProjectsButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
        viewProjectsButton.setForeground(Color.white);
        viewProjectsButton.setOpaque(false);
        viewProjectsButton.setPreferredSize(new Dimension(100, 50));
        buttonPanel.add(viewProjectsButton);
        contactsPanel.add(buttonPanel);

        //add contact
        JButton RequestsButton = new JButton("Requests");
        RequestsButton.setBackground(Color.white);
        RequestsButton.setBorder(BorderFactory.createEtchedBorder());
        RequestsButton.setFocusable(false);
        RequestsButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
        RequestsButton.setForeground(Color.white);
        RequestsButton.setOpaque(false);
        RequestsButton.setPreferredSize(new Dimension(100, 50));
        buttonPanel.add(RequestsButton);
        contactsPanel.add(buttonPanel);

        //remove contact
        JButton removeContactButton = new JButton("Remove Contact");
        removeContactButton.setBackground(Color.white);
        removeContactButton.setBorder(BorderFactory.createEtchedBorder());
        removeContactButton.setFocusable(false);
        removeContactButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
        removeContactButton.setForeground(Color.white);
        removeContactButton.setOpaque(false);
        removeContactButton.setPreferredSize(new Dimension(100, 50));
        buttonPanel.add(removeContactButton);
        contactsPanel.add(buttonPanel);

        basePanel.add(contactsPanel, JLabel.CENTER);
        frame.add(basePanel);
        frame.setVisible(true);

        // Cosmic Conversations
        JPanel convPanel = new JPanel();
        convPanel.setLayout(new GridLayout(2, 1, 0, 20));
        convPanel.setBounds(800, 270, 400, 400);
        convPanel.setOpaque(false);
        JLabel convLabel = new JLabel("        Projects");
        convLabel.setForeground(Color.white);
        convLabel.setFont(new Font("Monospaced", Font.PLAIN, 30));
        convLabel.setOpaque(false); // makes label background transparent
        convLabel.setBorder(BorderFactory.createEmptyBorder());
        convLabel.setPreferredSize(new Dimension(200, 50));
        convPanel.add(convLabel, JLabel.CENTER);

        // Buttons
        JPanel convButtonPanel = new JPanel();
        convButtonPanel.setLayout(new GridLayout(2, 1, 0, 15));
        convButtonPanel.setOpaque(false);
        convButtonPanel.setPreferredSize(new Dimension(100, 400));
        // start convo
        JButton startProjButton = new JButton("Start New Project");
        startProjButton.setBackground(Color.white);
        startProjButton.setBorder(BorderFactory.createEtchedBorder());
        startProjButton.setFocusable(false);
        startProjButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
        startProjButton.setForeground(Color.white);
        startProjButton.setOpaque(false);
        startProjButton.setPreferredSize(new Dimension(100, 50));
        convButtonPanel.add(startProjButton);
        convPanel.add(convButtonPanel);

        // delete convo
        JButton delConvButton = new JButton("Delete Conversation");
        delConvButton.setBackground(Color.white);
        delConvButton.setBorder(BorderFactory.createEtchedBorder());
        delConvButton.setFocusable(false);
        delConvButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
        delConvButton.setForeground(Color.white);
        delConvButton.setOpaque(false);
        delConvButton.setPreferredSize(new Dimension(100, 50));
        convButtonPanel.add(delConvButton);
        convPanel.add(convButtonPanel);

        basePanel.add(convPanel, JLabel.CENTER);
        frame.add(basePanel);
        frame.setVisible(true);

        // Messages
        JPanel msgPanel = new JPanel();
        msgPanel.setLayout(new GridLayout(2, 1, 0, 0));
        msgPanel.setBounds(1350, 250, 200, 450);
        msgPanel.setOpaque(false);
        JLabel msgLabel = new JLabel("Messages");
        msgLabel.setForeground(Color.white);
        msgLabel.setFont(new Font("Monospaced", Font.PLAIN, 30));
        msgLabel.setOpaque(false); // makes label background transparent
        msgLabel.setBorder(BorderFactory.createEmptyBorder());
        msgLabel.setPreferredSize(new Dimension(200, 50));
        msgPanel.add(msgLabel, JLabel.CENTER);

        // Buttons
        JPanel msgButtonPanel = new JPanel();
        msgButtonPanel.setLayout(new GridLayout(3, 1, 0, 15));
        msgButtonPanel.setOpaque(false);
        msgButtonPanel.setPreferredSize(new Dimension(200, 450));
        //view
        JButton viewMsgsButton = new JButton("View Messages");
        viewMsgsButton.setBackground(Color.white);
        viewMsgsButton.setBorder(BorderFactory.createEtchedBorder());
        viewMsgsButton.setFocusable(false);
        viewMsgsButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
        viewMsgsButton.setForeground(Color.white);
        viewMsgsButton.setOpaque(false);
        viewMsgsButton.setPreferredSize(new Dimension(200, 50));
        msgButtonPanel.add(viewMsgsButton);

        //add contact
        JButton sendMsgButton = new JButton("Send Message");
        sendMsgButton.setBackground(Color.white);
        sendMsgButton.setBorder(BorderFactory.createEtchedBorder());
        sendMsgButton.setFocusable(false);
        sendMsgButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
        sendMsgButton.setForeground(Color.white);
        sendMsgButton.setOpaque(false);
        sendMsgButton.setPreferredSize(new Dimension(200, 50));
        msgButtonPanel.add(sendMsgButton);

        //remove contact
        JButton delMsgButton = new JButton("Delete Message");
        delMsgButton.setBackground(Color.white);
        delMsgButton.setBorder(BorderFactory.createEtchedBorder());
        delMsgButton.setFocusable(false);
        delMsgButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
        delMsgButton.setForeground(Color.white);
        delMsgButton.setOpaque(false);
        delMsgButton.setPreferredSize(new Dimension(200, 50));
        msgButtonPanel.add(delMsgButton);

        msgPanel.add(msgButtonPanel);

        basePanel.add(msgPanel, JLabel.CENTER);
        frame.add(basePanel);
        frame.setVisible(true);


        //log out
        JButton logOutButton = new JButton("Log Out");
        logOutButton.setBackground(Color.white);
        logOutButton.setBorder(BorderFactory.createEtchedBorder());
        logOutButton.setFocusable(false);
        logOutButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
        logOutButton.setForeground(Color.white);
        logOutButton.setOpaque(false);
        logOutButton.setBounds(1600, 10, 150, 50);
        basePanel.add(logOutButton, JLabel.CENTER);

        frame.add(basePanel);
        frame.setVisible(true);

        // blocked
        JPanel blockedPanel = new JPanel();

        blockedPanel.setBounds(-10, 845, 1000, 300);
        blockedPanel.setOpaque(false);
        JLabel blockedLabel = new JLabel();
        blockedLabel.setForeground(Color.white);
        blockedLabel.setFont(new Font("Monospaced", Font.PLAIN, 30));
        blockedLabel.setBackground(Color.blue);
        blockedLabel.setOpaque(false); // makes label background transparent
        blockedLabel.setBorder(BorderFactory.createEmptyBorder());
        blockedLabel.setPreferredSize(new Dimension(100, 10));
        blockedPanel.add(blockedLabel);
        //buttons
        JPanel blockedbuttonPanel = new JPanel();
        blockedbuttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        blockedbuttonPanel.setOpaque(false);
        blockedbuttonPanel.setPreferredSize(new Dimension(1000, 400));
        JButton viewblockedButton = new JButton("Blocklist");
        viewblockedButton.setBackground(Color.white);
        viewblockedButton.setBorder(BorderFactory.createEtchedBorder());
        viewblockedButton.setFocusable(false);
        viewblockedButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
        viewblockedButton.setForeground(Color.white);
        viewblockedButton.setOpaque(false);
        viewblockedButton.setPreferredSize(new Dimension(150, 45));
        blockedbuttonPanel.add(viewblockedButton);
        blockedPanel.add(blockedbuttonPanel);

        //add contact
        JButton blockButton = new JButton("Block");
        blockButton.setBackground(Color.white);
        blockButton.setBorder(BorderFactory.createEtchedBorder());
        blockButton.setFocusable(false);
        blockButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
        blockButton.setForeground(Color.white);
        blockButton.setOpaque(false);
        blockButton.setPreferredSize(new Dimension(150, 45));
        blockedbuttonPanel.add(blockButton);
        blockedPanel.add(blockedbuttonPanel);

        //remove contact
        JButton unblockButton = new JButton("Unblock");
        unblockButton.setBackground(Color.white);
        unblockButton.setBorder(BorderFactory.createEtchedBorder());
        unblockButton.setFocusable(false);
        unblockButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
        unblockButton.setForeground(Color.white);
        unblockButton.setOpaque(false);
        unblockButton.setPreferredSize(new Dimension(150, 45));
        blockedbuttonPanel.add(unblockButton);
        blockedPanel.add(blockedbuttonPanel);

        basePanel.add(blockedPanel, JLabel.CENTER);
        frame.add(basePanel);
        frame.setVisible(true);


        //User search button
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(Color.white);
        searchButton.setBorder(BorderFactory.createEtchedBorder());
        searchButton.setFocusable(false);
        searchButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
        searchButton.setForeground(Color.white);
        searchButton.setOpaque(false);
        searchButton.setBounds(1440, 10, 150, 50);
        basePanel.add(searchButton, JLabel.CENTER);

        frame.add(basePanel);
        frame.setVisible(true);

        // View Profile
        JButton viewProfileButton = new JButton("View Profile");
        viewProfileButton.setBackground(Color.white);
        viewProfileButton.setBorder(BorderFactory.createEtchedBorder());
        viewProfileButton.setFocusable(false);
        viewProfileButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
        viewProfileButton.setForeground(Color.white);
        viewProfileButton.setOpaque(false);
        viewProfileButton.setBounds(240, 10, 175, 50);
        basePanel.add(viewProfileButton, JLabel.CENTER);

        frame.add(basePanel);
        frame.setVisible(true);
    }
}