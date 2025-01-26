import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CS180 Team Project -- Direct Messaging Platform
 *
 * Contains GUI elements for the main page of user authentication.
 * Includes the main starting menu with login and signup GUI screens.
 * Also sends/receives information to/from the server.
 *
 * @author Nicholas Chong
 *
 * @version 11/27/2024
 *
 */

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
    public  void start() {

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
    }



    // login details (server code 2)
    private void login() {

        JFrame frame = new JFrame("Log In") ;
        frame.setLayout(new FlowLayout( FlowLayout.CENTER , 10 ,0));
        frame.setSize(new Dimension(2000 , 1000));




        // BTextfeilds
        JPanel topPanel = new JPanel() ;
        topPanel.setPreferredSize(new Dimension(2000 , 1000));
        topPanel.setBackground(Color.white);
        topPanel.setLayout(null);

        JLabel banner = new JLabel("       Log In" , JLabel.CENTER);
        banner.setFont(new Font("Monospaced", Font.BOLD, 40));
        banner.setForeground(Color.black);
        banner.setOpaque(true);
        ImageIcon front = new ImageIcon("img_5.png") ;
        Image frontImage = front.getImage().getScaledInstance(1800 , 600 , Image.SCALE_SMOOTH) ;
        ImageIcon frontIcon = new ImageIcon(frontImage) ;
        banner.setIcon(frontIcon);
        banner.setHorizontalTextPosition(JLabel.CENTER);
        banner.setVerticalTextPosition(JLabel.TOP);
        banner.setIconTextGap(-400);

        banner.setVerticalAlignment(JLabel.CENTER);
        banner.setHorizontalAlignment(JLabel.CENTER);
        banner.setBounds(0,0 , 1800 , 300);
        topPanel.add(banner , JLabel.CENTER) ;




        JPanel inputPanel = new JPanel() ;
        inputPanel.setLayout(new FlowLayout( FlowLayout.CENTER , 10 ,50));

        inputPanel.setBounds(730,310, 550,200);
        inputPanel.setBackground(new Color(0x141414));
        inputPanel.setOpaque(false);


        JLabel username = new JLabel("Username: ") ;
        // Labels don't need prefered size
        username.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        username.setForeground(Color.black);

        JTextField usernameFeild = new JTextField() ;
        usernameFeild.setPreferredSize(new Dimension(300 ,30));
        usernameFeild.setBorder(BorderFactory.createEmptyBorder());
        usernameFeild.setFont(new Font("Monospaced" , Font.BOLD , 20));

        inputPanel.add(username);
        inputPanel.add(usernameFeild) ;

        JLabel password = new JLabel("Password: ") ; // Labels don't need prefered size
        password.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        password.setForeground(Color.black);

        JTextField passwordFeild = new JTextField() ;
        passwordFeild.setPreferredSize(new Dimension(300 ,30));
        passwordFeild.setBorder(BorderFactory.createEmptyBorder());
        passwordFeild.setFont(new Font("Monospaced" , Font.BOLD , 20));
        inputPanel.add(password);
        inputPanel.add(passwordFeild) ;
        topPanel.add(inputPanel);



        //Buttons
        JPanel controlPanel = new JPanel() ;
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER , 50 ,10));
        controlPanel.setBounds(650,550, 700,250);
        controlPanel.setBackground(Color.magenta);
        controlPanel.setOpaque(false);
        JButton loginButton = new JButton("Log In");
        loginButton.setPreferredSize(new Dimension(170, 55));
        loginButton.setBackground(Color.magenta) ;
        loginButton.setBorder(BorderFactory.createEtchedBorder());
        loginButton.setFocusable(false);
        loginButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        loginButton.setForeground(Color.black);
        loginButton.setOpaque(true);


        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(170, 55));
        backButton.setBackground(new Color(0x6eaa6b)) ;
        backButton.setBorder(BorderFactory.createEtchedBorder());
        backButton.setFocusable(false);
        backButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        backButton.setForeground(Color.black);
        backButton.setOpaque(true);



        // instantiate HomePageGUI to use its methods
        HomePage homePage = new HomePage(client);

        controlPanel.add(loginButton) ;
        controlPanel.add(backButton) ;
        topPanel.add(controlPanel) ;

        frame.add(topPanel , JLabel.CENTER) ;


        frame.setVisible(true);


        // action listeners
       loginButton.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent e) {
                try {

                    output.writeUTF("2\n");
                    output.writeUTF(usernameFeild.getText() + "\n");
                    output.writeUTF(new String(passwordFeild.getText()) + "\n") ;
                    Database database = new Database();
                    User thisUser = database.findUser(usernameFeild.getText()) ;
                    client.user = thisUser ;
                    // server response
                    String serverResponse = bfr.readLine();
                    if (serverResponse.equals("true")) {
                        frame.dispose();
                        homePage.showHomePage(thisUser);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Login failed. Please check that username and password are correct.");
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
                start();
            }
        });



    }




    // signup details (server code 1)
    private void signUp() {

        // create a frame
        JFrame frame = new JFrame("Sign Up");
        frame.setLayout(new FlowLayout());
        frame.setSize(new Dimension(2000, 1000));
        frame.getContentPane().setBackground(new Color(0x141414));


        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(2000, 1000));
        topPanel.setBackground(new Color(0x141414));
        topPanel.setLayout(null);

        JLabel banner = new JLabel("        About" , JLabel.CENTER);
        banner.setFont(new Font("Monospaced", Font.BOLD, 40));
        ImageIcon front = new ImageIcon("img_1.png");
        Image frontImage = front.getImage().getScaledInstance(1800, 325, Image.SCALE_SMOOTH);
        ImageIcon frontIcon = new ImageIcon(frontImage);
        banner.setIcon(frontIcon);
        banner.setVerticalTextPosition(JLabel.TOP);
        banner.setHorizontalTextPosition(JLabel.CENTER);
        banner.setHorizontalAlignment(JLabel.CENTER);
        banner.setForeground(new Color(0x6eaa6b));
        banner.setOpaque(true);
        banner.setIconTextGap(-220);
        banner.setBounds(0, 0, 1800, 325);
        topPanel.add(banner);


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        inputPanel.setBounds(570, 300, 650, 450);

        inputPanel.setBackground(new Color(0x141414));


        JLabel firstName = new JLabel("      First Name: ");
        // Labels don't need prefered size
        firstName.setFont(new Font("Monospaced", Font.PLAIN, 20));
        firstName.setForeground(Color.yellow);

        JTextField firstNameFeild = new JTextField();
        firstNameFeild.setPreferredSize(new Dimension(350, 40));
        firstNameFeild.setBorder(BorderFactory.createEmptyBorder());
        firstNameFeild.setFont(new Font("Monospaced", Font.BOLD, 20));

        inputPanel.add(firstName);
        inputPanel.add(firstNameFeild);

        JLabel email = new JLabel("    Purdue email: "); // Labels don't need prefered size
        email.setFont(new Font("Monospaced", Font.PLAIN, 20));
        email.setForeground(Color.yellow);

        JTextField emailFeild = new JTextField();
        emailFeild.setPreferredSize(new Dimension(350, 40));
        emailFeild.setBorder(BorderFactory.createEmptyBorder());
        emailFeild.setFont(new Font("Monospaced", Font.BOLD, 20));
        inputPanel.add(email);
        inputPanel.add(emailFeild);
        topPanel.add(inputPanel);

        // Phone
        JLabel major = new JLabel("          Major:  "); // Labels don't need prefered size
        major.setFont(new Font("Monospaced", Font.PLAIN, 20));
        major.setForeground(Color.yellow);

        JTextField majorFeild = new JTextField();
        majorFeild.setPreferredSize(new Dimension(350, 40));
        majorFeild.setBorder(BorderFactory.createEmptyBorder());
        majorFeild.setFont(new Font("Monospaced", Font.BOLD, 20));
        inputPanel.add(major);
        inputPanel.add(majorFeild);
        topPanel.add(inputPanel);

        // skills
        // Phone
        JLabel skills = new JLabel("          Skills: "); // Labels don't need prefered size
        skills.setFont(new Font("Monospaced", Font.PLAIN, 20));
        skills.setForeground(Color.yellow);

        JTextField skillsFeild = new JTextField();
        skillsFeild.setPreferredSize(new Dimension(350, 40));
        skillsFeild.setBorder(BorderFactory.createEtchedBorder());
        skillsFeild.setFont(new Font("Monospaced", Font.BOLD, 20));
        inputPanel.add(skills);
        inputPanel.add(skillsFeild);
        topPanel.add(inputPanel);

        //Username
        JLabel username = new JLabel("        Username: "); // Labels don't need prefered size
        username.setFont(new Font("Monospaced", Font.PLAIN, 20));
        username.setForeground(Color.yellow);

        JTextField usernameFeild = new JTextField();
        usernameFeild.setPreferredSize(new Dimension(350, 40));
        usernameFeild.setBorder(BorderFactory.createEmptyBorder());
        usernameFeild.setFont(new Font("Monospaced", Font.BOLD, 20));
        inputPanel.add(username);
        inputPanel.add(usernameFeild);
        topPanel.add(inputPanel);

        //Password
        JLabel password = new JLabel("        Password: "); // Labels don't need prefered size
        password.setFont(new Font("Monospaced", Font.PLAIN, 20));
        password.setForeground(Color.yellow);

        JTextField passwordFeild = new JTextField();
        passwordFeild.setPreferredSize(new Dimension(350, 40));
        passwordFeild.setBorder(BorderFactory.createEmptyBorder());
        passwordFeild.setFont(new Font("Monospaced", Font.BOLD, 20));
        inputPanel.add(password);
        inputPanel.add(passwordFeild);
        topPanel.add(inputPanel);

        // Confirm Password
        JLabel confirm = new JLabel("Confirm Password: "); // Labels don't need prefered size
        confirm.setFont(new Font("Monospaced", Font.PLAIN, 20));
        confirm.setForeground(Color.yellow);

        JTextField confirmFeild = new JTextField();
        confirmFeild.setPreferredSize(new Dimension(350, 40));
        confirmFeild.setBorder(BorderFactory.createEmptyBorder());
        confirmFeild.setFont(new Font("Monospaced", Font.BOLD, 20));
        inputPanel.add(confirm);
        inputPanel.add(confirmFeild);
        topPanel.add(inputPanel);


        //Buttons
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
        controlPanel.setBounds(630, 800, 700, 250);
        controlPanel.setBackground(new Color(0x141414));

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setPreferredSize(new Dimension(150, 50));
        signUpButton.setBackground(Color.magenta);
        signUpButton.setBorder(BorderFactory.createEtchedBorder());
        signUpButton.setFocusable(false);
        signUpButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        signUpButton.setForeground(Color.black);
        signUpButton.setOpaque(true);


        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.setBackground(new Color(0x6eaa6b));
        backButton.setBorder(BorderFactory.createEtchedBorder());
        backButton.setFocusable(false);
        backButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        backButton.setForeground(Color.black);
        backButton.setOpaque(true);


        // instantiate HomePageGUI to use its methods
        //HomePage homePage = new HomePage(client);

        controlPanel.add(signUpButton);
        controlPanel.add(backButton);
        topPanel.add(controlPanel);

        frame.add(topPanel);
        frame.setVisible(true);

    // action listeners
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
               // Signup : "SIGNUP " + name + " " + username + " " + password + " " + email + " " + languages + " " + major + " " + confirmPassword                try {
                    String outputString = "SIGNUP" + firstNameFeild.getText() + usernameFeild.getText() + passwordFeild.getText() + emailFeild.getText() + skillsFeild.getText() + majorFeild.getText() + confirmFeild.getText()  + "\n";
                    output.writeUTF(outputString);
                    // server response
                    String serverResponse = bfr.readLine();
                    System.out.println(serverResponse);
                    if (serverResponse.equals("true")) {
                        JOptionPane.showMessageDialog(frame, "Signup successful!");
                        frame.dispose();
                        start();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid Entry! Username already exists.");
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
                start();
            }
        });

   }


}