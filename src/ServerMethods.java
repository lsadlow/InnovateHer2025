import java.util.ArrayList;

public class ServerMethods {
    private Database db;


    public ServerMethods(Database db) {
        this.db = db;
        db.loadUsers();
    }

    public String serverFunctions(String infoSent) {
        db.loadProjects();
        db.loadUsers();
        System.out.println(infoSent);

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
                outcome = removeProject(split[1]);
                break;
            case "CHANGEUSERNAME":
                outcome = changeUsername(split[1], split[2]);
                break;
            case "CHANGEPASSWORD":
                outcome = changePassword(split[1], split[2]);
                break;
            /* case "ADDBIO":
                outcome = addBio(split[1],split[2]);
                break; */
            case "SENDREQUEST":
                outcome = sendRequest(split[1], split[2], split[3]);
                break;
            case "ACCEPTREQUEST":
                outcome = acceptRequest(split[1], split[2], split[3]);
                break;
            case "REJECTREQUEST":
                outcome = rejectRequest(split[1], split[2], split[3]);
                break;
            case "SETINVISIBLE":
                outcome = setInvisible(split[1]);
                break;
            case "SETVISIBLE":
                outcome = setVisible(split[1]);
                break;
        }
        db.updateDatabase();
        System.out.println("Outcome: "  + outcome);

        return outcome;
    }


    // Signup and login

    public String signup(String name, String username, String password, String email, String languages, String major, String confirmPassword) {
        try {
            // Replace backticks with spaces for compatibility
            major = major.replace("`", " ");
            languages = languages.replace("`", " ");

            // Validate the signup process with the database
            String result = db.confirmSignup(email, username, password, confirmPassword);
            if (result.equals("Signup successful!")) {
                // Create a new user if validation succeeds
                User user = new User(name, username, password, email, languages, major, "", "", "", "");
                db.addUser(user); // Add user to the database
            }

            return result; // Return the result of the signup attempt
        } catch (Exception e) {
            // Catch any unexpected exceptions and return a failure message
            e.printStackTrace();
            return "Failure";

        }
    }


    public String  login(String username, String password) {
        User toValidate = db.findUser(username);

        if (toValidate == null) {
            System.out.println("Login failed: User not found.");
            return "Faliure";
        }

        if (toValidate.getPassword().equals(password)) {
            System.out.println("Login successful.");
            return "Success";
        } else {
            System.out.println("Login failed: Incorrect password.");
            return "Faliure";
        }
    }



    // Language updates

    public String addProjectLanguages(String languages, String projectName) {
        languages = languages.replace("`", " ");
        String[] languagesArray = languages.split(",");
        ArrayList<String> projectLanguages = null;
        Project projectToEdit = null;
        projectName = projectName.replace("`", " ");
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
        userLanguages = userLanguages.replace("`", " ");
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
        description = description.replace("`", " ");
        projectName = projectName.replace("`", " ");
        languages = languages.replace("`", " ");
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
        description = description.replace("`", " ");
        projectName = projectName.replace("`", " ");
        languages = languages.replace("`", " ");
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
        projectName = projectName.replace("`", " ");
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

    /* public String addBio(String username, String bio) {
        User user = db.findUser(username);
        bio = bio.replace("`", " ");
        user.setBio(bio);
        return "Bio added";
    } */

    // Send, accept, and deny requests

    public String sendRequest(String message, String username, String projectName) {
        message = message.replace("`", " ");
        projectName = projectName.replace("`", " ");
        Request request = new Request(message, username, projectName);
        Project project = db.findProject(projectName);
        User currentUser = db.findUser(username);
        currentUser.addSentRequest(request);
        User owner = db.findUser(project.getPoster().getUsername());
        owner.addReceivedRequest(request);
        return "Request sent succesfully";
    }

    public String acceptRequest(String ownerUsername, String projectName, String requesterUsername) {
        User owner = db.findUser(ownerUsername);
        User requester = db.findUser(requesterUsername);
        projectName = projectName.replace("`", " ");
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
        projectName = projectName.replace("`", " ");
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

    public String setInvisible(String projectName) {
        projectName = projectName.replace("`", " ");
        Project project = db.findProject(projectName);
        project.makeInvisible();
        return "Set Invisible";
    }

    public String setVisible(String projectName) {
        projectName = projectName.replace("`", " ");
        Project project = db.findProject(projectName);
        project.makeVisible();
        return "Set Visible";
    }
}
