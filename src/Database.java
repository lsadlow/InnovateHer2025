import java.io.*;
import java.util.ArrayList;

public class Database {

    private File userDataFile;
    private File projectDataFile;
    private ArrayList<File> userProjectFiles;
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
        this.userProjectFiles = new ArrayList<>();

    }

    public ArrayList<Project> getProjects(){
        return projects;
    }

    // Confirming methods
    public String confirmSignup(String email, String username, String password) {
        String results = "";
        results += confirmEmail(email) + confirmUsername(username) + confirmPassword(password);
        if (results.isEmpty()) {
            return "Signup successful!";
        } else {
            return results;
        }
    }

    public String confirmEmail(String email){
        if(email.contains("@purdue.edu")) {
            return "";
        } else {
            return "Must be a Purdue email. ";
        }
    }

    public String confirmProjectName(String projectName){
        for(int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getName().equals(projectName)) {
                return "Project name is already taken";
            }
        }
        return "Valid project name. ";
    }

    public String confirmUsername(String username){
        for(int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(username)){
                return "Username is already taken. ";
            }
        }
        return "";
    }

    public String confirmPassword(String password) {
        boolean uppercase = false;
        boolean lowercase = false;
        boolean number = false;
        String issues = "";

        if (password.contains(" ")) {
            issues += "Password cannot contain spaces. ";
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
                String[] userParameters = line.split(";");
                User toBeAdded = new User(userParameters[0], userParameters[1], userParameters[2], userParameters[3], userParameters[4], userParameters[5]);
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
        projectDataWriter.flush();
    }

    public void loadProjects() {
        try {
            String line = projectDataReader.readLine();
            while (line != null) {
                String[] projectParameters = line.split(";");
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

    public User findUser(String username) {
        ArrayList<User> userList= this.getUserList();
        for (User user : userList) {
            if (user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public Project findProject(String projectName) {
        ArrayList<Project> projectList= this.getProjects();
        for (Project project : projectList) {
            if (project.getName().equals(projectName)) {
                return project;
            }
        }
        return null;
    }

    public String removeProject(String projectName) {
        for (Project project : projects) {
            if (project.getName().equals(projectName)) {
                projects.remove(project);
                return "Success";
            }
        }
        return "Failure";
    }

    public String createUserProjectFiles() {
        this.loadUsers();
        for (User user : userList) {
            String fileName = user.getUsername() + "Projects.txt";
            ArrayList<String> projectsLeading = user.getProjects();
            try {
                File file = new File(fileName);
                PrintWriter fileWriter = new PrintWriter(new FileWriter(file));
                fileWriter.println("Leading:");
                for (String project : projectsLeading) {
                    fileWriter.println(project);
                    fileWriter.flush();
                }
                fileWriter.println("Working on:");
                ArrayList<String> projectsTrailing = user.getProjects();
                for (String project : projectsTrailing) {
                    fileWriter.println(project);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "Failure";
            }
        }
    }

    public String getUserProjectFile(String username) {
        try {
            User user = this.findUser(username);
            BufferedReader fileReader = new BufferedReader(new FileReader(username + "Projects.txt"));
            fileReader.readLine();
            String line = fileReader.readLine();
            ArrayList<String> projectLeaderNames = new ArrayList<String>();
            while (line != null && !line.equals("Working on:")) {
                projectLeaderNames.add(line);
                line = fileReader.readLine();
            }
            String workingLine = fileReader.readLine();
            ArrayList<String> workingNames = new ArrayList<String>();
            while (workingLine != null && !workingLine.equals("Working on:")) {
                workingNames.add(workingLine);
                workingLine = fileReader.readLine();
            }
            String leaderLine = "";
            for (int i = 0; i < projectLeaderNames.size(); i++) {
                leaderLine += projectLeaderNames.get(i) + ",";
            }
            leaderLine = leaderLine.substring(0, leaderLine.length() - 1);
            String workingOutput = "";
            for (int i = 0; i < workingNames.size(); i++) {
                workingOutput += workingNames.get(i) + ",";
            }
            workingOutput = workingOutput.substring(0, workingOutput.length() - 1);
            return leaderLine + " " + workingOutput;
        } catch (IOException e) {
            e.printStackTrace();
            return "Failure";
        }
    }
}
