import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.io.*;

public class Project {
    private String name;
    private String description;
    private ArrayList languages;
    private User poster;

    private String username;
    private Database db;

    public Project(String name, String description, String languages, String username) {
        this.name = name;
        this.description = description;
        this.languages = this.setLanguages(languages);
        this.username = username;
        Database db = new Database();
        db.loadUsers();
        this.poster = this.findUser(username);
    }

    public String getName() {
        return name;
    }

    public User findUser(String username) {
        ArrayList<User> userList= db.getUserList();
        for (User user : userList) {
            if (user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void addLanguage(String language) {
        languages.add(language);
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
        languages.remove(language);
    }

    public String toString() {
        String languagesString = "";
        for (int i = 0; i < languages.size(); i++) {
            languagesString += languages.get(i) + ",";
        }
        languagesString = languagesString.substring(0, languagesString.length() - 1);
        String postToString = name + " " + description + " " + languagesString + " " + poster.getName();
        return postToString;
    }
}
