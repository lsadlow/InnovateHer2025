import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class OwnProjectsPage implements Runnable {
   private User user;
   private DataInputStream in;
   private DataOutputStream out;

   public OwnProjectsPage(User user, DataInputStream in, DataOutputStream out) {
       this.user = user;
       this.in = in;
       this.out = out;
   }

   public void run() {
       displayOwnProjects();
   }

   public void displayOwnProjects() {
       JFrame frame = new JFrame("Own Projects");
       JPanel panel = new JPanel();
       panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       ArrayList<String> ownProjects = user.getProjectsOwned();
       ArrayList<String> projectsOn = user.getProjectsOn();

       JLabel titleLabelOne = new JLabel("Own Projects");
       JLabel titleLabelTwo = new JLabel("Projects Working On");

       JComboBox comboBoxOne = new JComboBox(ownProjects.toArray());
       JComboBox comboBoxTwo = new JComboBox(projectsOn.toArray());
       comboBoxOne.setSelectedIndex(0);
       comboBoxTwo.setSelectedIndex(0);
       comboBoxOne.setEditable(false);
       comboBoxTwo.setEditable(false);
       panel.add(titleLabelOne);
       panel.add(comboBoxOne);
       panel.add(titleLabelTwo);
       panel.add(comboBoxTwo);
       frame.add(panel);
       frame.setVisible(true);
   }

}
