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
    private ArrayList<Project> projects;

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

    public ArrayList<Project> getProjects(){
        return projects;
    }

    // Confirming methods
    public String confirmEmail(String email){
        if(email.contains("@purdue.edu")) {
            return "Valid username";
        } else {
            return "Must be a Purdue username";
        }
    }

    public String confirmProjectName(String projectName){
        for(int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getName().equals(projectName)) {
                return "Project name is already taken";
            }
        }
        return "Valid project name";
    }

    public String confirmUsername(String username){
        for(int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(username)){
                return "Username is already taken";
            }
        }
        return "Valid username";
    }

    public String confirmPassword(String password) {
        boolean uppercase = false;
        boolean lowercase = false;
        boolean number = false;
        boolean noSpace = false;
        boolean correctLength = false;
        String issues = "";

        if (password.contains(" ")) {
            issues += "Password cannot contain spaces. ";
        } else {
            noSpace = true;
        }

        for(int i = 0; i < password.length(); i++) {
            if ((password.charAt(i) > 65) && (password.charAt(i) < 91)) {
                uppercase = true;
            }
        }
        if (!uppercase) {
            issues += "Password must have an uppercase letter. ";
        }

        for(int i = 0; i < password.length(); i++) {
            if ((password.charAt(i) > 96) && (password.charAt(i) < 123)) {
                lowercase = true;
            }
        }
        if (!lowercase) {
            issues += "Password must have a lowercase letter. ";
        }

        for(int i = 0; i < password.length(); i++) {
            if ((password.charAt(i) > 47) && (password.charAt(i) < 58)) {
                number = true;
            }
        }
        if (!number) {
            issues += "Password must have a number. ";
        }

        if(password.length() < 8 || password.length() > 15) {
            issues += "Password must be between 8 and 15 characters long. ";
        } else {
            correctLength = true;
        }

        if(noSpace && uppercase && lowercase && number && correctLength) {
            issues = "Valid Password";
        }
        return issues;
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
                User toBeAdded = new User(userParameters[0], userParameters[1], userParameters[2], userParameters[3]);
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
                projects.add(toBeAdded);
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
