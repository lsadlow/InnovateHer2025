import java.util.ArrayList;

public class User {
    private String name;
    private String email;
    private String password;
    private ArrayList<String> languages;
    private String bio;

    public User(String name, String email, String password, String languages) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.setLanguages(languages);
        this.bio = "";
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList getLanguages() {
        return languages;
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

    public String toString() {  //for sending to database addUser() method
        String languagesToString = "";
        for (String language : languages) {
            languagesToString += language + ",";
        }
        languagesToString = languagesToString.substring(0, languagesToString.length() - 1);
        String userString = name + " " + email + " " + password + " " + languagesToString + " " + bio;
        return userString;
    }

}
