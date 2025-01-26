public class Test {
    public static void main(String[] args) {
        Database db = new Database();
        ServerMethods serverMethods = new ServerMethods(db) ;
        serverMethods.login("jen3" , "Gru12345") ;
        db.findUser("jen3") ;



    }
}
