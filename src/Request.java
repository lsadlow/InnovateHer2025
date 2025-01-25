public class Request {
    private String message;
    private User sender;
    private String status;
    private String projectName;
    private User projectOwner;

    public Request(String message, String senderUsername, String projectName) {
        this.message = message;
        Database db = new Database();
        db.loadUsers();
        this.sender = db.findUser(senderUsername);
        this.status = "pending";
        this.projectName = projectName;
        db.loadProjects();
        Project project = db.findProject(projectName);
        this.projectOwner  = project.getPoster();
        projectOwner.addReceivedRequest(this);
        sender.addSentRequest(this);
    }

    public String getMessage() {
        return message;
    }

    public String checkStatus() {
        return status;
    }

    public void rejectRequest() {
        this.status = "rejected";
    }

    public void acceptRequest() {
        this.status = "accepted";
        this.sender.addProject(projectName);
    }

    public String toString() {
        return "" + sender + " " + status;
    }
}
