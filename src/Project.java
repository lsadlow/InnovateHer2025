import java.util.ArrayList;

public class Project {
    private String description;
    private ArrayList languages;
    private User poster;
    private String username;

    public Project(String description, String languages, String username) {
        this.description = description;
        this.languages = this.setLanguages(languages);
        this.username = username;
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
        String postToString = description + " " + languagesString + " " + poster.getName();
        return postToString;
    }
}
