import java.util.ArrayList;

public class Project {
    private String description;
    private ArrayList<String> languages;
    private User poster;
    private String name;
    private Database db;

    public Project(String name, String description, String languages, String username) {
        this.name = name;
        this.description = description;
        this.languages = this.setLanguages(languages);
        db = new Database();
        db.loadUsers();
        this.poster = findUser(username);
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

    public void removeLanguage(String language) {
        languages.remove(language);
    }

    public ArrayList<String> setLanguages(String languages) {
        ArrayList<String> newLanguages = new ArrayList<>();
        String[] languagesArray = languages.split(",");
        for (String language : languagesArray) {
            newLanguages.add(language);
        }
        return newLanguages;
    }
    public String toString() {
        String languagesString = "";
        for (int i = 0; i < languages.size(); i++) {
            languagesString += languages.get(i) + ",";
        }
        languagesString = languagesString.substring(0, languagesString.length() - 1);
        String postToString = description + " " + languagesString + " " + poster.getName();
        return postToString;
    }
}
