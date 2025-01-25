import java.util.ArrayList;

public class User {
    private String name;
    private String email;
    private String password;
    private ArrayList<String> languages;
    private String bio;
    private ArrayList<String> projects;
    private String username;
    private String major;

    public User(String name, String username, String password, String email, String languages, String major) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.setLanguages(languages);
        this.bio = "";
        this.projects = new ArrayList<String>();
        this.username = username;
        this.major = major;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getUsername(){
        return username;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList getLanguages() {
        return languages;
    }

    public String getPassword(){
        return password;
    }

    public String getBio(){
        return bio;
    }

    // Setters
    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setProjects(String projects){
        String[] projectArray = projects.split(",");
        for (int i = 0; i < projectArray.length; i++) {
            this.projects.add(projectArray[i]);
        }
    }

    public void setLanguages(String languages) {  //input is languages separated by commas (no spaces)
        String[] languageArray = languages.split(",");
        for (int i = 0; i < languageArray.length; i++) {
            this.languages.add(languageArray[i]);
        }
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    // Other methods
    public void addProject(String projectName){
        this.projects.add(projectName);
    }

    public String toString() {  //for sending to database addUser() method
        String languagesToString = "";
        for (String language : languages) {
            languagesToString += language + ",";
        }
        languagesToString = languagesToString.substring(0, languagesToString.length() - 1);
        String userString = name + ";" + username+ ";" + password + ";" + email + languagesToString + ";" + bio + ";" + username + ";" + major;
        return userString;
    }

}
