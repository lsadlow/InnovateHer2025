import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {
    private final DataInputStream dis;
    private final DataOutputStream dos;
    private final Socket s;
    private final ServerMethods serverMethods;

    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, ServerMethods serverMethods) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this.serverMethods = serverMethods;
    }

    @Override
    public void run() {
        String received;
        while (true) {
            try {
                // Send initial message to client
                dos.writeUTF("Connected to server. Enter your command:");
                dos.flush();

                // Receive input from client
                received = dis.readUTF();

                // Handle client disconnection
                if (received.equalsIgnoreCase("Exit")) {
                    System.out.println("Client " + this.s + " sent exit...");
                    System.out.println("Closing connection.");
                    this.s.close();
                    System.out.println("Connection closed.");
                    break;
                }

                // Process the client request using ServerMethods
                System.out.println(received);
                String response = serverMethods.serverFunctions(received);

                // Send the response back to the client
                System.out.println("Response: " + response);
                dos.writeUTF(response);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }

        try {
            // Close resources
            dis.close();
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
