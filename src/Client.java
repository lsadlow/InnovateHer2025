import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private Database clientDatabase;

    public User user ;

    public Client(String address, int port) {
        try {
            // Establish connection
            socket = new Socket(address, port);
            System.out.println("Connected to server");

            // Create input and output streams
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            user = null ;
            clientDatabase = new Database() ;
            clientDatabase.loadUsers();
            clientDatabase.loadProjects();

        } catch (IOException e) {
            e.printStackTrace();
        }

        new MainAuthPage(this) ;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public DataInputStream getDis() {
        return dis;
    }

    public void setDis(DataInputStream dis) {
        this.dis = dis;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("Enter a command (type 'Exit' to quit): ");
                String command = scanner.nextLine();

                if (command.equalsIgnoreCase("Exit")) {
                    dos.writeUTF("Exit");
                    System.out.println("Exiting client...");
                    break;
                }

                // Send command to server
                dos.writeUTF(command);
                dos.flush();

                // Read response from server
                String response = dis.readUTF();
                System.out.println("Server response: " + response);

                // Specific actions for different commands (optional):
                if (command.startsWith("signup")) {
                    // Handle signup-specific logic if needed
                } else if (command.startsWith("login")) {
                    // Handle login-specific logic if needed
                }
                // Add more cases as needed
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dis.close();
                dos.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public User getUser() {
        return user;
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 12345); // Replace with server IP and port
        client.start();
    }
}
