public class ServerMethods {
    private Database db;
    // Signup - name, username grade, email (check that email has purdue in it), major, password
    //Login - username, password
    //Add languages/skills
    //Add project
    //Remove project

    public String serverFunctions(String infoSent){
        String[] split = infoSent.split(" ");
        String action = split[0];
        switch(action) {
            case "SIGNUP":
                return signup(split[1], split[2], split[3], split[4], split[5], split[6]);


        }
    }

    // string sent over contains "SIGNUP name, username, password, email, languages
    // public String confirmSignup(String email, String username, String password)
    public String signup(String name, String username, String password,String email, String languages, String major){
        String result = db.confirmSignup(email, username, password);
        if(result.equals("Signup successful!")) {
            User user = new User(name, username, password, email, languages, major);
            db.addUser(user);
        }
        return result;
    }

}
