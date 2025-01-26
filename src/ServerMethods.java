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
                outcome = signup(split[1], split[2], split[3], split[4], split[5], split[6], split[7]);
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
            case "ADDPROJECTOWNED":
                outcome = addProjectOwned(split[1], split[2], split[3], split[4]);  //parameters are project name,
                // description, language list, and username of poster
                break;
            case "ADDPROJECTON":
                outcome = addProjectOn(split[1], split[2], split[3], split[4]);  //parameters are project name,
                // description, language list, and username of poster
                break;
            case "REMOVEPROJECT":
                outcome = db.removeProject(split[1]);
                break;
            case "CHANGEUSERNAME":
                outcome = changeUsername(split[1], split[2]);
                break;
            case "CHANGEPASSWORD":
                outcome = changePassword(split[1], split[2]);
                break;
            case "ADDBIO":
                outcome = addBio(split[1],split[2]);
                break;
            case "ACCEPTREQUEST":
                outcome = acceptRequest(split[1], split[2], split[3]);
                break;
            case "REJECTREQUEST":
                outcome = rejectRequest(split[1], split[2], split[3]);
        }
        db.updateDatabase();
        return outcome;
    }

    // Signup and login

    public String signup(String name, String username, String password,String email, String languages, String major,
                         String confirmPassword){
        String result = db.confirmSignup(email, username, password, confirmPassword);
        if(result.equals("Signup successful!")) {
            User user = new User(name, username, password, email, languages, major, "", "", "", "");
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

    // Language updates

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

    // add/remove projects

    public String addProjectOwned(String projectName, String description, String languages, String username) {
        if (db.confirmProjectName(projectName).equals("Project name is already taken")) {
            return "Project name is already taken";
        }
        Project toAdd = new Project(projectName, description, languages, username, "");
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

    public String addProjectOn(String projectName, String description, String languages, String username) {
        Project toAdd = new Project(projectName, description, languages, username, "");
        try {
            db.saveProject(toAdd);
            User projectPoster = db.findUser(username);
            projectPoster.addProjectOn(projectName);
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
            for(int i = 0; i < toRemove.getCollaborators().size(); i++) {
                User user = db.findUser(toRemove.getCollaborators().get(i));
                user.removeProjectOn(projectName);
            }
            return "Project Removed Successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failure";
        }
    }

    // Edit user attributes

    public String changeUsername(String currentUsername, String newUsername) {
        try {
            User toChange = db.findUser(currentUsername);
            toChange.setUsername(newUsername);
            return "Username Changed Successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failure";
        }
    }

    public String changePassword(String currentUsername, String newPassword) {
        try {
            User toChange = db.findUser(currentUsername);
            toChange.setPassword(newPassword);
            return "Password Changed Successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failure";
        }
    }

    public String addBio(String username, String bio) {
        User user = db.findUser(username);
        user.setBio(bio);
        return "Bio added";
    }

    // Accept and deny requests

    public String acceptRequest(String ownerUsername, String projectName, String requesterUsername) {
        User owner = db.findUser(ownerUsername);
        User requester = db.findUser(requesterUsername);
        for(int i = 0; i < owner.getReceivedRequests().size(); i++) {
            if((owner.getReceivedRequests().get(i).getSender().getUsername().equals(requesterUsername)) &&
                    owner.getReceivedRequests().get(i).getProjectName().equals(projectName)) {
                owner.getReceivedRequests().get(i).acceptRequest();
                db.findProject(projectName).addCollaborator(requesterUsername);
            }
        }
        for(int i = 0; i < requester.getSentRequests().size(); i++) {
            if(requester.getSentRequests().get(i).getProjectName().equals(projectName)) {
                requester.getSentRequests().get(i).acceptRequest();
            }
        }
        return "Successfully Accepted";
    }

    public String rejectRequest(String ownerUsername, String projectName, String requesterUsername) {
        User owner = db.findUser(ownerUsername);
        User requester = db.findUser(requesterUsername);
        for(int i = 0; i < owner.getReceivedRequests().size(); i++) {
            if((owner.getReceivedRequests().get(i).getSender().getUsername().equals(requesterUsername)) &&
                    owner.getReceivedRequests().get(i).getProjectName().equals(projectName)) {
                owner.getReceivedRequests().get(i).rejectRequest();
            }
        }
        for(int i = 0; i < requester.getSentRequests().size(); i++) {
            if(requester.getSentRequests().get(i).getProjectName().equals(projectName)) {
                requester.getSentRequests().get(i).rejectRequest();
            }
        }
        return "Successfully Rejected";
    }
}
