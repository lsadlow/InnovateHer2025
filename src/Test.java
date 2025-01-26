public class Test {
    public static void main(String[] args) {
        Database db = new Database();
        User user = new User("Jen", "jen3", "Gru12345", "Jen@purdue.edu", "python", "CS", "", "", "", "");
        db.addUser(user);
    }
}
