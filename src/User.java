import java.util.ArrayList;

public class User {
    private String name;
    private String email;
    private String password;
    private ArrayList<String> languages;
    private ArrayList<String> projectsOwned;
    private ArrayList<String> projectsOn;
    private String username;
    private String major;

    private ArrayList<Request> sentRequests;
    private ArrayList<Request> receivedRequests;

    public User(String name, String username, String password, String email, String languages, String major, String projectsOwned, String projectsOn, String sentRequests, String receivedRequests) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.languages = new ArrayList<String>();
        this.setLanguages(languages);
        this.projectsOwned = setProjectsOwned(projectsOwned);
        this.projectsOn = setProjectsOn(projectsOn);
        this.sentRequests = new ArrayList<Request>();
        if(!(sentRequests.isEmpty()) && !(sentRequests == null)) {
            this.sentRequests = setSentRequests(sentRequests);
        }
        this.receivedRequests = new ArrayList<Request>();
        if(!(receivedRequests.isEmpty()) && !(receivedRequests == null)) {
            this.receivedRequests = setReceivedRequests(receivedRequests);
        }
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

    public ArrayList<String> getProjectsOwned() {
        return projectsOwned;
    }

    public ArrayList<String> getProjectsOn() {
        return projectsOn;
    }

    public ArrayList<Request> getSentRequests() {
        return sentRequests;
    }

    public ArrayList<Request> getReceivedRequests() {
        return receivedRequests;
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

    public ArrayList<String> setProjectsOwned(String projects) {
        if (projects.equals(" ")) {
            return new ArrayList<String>();
        }
        String[] projectArray = projects.split(",");
        ArrayList<String> projectsOwnedList = new ArrayList<>();
        for (int i = 0; i < projectArray.length; i++) {
            projectsOwnedList.add(projectArray[i]);
        }
        return projectsOwnedList;
    }

    public ArrayList<String> setProjectsOn(String projects) {
        if (projects.equals(" ")) {
            return new ArrayList<String>();
        }
        String[] projectArray = projects.split(",");
        ArrayList<String> projectsOnList = new ArrayList<>();
        for (int i = 0; i < projectArray.length; i++) {
            projectsOnList.add(projectArray[i]);
        }
        return projectsOnList;
    }

    public void setLanguages(String languages) {  //input is languages separated by commas (no spaces)
        if (languages.length() <= 1) {
            return;
        }
        String[] languageArray = languages.split(",");

        for (int i = 0; i < languageArray.length; i++) {
            this.languages.add(languageArray[i]);
        }
    }

    public void setProjectsOwned(ArrayList<String> projects) {
        this.projectsOwned = projects;
    }

    public void setProjectsOn(ArrayList<String> projects) {
        this.projectsOn = projects;
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
        if (languagesToString.length() <= 1) {
            languagesToString = " ";
        } else {
            languagesToString = languagesToString.substring(0, languagesToString.length() - 1);
        }
        String userString = name + ";" + username + ";" + password + ";" + email + ";" + languagesToString + ";" + major + ";";
        String projectsOwnedString = "";
        for (int i = 0; i < projectsOwned.size(); i++) {
            projectsOwnedString += projectsOwned.get(i) + ",";
        }
        if (projectsOwnedString.length() <= 1) {
            projectsOwnedString = " ";
        } else {
            projectsOwnedString = projectsOwnedString.substring(0, projectsOwnedString.length() - 1);
        }
        String projectsOnString = "";
        for (int i = 0; i < projectsOn.size(); i++) {
            projectsOnString += projectsOn.get(i) + ",";
        }
        if (projectsOnString.length() <= 1) {
            projectsOnString = " ";
        } else {
            projectsOnString = projectsOnString.substring(0, projectsOnString.length() - 1);
        }
        userString = userString + projectsOwnedString + ";" + projectsOnString + ";";
        for (int i = 0; i < sentRequests.size(); i++) {
            userString += sentRequests.get(i) + ",";
        }
        if (sentRequests.size() == 0)
            userString += " ";
        else
            userString = userString.substring(0, userString.length() - 1);
        userString += ";";
        for (int i = 0; i < receivedRequests.size(); i++) {
            userString += receivedRequests.get(i) + ",";
        }
        if (receivedRequests.size() <= 1)
            userString += " ";
        else
            userString = userString.substring(0, userString.length() - 1);
        userString += ";";
        return userString;
    }

    public void addReceivedRequest(Request request) {
        receivedRequests.add(request);
    }

    public void addSentRequest(Request request) {
        sentRequests.add(request);
    }

    public void removeSentRequest(Request request) {
        sentRequests.remove(request);
    }

    public void removeReceivedRequest(Request request) {
        receivedRequests.remove(request);
    }

    public ArrayList<Request> setSentRequests(String requests) {
        if (requests.equals(" ")) {
            return new ArrayList<Request>();
        }
        String[] requestArray = requests.split(",");
        ArrayList<Request> sentRequestList = new ArrayList<Request>();
        for (int i = 0; i < requestArray.length; i++) {
            Request toAdd = new Request(requestArray[0], requestArray[1], requestArray[2]);
            sentRequestList.add(toAdd);
        }
        return sentRequestList;
    }

    public ArrayList<Request> setReceivedRequests(String requests) {
        if (requests.equals(" ")) {
            return new ArrayList<Request>();
        }
        String[] requestArray = requests.split(",");
        ArrayList<Request> receivedRequestList= new ArrayList<Request>();
        for (int i = 0; i < requestArray.length; i++) {
            Request toAdd = new Request(requestArray[0], requestArray[1], requestArray[2]);
            receivedRequestList.add(toAdd);
        }
        return receivedRequestList;
    }

}
