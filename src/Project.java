import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.io.*;

public class Project {
    private String name;
    private String description;
    private ArrayList languages;
    private User poster;
    private boolean visible;

    private String username;
    private Database db;

    public Project(String name, String description, String languages, String username) {
        this.name = name;
        this.description = description;
        this.languages = this.setLanguages(languages);
        this.username = username;
        Database db = new Database();
        db.loadUsers();
        this.poster = db.findUser(username);
        this.visible = true;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public void makeVisible() {
        this.visible = true;
    }

    public void makeInvisible() {
        this.visible = false;
    }

    public boolean isVisible() {
        return visible;
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
        languagesString = languagesString.substring(0, languagesString.length() - 1);
        String postToString = name + ";" + description + ";" + languagesString + ";" + poster.getUsername();
        return postToString;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public User getPoster() {
        return poster;
    }

}
