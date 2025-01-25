import java.util.ArrayList;

public class Project {
    private String description;
    private ArrayList languages;
    private User poster;

    public Project(String description, ArrayList languages, User poster) {
        this.description = description;
        this.languages = languages;
        this.poster = poster;
    }


    public void addLanguage(String language) {
        languages.add(language);
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
        String postToString = description + " " + languagesString + " " + poster.getName();
        return postToString;
    }
}
