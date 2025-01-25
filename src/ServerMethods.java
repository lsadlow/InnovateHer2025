import java.util.ArrayList;

public class ServerMethods {
    Database db;
    // Signup - name, username grade, email (check that email has purdue in it), major, password
    //Login - username, password
    //Add languages/skills
    //Add project
    //Remove project
    public ServerMethods(Database db) {
        this.db = db;
    }
    public String serverFunctions(String infoSent) {
        String[] split = infoSent.split(" ");
        String action = split[0];
        switch(action) {
            case "LOGIN":
                String outcome = login(split[1], split[2]);
            case "ADDPROJECTLANGUAGES":
                String projectLanguageOutcome = addProjectLanguages(split[1], split[2]);  //parameters are language list and project name
            case "ADDUSERLANGUAGES":
                String userLanguageOutcome = addUserLanguages(split[1], split[2]); //parameters are language list and username
            case "Signup":
            case "ADDPROJECT":
                String addProjectOutcome = addProject(split[1], split[2], split[3], split[4]);  //parameters are project name, description, language list, and username of poster




        }
    }

    public String signup(String name, String username, String password,String email, String languages) {

    }

    public String login(String username, String password) {
        User toValidate = db.findUser(username);
        String userPassword = toValidate.getPassword();
        if(userPassword.equals(password)) {
            return "Success";
        } else {
            return "Failure";
        }
    }

    public String addProjectLanguages(String languages, String projectName) {
        String[] languagesArray = languages.split(",");
        ArrayList<String> projectLanguages = null;
        Project projectToEdit = null;
        try {
            projectToEdit = db.findProject(projectName);
            projectLanguages = projectToEdit.getLanguages();
        } catch (Exception e) {
            e.printStackTrace();
            return "Failure";
        }
        for (String language : languagesArray) {
            if (projectLanguages.contains(language)) {
                continue;
            }
            projectToEdit.addLanguage(language);
        }
        return "Languages Added Successfully";
    }

    public String addUserLanguages(String userLanguages, String username) {
        String[] languagesArray = userLanguages.split(",");
        User userToEdit = null;
        ArrayList<String> userLanguagesArray = null;
        try {
            userToEdit = db.findUser(username);
            userLanguagesArray = userToEdit.getLanguages();
        } catch (Exception e) {
            e.printStackTrace();
            return "Failure";
        }
        for (String language : languagesArray) {
            if (userLanguagesArray.contains(language)) {
                continue;
            }
            userLanguagesArray.add(language);
        }
        return "Languages Added Successfully";
    }

    public String addProject(String projectName, String description, String languages, String username) {
        Project toAdd = new Project(projectName, description, languages, username);
        try {
            db.saveProject(toAdd);
            User projectPoster = db.findUser(username);
            projectPoster.addProject(projectName);
        } catch (Exception e) {
            e.printStackTrace();
            return "Failure";
        }
        return "Project Added Successfully";
    }

    public String removeProject(String projectName) {
        try {
            Project toRemove = db.findProject(projectName);
            db.removeProject(projectName);
            return "Project Removed Successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failure";
        }

    }

}
