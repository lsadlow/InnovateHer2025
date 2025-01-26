import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        Database db = new Database(); // Initialize your database
        ServerMethods serverMethods = new ServerMethods(db);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Listening on port " + PORT);

            // Thread pool to handle multiple clients
            ExecutorService executorService = Executors.newFixedThreadPool(10);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket);

                // Create streams for client communication
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                // Create and start a new ClientHandler thread
                ClientHandler clientHandler = new ClientHandler(socket, dis, dos, serverMethods);
                executorService.execute(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
