import java.util.ArrayList;

public class User {
    private String name;
    private String email;
    private String password;
    private ArrayList<String> languages;
    private String bio;
    private ArrayList<String> projectsOwned;
    private ArrayList<String> projectsOn;
    private String username;
    private String major;

    private ArrayList<Request> sentRequests;
    private ArrayList<Request> receivedRequests;

    public User(String name, String username, String password, String email, String languages, String major) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.setLanguages(languages);
        this.bio = "";
        this.projectsOwned = new ArrayList<String>();
        this.projectsOn = new ArrayList<String>();
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

    public ArrayList<String> getLanguages() {
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setProjectsOwned(String projects){
        String[] projectArray = projects.split(",");
        for (int i = 0; i < projectArray.length; i++) {
            this.projectsOwned.add(projectArray[i]);
        }
    }

    public void setProjectsOn(String projects){
        String[] projectArray = projects.split(",");
        for (int i = 0; i < projectArray.length; i++) {
            this.projectsOn.add(projectArray[i]);
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
    public void addProjectOwned(String projectName){
        this.projectsOwned.add(projectName);
    }

    public void addProjectOn(String projectName){
        this.projectsOn.add(projectName);
    }


    public void removeProjectOwned(String projectName) {
        for (int i = 0; i < this.projectsOwned.size(); i++) {
            if(projectsOwned.get(i).equals(projectName)) {
                projectsOwned.remove(i);
                break;
            }
        }
    }

    public void removeProjectOn(String projectName) {
        for (int i = 0; i < this.projectsOn.size(); i++) {
            if(projectsOn.get(i).equals(projectName)) {
                projectsOn.remove(i);
                break;
            }
        }
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

    public ArrayList<String> getProjectsOwned() {
        return projectsOwned;
    }

    public ArrayList<String> getProjectsOn() {
        return projectsOn;
    }

    public void addReceivedRequest(Request request) {
        receivedRequests.add(request);
    }

    public void addSentRequest(Request request) {
        sentRequests.add(request);
    }

}
