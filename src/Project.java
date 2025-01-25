import java.util.ArrayList;

public class Project {
    private String description;
    private ArrayList languages;
    private int numOfPeople;  //how many people looking to collaborate with
    private User poster;
    private int activePeople;  //number of people on project

    public Project(String description, ArrayList languages, int numOfPeople, User poster) {
        this.description = description;
        this.languages = languages;
        this.numOfPeople = numOfPeople;
        this.poster = poster;
        this.activePeople = 1;
    }

    public void addCollaborator() {
        activePeople++;
    }

    public void addLanguage(String language) {
        languages.add(language);
    }

    public void removeLanguage(String language) {
        languages.remove(language);
    }
}
