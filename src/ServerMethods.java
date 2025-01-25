public class ServerMethods {
    Database db;
    // Signup - name, username grade, email (check that email has purdue in it), major, password
    //Login - username, password
    //Add languages/skills
    //Add project
    //Remove project
    public ServerMethods(Database db) {
        this.db = db;
    }
    public String serverFunctions(String infoSent){
        String[] split = infoSent.split(" ");
        String action = split[0];
        switch(action) {
            case "LOGIN":
                String outcome = login(split[1], split[2]);
            case "Signup":




        }
    }

    public String signup(String name, String username, String password,String email, String languages){

    }

    public String login(String username, String password) {
        User toValidate = db.findUser(username);
        String userPassword = toValidate.getPassword();
        if(userPassword.equals(password)) {
            return "Success";
        } else {
            return "Failure";
        }
    }

}
