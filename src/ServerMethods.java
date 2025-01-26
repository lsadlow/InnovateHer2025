import java.util.ArrayList;

public class ServerMethods {
    private Database db;

    public ServerMethods(Database db) {
        this.db = db;
    }

    public String serverFunctions(String infoSent) {
        String[] split = infoSent.split(" ");
        String action = split[0];
        String outcome = "";
        switch (action) {
            case "SIGNUP":
                outcome = signup(split[1], split[2], split[3], split[4], split[5], split[6]);
                break;
            case "LOGIN":
                outcome = login(split[1], split[2]);
                break;
            case "ADDPROJECTLANGUAGES":
                outcome = addProjectLanguages(split[1], split[2]);  //parameters are language list and project name
                break;
            case "ADDUSERLANGUAGES":
                outcome = addUserLanguages(split[1], split[2]); //parameters are language list and username
                break;
            case "ADDPROJECT":
                outcome = addProject(split[1], split[2], split[3], split[4]);  //parameters are project name,
                // description, language list, and username of poster
                break;
            case "REMOVEPROJECT":
                outcome = db.removeProject(split[1]);
            case "CHANGEUSERNAME":
                outcome =

        }
        return outcome;
    }

    public String signup(String name, String username, String password,String email, String languages, String major){
        String result = db.confirmSignup(email, username, password);
        if(result.equals("Signup successful!")) {
            User user = new User(name, username, password, email, languages, major);
            db.addUser(user);
        }
        return result;
          
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
            projectPoster.addProjectOwned(projectName);
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
            User poster = toRemove.getPoster();
            poster.removeProjectOwned(projectName);
            return "Project Removed Successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failure";
        }

    }

    public String changeUsername(String currentUsername, String newUsername) {
        try {
            User toChange = db.findUser(currentUsername);
            toChange.setUsername(newUsername);

        }
    }

}
