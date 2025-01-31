import java.io.*;
import java.util.ArrayList;

public class Database {

    private File userDataFile;
    private File projectDataFile;
    private BufferedReader userDataReader;
    private BufferedReader projectDataReader;
    private PrintWriter userDataWriter;
    private PrintWriter projectDataWriter;
    private static ArrayList<User> userList ;
    private ArrayList<Project> projects;

    public Database() {
        this.userDataFile = new File("users.txt");
        this.projectDataFile = new File("projects.txt");
        try {
            userDataWriter = new PrintWriter(new FileWriter(userDataFile) , true);
            userDataReader = new BufferedReader(new FileReader(userDataFile));
            projectDataWriter = new PrintWriter(new FileWriter(projectDataFile) , true);
            projectDataReader = new BufferedReader(new FileReader(projectDataFile));
            System.out.println("file i/o objects created successfully");

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.userList = new ArrayList<>();
        this.projects = new ArrayList<>();
        loadUsers();
        loadProjects();
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    // Confirming methods
    public String confirmSignup(String email, String username, String password, String confirmpassword) {
        String results = "";
        results += confirmEmail(email) + confirmUsername(username) + confirmPassword(password, confirmpassword);
        if (results.isEmpty()) {
            return "Signup successful!";
        } else {
            return results;
        }
    }

    public String confirmEmail(String email) {
        if (email.contains("@purdue.edu")) {
            return "";
        } else {
            return "Must be a Purdue email. ";
        }
    }

    public String confirmProjectName(String projectName) {
        if(projects == null) {
            return "Valid project name.";
        }
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getName().equals(projectName)) {
                return "Project name is already taken.";
            }
        }
        return "Valid project name. ";
    }

    public String confirmUsername(String username) {

        if (userList == null) {
            return "" ;
        } else {
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getUsername().equals(username)) {
                    return "Username is already taken. ";
                }

            }
        }

        return "";
    }

    public String confirmPassword(String password, String confirmPassword) {
        System.out.println(password);
        System.out.println(confirmPassword);
        boolean uppercase = false;
        boolean lowercase = false;
        boolean number = false;
        String issues = "";

        if (password.contains(" ")) {
            issues += "Password cannot contain spaces. ";
        }

        for (int i = 0; i < password.length(); i++) {
            if ((password.charAt(i) > 65) && (password.charAt(i) < 91)) {
                uppercase = true;
            }
        }
        if (!uppercase) {
            issues += "Password must have an uppercase letter. ";
        }

        for (int i = 0; i < password.length(); i++) {
            if ((password.charAt(i) > 96) && (password.charAt(i) < 123)) {
                lowercase = true;
            }
        }
        if (!lowercase) {
            issues += "Password must have a lowercase letter. ";
        }

        for (int i = 0; i < password.length(); i++) {
            if ((password.charAt(i) > 47) && (password.charAt(i) < 58)) {
                number = true;
            }
        }
        if (!number) {
            issues += "Password must have a number. ";
        }

        if (password.length() < 8 || password.length() > 15) {
            issues += "Password must be between 8 and 15 characters long. ";
        }

        System.out.println("Password:" + password + "CP :" + confirmPassword);

        if (!(password.equals(confirmPassword))) {
            issues += "Confirm password does not match. ";
        }

        return issues;
    }

    public void addUser(User user) {
        System.out.println("at addUser method");
        userList.add(user);

        try (PrintWriter userFileWriter = new PrintWriter(new FileWriter(userDataFile, true))) {
            userFileWriter.println(user.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("User added successfully");
    }


    public ArrayList<User> getUsers() {
        return userList;
    }

    public void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Users.txt"))){
            String line ;
            while ((line = reader.readLine()) != null) {
                String[] userParameters = line.split(";");
                User toBeAdded = new User(userParameters[0], userParameters[1], userParameters[2], userParameters[3], userParameters[4], userParameters[5], userParameters[6], userParameters[7], userParameters[8], userParameters[9]);
                userList.add(toBeAdded);

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
                Project toBeAdded = new Project(projectParameters[0], projectParameters[1], projectParameters[2], projectParameters[3], projectParameters[4]);
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
        loadUsers();
        System.out.println("Parameter received: " + username);

        for (User user : userList) {
            System.out.println(user);
            if (user.getUsername().equals(username)) {
                System.out.println("----------");
                System.out.println(user.getUsername().equals(username));
                return user;
            }

        }
        return null;
    }

    public Project findProject(String projectName) {
        ArrayList<Project> projectList = this.getProjects();
        for (Project project : projectList) {
            if (project.getName().equals(projectName)) {
                return project;
            }
        }
        return null;
    }

    public boolean projectIsUsers(String projectName, String username) {
        Project project = this.findProject(projectName);
        return project.getUsername().equals(username);
    }

    public String removeProject(String projectName) {
        Project toRemove = findProject(projectName);
        for (Project project : projects) {
            if (project.getName().equals(projectName)) {
                projects.remove(project);
                break;
            }
        }
        User poster = toRemove.getPoster();
        poster.removeProjectOwned(projectName);
        if(toRemove.getCollaborators() == null) {
            return "Error";
        }
        for (int i = 0; i < toRemove.getCollaborators().size(); i++) {
            User user = findUser(toRemove.getCollaborators().get(i));
            user.removeProjectOn(projectName);
        }
        return "Project removed";
    }

    public String getUsersProjects(String username) {
        String projects = "";
        try {
            User findUser = this.findUser(username);
            ArrayList<String> projectOwnedList = findUser.getProjectsOwned();
            ArrayList<String> projectsOnList = findUser.getProjectsOn();
            for (String project : projectOwnedList) {
                projects += project + ",";
            }
            projects += " ";
            for (String project : projectsOnList) {
                projects += project + ",";
            }
            return projects;
        } catch (Exception e) {
            e.printStackTrace();
            return "Failure";
        }
    }

    public void updateDatabase() {
        try {
            System.out.println("updateDatabase reached");

            // First, reload data to avoid overwriting issues
            loadUsers();
            loadProjects();

            // Rewrite the files with current data
            try (PrintWriter userFileWriter = new PrintWriter(new FileWriter(userDataFile));
                 PrintWriter projectFileWriter = new PrintWriter(new FileWriter(projectDataFile))) {

                if (userList != null) {
                    for (User user : userList) {
                        userFileWriter.println(user.toString());
                    }
                }

                if (projects != null) {
                    for (Project project : projects) {
                        projectFileWriter.println(project.toString());
                    }
                }

                System.out.println("Database updated successfully");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
