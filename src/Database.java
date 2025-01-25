import java.io.*;
import java.util.ArrayList;

public class Database {

    private File userDataFile;
    private File projectDataFile;
    private BufferedReader userDataReader;
    private BufferedReader projectDataReader;
    private PrintWriter userDataWriter;
    private PrintWriter projectDataWriter;
    private ArrayList<User> userList;
    private ArrayList<Project> projectList;

    public Database() {
        this.userDataFile = new File("users.txt");
        this.projectDataFile = new File("projects.txt");
        try {
            PrintWriter userDataWriter = new PrintWriter(new FileWriter(userDataFile));
            userDataReader = new BufferedReader(new FileReader(userDataFile));
            PrintWriter projectDataWriter = new PrintWriter(new FileWriter(projectDataFile));
            projectDataReader = new BufferedReader(new FileReader(projectDataFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void addUser(User user) {
        userDataWriter.println(user.toString());
        userDataWriter.flush();
        userList.add(user);
    }

    public void loadUsers() {
        try {
            String line = userDataReader.readLine();
            while (line != null) {
                String[] userParameters = line.split(" ");
                User toBeAdded = new User(userParameters[0], userParameters[1], userParameters[2], userParameters[3], userParameters[4]);
                userList.add(toBeAdded);
                line = userDataReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveProject(Project project) {
        String projectString = project.toString();
        projectDataWriter.println(projectString);
    }

    public void loadProjects() {
        try {
            String line = projectDataReader.readLine();
            while (line != null) {
                String[] projectParameters = line.split(" ");
                Project toBeAdded = new Project(projectParameters[0], projectParameters[1], projectParameters[2], projectParameters[3]);
                projectList.add(toBeAdded);
                line = projectDataReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

}
