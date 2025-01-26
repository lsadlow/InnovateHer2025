import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.io.*;

public class Project {
    private String name;
    private String description;
    private ArrayList<String> languages;
    private User poster;
    private boolean visible;
    private ArrayList<String> collaborators;

    private String username;
    private Database db;

    public Project(String name, String description, String languages, String username, String collaborators) {
        this.name = name;
        this.description = description;
        this.languages = this.setLanguages(languages);
        this.username = username;
        Database db = new Database();
        db.loadUsers();
        this.poster = db.findUser(username);
        this.collaborators = this.setCollaborators(collaborators);
        this.visible = true;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public boolean isVisible() {
        return visible;
    }

    public ArrayList<String> getCollaborators(){
        return collaborators;
    }

    public User getPoster() {
        return poster;
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }

    public void makeVisible() {
        this.visible = true;
    }

    public void makeInvisible() {
        this.visible = false;
    }

    public void setCollaborators(ArrayList<String> collaborators) {
        this.collaborators = collaborators;
    }

    public void addCollaborator(String username) {
        this.collaborators.add(username);
    }

    public void addLanguage(String language) {
        boolean notHere = true;
        for (int i = 0; i < languages.size(); i++)
            if (languages.get(i).equals(language)) {
                notHere = false;
                break;
            }
        if (notHere) {
            languages.add(language);
        }
    }


    public ArrayList<String> setLanguages(String languages) {
        String[] languagesArray = languages.split(",");
        ArrayList<String> newLanguages = new ArrayList<>();
        for (String language : languagesArray) {
            newLanguages.add(language);
        }
        return newLanguages;
    }

    public void removeLanguage(String language) {
        for (int i = 0; i < languages.size(); i++) {
            if (languages.get(i).equals(language)) {
                languages.remove(i);
                break;
            }
        }
    }

    public String toString() {
        String languagesString = "";
        for (int i = 0; i < languages.size(); i++) {
            languagesString += languages.get(i) + ",";
        }
        if (languagesString.length() <= 1) {
            languagesString = " ";
        } else {
            languagesString = languagesString.substring(0, languagesString.length() - 1);
        }
        String collaboratorsString = "";
        for (int i = 0; i < collaborators.size(); i++) {
            collaboratorsString += collaborators.get(i) + ",";
        }
        if (collaboratorsString.length() <= 1) {
            collaboratorsString = " ";
        } else {
            collaboratorsString = collaboratorsString.substring(0, collaboratorsString.length() - 1);
        }
        String postToString = name + ";" + description + ";" + languagesString + ";" + poster.getUsername() +";" + collaboratorsString;
        return postToString;
    }

    public ArrayList<String> setCollaborators(String collaborators) {
        String[] collaboratorArray = collaborators.split(",");
        ArrayList<String> newCollaborators = new ArrayList<>();
        for (String collaborator : collaboratorArray) {
            newCollaborators.add(collaborator);
        }
        return newCollaborators;
    }

}
