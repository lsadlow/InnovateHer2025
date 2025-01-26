import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MainAuthPage {

    private Client client;
    private DataOutputStream output;
    private DataInputStream bfr;

    public MainAuthPage(Client client) {
        this.client = client;
        this.output = client.getDos();
        this.bfr = client.getDis();

      start();

   }


    // main page (login, sign up)
    public  void start()  {

        /*

        // DEFAULT FONTS for buttons/labels for ALL gui components
        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 30)); // JLabels
        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 20)); // buttons
        UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 20)); // text input field
        UIManager.put("PasswordField.font", new Font("Arial", Font.PLAIN, 20)); // password input field
        UIManager.put("List.font", new Font("Arial", Font.PLAIN, 20)); // JList

         */


        // create JFrame
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000, 1000);
        frame.getContentPane().setBackground(Color.black);
        frame.setLayout(new FlowLayout());


        JPanel basePanel = new JPanel() ;
        basePanel.setLayout(null);
        basePanel.setPreferredSize(new Dimension(2000 , 1000));
        basePanel.setBackground(Color.white);

        ImageIcon front = new ImageIcon("img_3.png") ;
        Image frontImage = front.getImage().getScaledInstance(1800 , 990 , Image.SCALE_SMOOTH) ;
        ImageIcon frontIcon = new ImageIcon(frontImage) ;





        // panels & layout
        // JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        //panel.setBounds();


        // labels & buttons
        JLabel label = new JLabel("Welcome to CodeBabes !" , JLabel.CENTER);
        label.setLayout(null);

        //label.setBackground(Color.black);
        label.setFont(new Font("Monospaced", Font.PLAIN, 50));
        label.setForeground(new Color(0x8daaac));
        label.setOpaque(true);
        label.setBounds(300, 50, 400, 50); // Adjusted position and size


        label.setBounds(0, 0, 2000, 1000); // Ensure bounds do not cover header
        label.setIcon(frontIcon);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setIconTextGap(-200);

        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        basePanel.setLayout(null); // Ensure absolute positioning
        basePanel.add(label);
        label.setOpaque(true);
        // Add label first
        //basePanel.add(header);     // Add header on top
        frame.add(basePanel);



        // Button panel
        JPanel buttonPanel = new JPanel() ;
        buttonPanel.setLayout(new FlowLayout( FlowLayout.CENTER , 30 ,50));
        buttonPanel.setBounds(540 , 690, 900 , 150) ;
        basePanel.add(buttonPanel , JLabel.CENTER) ;
        buttonPanel.setAlignmentX(400);
        buttonPanel.setAlignmentY(400);
        buttonPanel.setBackground(Color.black);
        buttonPanel.setOpaque(false);


        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(new Color(0x8daaac)) ;
        signUpButton.setBorder(BorderFactory.createEtchedBorder());
        signUpButton.setFocusable(false);
        signUpButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        signUpButton.setForeground(Color.BLACK);
        signUpButton.setOpaque(true);
        signUpButton.setPreferredSize(new Dimension(200 , 75) );


        JButton loginButton = new JButton("Log In");
        loginButton.setBackground(new Color(0x8daaac)) ;
        loginButton.setBorder(BorderFactory.createEtchedBorder());
        loginButton.setFocusable(false);
        loginButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        loginButton.setForeground(Color.black);
        loginButton.setOpaque(true);
        loginButton.setPreferredSize(new Dimension(200 , 75) )  ;


        JButton closeButton = new JButton("Close App");
        closeButton.setBackground(new Color(0x8daaac)) ;
        closeButton.setBorder(BorderFactory.createEtchedBorder());
        closeButton.setFocusable(false);
        closeButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        closeButton.setForeground(Color.black);
        closeButton.setOpaque(true);
        closeButton.setPreferredSize(new Dimension(200 , 75) ) ;

        buttonPanel.add(signUpButton);
        buttonPanel.add(loginButton);
        buttonPanel.add(closeButton);



        // action listeners for buttons
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                signUp();
            }
        });
        loginButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                login();
            }
        }));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmExit = JOptionPane.showConfirmDialog(frame, "Are you sure you want to close the app?", "Exit", JOptionPane.YES_NO_OPTION);
                if (confirmExit == JOptionPane.YES_NO_OPTION) {
                    System.exit(0);
                    frame.setVisible(false);
                }
            }
        });

        frame.setVisible(true);
        try {
            bfr.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    // login details (server code 2)
    private void login() {

        JFrame frame = new JFrame("Log In");
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        frame.setSize(new Dimension(2000, 1000));
        frame.getContentPane().setBackground(Color.black);

        // JLayeredPane to manage layers
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(2000, 1000));

        // Banner setup
        JLabel banner = new JLabel();
        banner.setFont(new Font("Monospaced", Font.BOLD, 40));
        banner.setForeground(Color.black);
        banner.setOpaque(true);
        ImageIcon front = new ImageIcon("img_16.png");
        Image frontImage = front.getImage().getScaledInstance(1400, 750, Image.SCALE_SMOOTH);
        ImageIcon frontIcon = new ImageIcon(frontImage);
        banner.setIcon(frontIcon);
        banner.setHorizontalTextPosition(JLabel.CENTER);
        banner.setVerticalTextPosition(JLabel.TOP);
        banner.setIconTextGap(-400);
        banner.setBackground(Color.black);
        banner.setVerticalAlignment(JLabel.CENTER);
        banner.setHorizontalAlignment(JLabel.CENTER);
        banner.setBounds(100, 0, 1800, 450);

        // Adding banner to the layered pane at the default layer
        layeredPane.add(banner, JLayeredPane.DEFAULT_LAYER);
        layeredPane.setBackground(Color.black);

        // Input panel setup
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
        inputPanel.setBounds(690, 350, 600, 300);
        inputPanel.setBackground(Color.black);
        inputPanel.setOpaque(true);
        inputPanel.setBorder(BorderFactory.createLineBorder((new Color(0xFFE9C7) )));

        JLabel text = new JLabel("                     Login                    ");
        text.setFont(new Font("Monospaced", Font.PLAIN, 30));
        text.setForeground((new Color(0xFFE9C7) ));

        JLabel username = new JLabel("  Username: ");
        username.setFont(new Font("Monospaced", Font.PLAIN, 20));
        username.setForeground((new Color(0xFFE9C7)));

        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(300, 30));
        usernameField.setBorder(BorderFactory.createEmptyBorder());
        usernameField.setFont(new Font("Monospaced", Font.BOLD, 20));

        JLabel password = new JLabel("  Password: ");
        password.setFont(new Font("Monospaced", Font.PLAIN, 20));
        password.setForeground((new Color(0xFFE9C7)));

        JTextField passwordField = new JTextField();
        passwordField.setPreferredSize(new Dimension(300, 30));
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        passwordField.setFont(new Font("Monospaced", Font.BOLD, 20));

        inputPanel.add(text) ;
        inputPanel.add(username);
        inputPanel.add(usernameField);
        inputPanel.add(password);
        inputPanel.add(passwordField);

        // Adding input panel to the layered pane at the popup layer
        layeredPane.add(inputPanel, JLayeredPane.POPUP_LAYER);

        // Buttons
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
        controlPanel.setBounds(640, 720, 700, 250);
        controlPanel.setBackground(Color.magenta);
        controlPanel.setOpaque(false);

        JButton loginButton = new JButton("Log In");
        loginButton.setPreferredSize(new Dimension(170, 55));
        loginButton.setBackground((new Color(0xFFE9C7)));
        loginButton.setBorder(BorderFactory.createEtchedBorder());
        loginButton.setFocusable(false);
        loginButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        loginButton.setForeground(Color.black);
        loginButton.setOpaque(true);

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(170, 55));
        backButton.setBackground(new Color(0x6eaa6b));
        backButton.setBorder(BorderFactory.createEtchedBorder());
        backButton.setFocusable(false);
        backButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        backButton.setForeground(Color.black);
        backButton.setOpaque(true);

        controlPanel.add(loginButton);
        controlPanel.add(backButton);

        // Adding control panel to the layered pane at the popup layer
        layeredPane.add(controlPanel, JLayeredPane.POPUP_LAYER );

        // Adding the layered pane to the frame
        frame.add(layeredPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        HomePage homePage = new HomePage(client) ;


        // action listeners
       loginButton.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent e) {
                try {

                   // "LOGIN " + username + " " + password
                    String outputString = "LOGIN " + usernameField.getText() + " " + passwordField.getText() ;

                    output.writeUTF(outputString);
                    String serverResponse = bfr.readUTF() ;

                    Database database = new Database();

                    database.loadUsers();
                    User thisUser = database.findUser(usernameField.getText()) ;
                    client.user = thisUser ;
                    // server response
                    JOptionPane.showMessageDialog(null, serverResponse);

                    if (serverResponse.equals("Success")) {

                        frame.dispose();
                        homePage.showHomePage(thisUser);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Login failed. Please check that username and password are correct.");
                        frame.dispose();
                        start();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainAuthPage(client).start();
            }
        });



    }




    // signup details (server code 1)
    private void signUp() {


                JFrame frame = new JFrame("Sign Up");
                frame.setLayout(null); // Use null layout for precise positioning
                frame.setSize(new Dimension(2000, 1000));
                frame.getContentPane().setBackground(new Color(0x141414));

                // Create a JLayeredPane for layering components
                JLayeredPane layeredPane = new JLayeredPane();
                layeredPane.setBounds(0, 0, 2000, 1000);
                frame.add(layeredPane);

                // Create the banner with an image
                JLabel banner = new JLabel();
                banner.setFont(new Font("Monospaced", Font.PLAIN, 40));
                ImageIcon front = new ImageIcon("img_15.png");
                Image frontImage = front.getImage().getScaledInstance(1400, 350, Image.SCALE_SMOOTH);
                ImageIcon frontIcon = new ImageIcon(frontImage);

                banner.setIcon(frontIcon);
                banner.setVerticalTextPosition(JLabel.TOP);
                banner.setHorizontalTextPosition(JLabel.CENTER);
                banner.setHorizontalAlignment(JLabel.CENTER);
                banner.setForeground(new Color(0x6eaa6b));
                banner.setOpaque(true);
                banner.setIconTextGap(-220);
                banner.setBounds(75, 0, 1400, 350);
                layeredPane.add(banner, Integer.valueOf(0)); // Add to the lowest layer

                // Create the input panel
                JPanel inputPanel = new JPanel();
                inputPanel.setLayout(new GridLayout(7, 2, 10, 20)); // Use GridLayout for neat label-text field pairs
                inputPanel.setBackground(new Color(0x141414)); // Transparent background
                inputPanel.setBorder(BorderFactory.createLineBorder(new Color(0xFFE9C7)));
                inputPanel.setOpaque(true);
                inputPanel.setBounds(475, 280, 600, 450); // Positioned on top of the banner

                // Labels and fields
                String[] labels = {"First Name:", "Purdue email:", "Major:", "Skills:", "Username:", "Password:", "Confirm Password:"};
                JTextField[] textFields = new JTextField[labels.length]; // Array to store text fields

                for (int i = 0; i < labels.length; i++) {
                    JLabel fieldLabel = new JLabel(labels[i]);
                    fieldLabel.setFont(new Font("Monospaced", Font.PLAIN, 20));
                    fieldLabel.setForeground(new Color(0xFFE9C7));

                    JTextField textField = new JTextField();
                    textField.setPreferredSize(new Dimension(350, 40));
                    textField.setBorder(BorderFactory.createLineBorder(new Color(0xFFE9C7)));
                    textField.setFont(new Font("Monospaced", Font.BOLD, 20));

                    inputPanel.add(fieldLabel);
                    inputPanel.add(textField);
                    textFields[i] = textField; // Store reference to the text field
                }

                // Add the input panel to the layered pane
                layeredPane.add(inputPanel, Integer.valueOf(1)); // Add to a higher layer

                // Add control panel
                JPanel controlPanel = new JPanel();
                controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
                controlPanel.setBounds(420, 760, 700, 250);
                controlPanel.setBackground(new Color(0x141414));

                JButton signUpButton = new JButton("Sign Up");
                signUpButton.setPreferredSize(new Dimension(150, 50));
                signUpButton.setBackground(new Color(0x97B0FF));
                signUpButton.setBorder(BorderFactory.createEtchedBorder());
                signUpButton.setFocusable(false);
                signUpButton.setFont(new Font("Monospaced", Font.BOLD, 20));
                signUpButton.setForeground(Color.black);

        // action listeners
      signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
               // Signup : "SIGNUP " + name + " " + username + " " + password + " " + email + " " + languages + " " + major + " " + confirmPassword                try {
                    String outputString = "SIGNUP" + " " + textFields[0].getText().trim() + " " + textFields[4].getText().trim() + " " +
                            textFields[5].getText().trim() + " " + textFields[1].getText().trim() + " " +
                            textFields[3].getText().replace(" ", "`") + " " + textFields[2].getText().replace(" ", "`") + " " +
                            textFields[6].getText().trim();
                    output.writeUTF(outputString);
                    // server response
                    String serverResponse = bfr.readUTF();
                    System.out.println(serverResponse);
                    JOptionPane.showMessageDialog(frame, serverResponse);
                    frame.dispose();
                    start();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.setBackground(new Color(0x6eaa6b));
        backButton.setBorder(BorderFactory.createEtchedBorder());
        backButton.setFocusable(false);
        backButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        backButton.setForeground(Color.black);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                start();
            }
        });

        controlPanel.add(signUpButton) ;
        controlPanel.add(backButton) ;
        frame.add(controlPanel) ;
        frame.setVisible(true );

   }


}
