import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static int userIdCounter = 0;
    private static final Map<Integer, PrintWriter> clientWriters = new HashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Chat server started...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                userIdCounter++;
                int userId = userIdCounter;
                System.out.println("User " + userId + " connected.");

                new Thread(new ClientHandler(clientSocket, userId)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private final int userId;

        public ClientHandler(Socket clientSocket, int userId) {
            this.clientSocket = clientSocket;
            this.userId = userId;
        }

        @Override
        public void run() {
            try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                synchronized (clientWriters) {
                    clientWriters.put(userId, writer);
                }

                writer.println("Welcome to the chat server! You are User " + userId + ".");
                writer.println("Type your message and press Enter to send. Type 'exit' to leave the chat.");

                broadcastMessage("User " + userId + " has joined the chat!");

                String message;
                while ((message = reader.readLine()) != null) {
                    if (message.equalsIgnoreCase("exit")) {
                        break;
                    }
                    broadcastMessage("User " + userId + ": " + message);
                }

                writer.println("Goodbye, User " + userId + "!");
            } catch (IOException e) {
                System.out.println("User " + userId + " disconnected.");
            } finally {
                synchronized (clientWriters) {
                    clientWriters.remove(userId);
                }
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                broadcastMessage("User " + userId + " has left the chat.");
            }
        }

        private void broadcastMessage(String message) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters.values()) {
                    writer.println(message);
                }
            }
        }
    }
}
