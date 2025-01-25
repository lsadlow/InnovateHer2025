public class ServerMethods {

    // Signup - name, username grade, email (check that email has purdue in it), major, password
    //Login - username, password
    //Add languages/skills
    //Add project
    //Remove project

    public String buttonServer(String infoSent){
        String[] split = infoSent.split(" ");
        String action = split[0];
        switch(action) {
            case "Signup":



        }
    }

    public String signup(String name, String username, String password,String email, String languages){
        User user = new User(name, )
    }

}
