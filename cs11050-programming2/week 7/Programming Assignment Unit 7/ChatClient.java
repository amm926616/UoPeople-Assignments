import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {
            System.out.println("Connected to the chat server.");

            // Thread to listen for incoming messages
            new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String message;
                    while ((message = reader.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }
            }).start();

            // Main thread for sending messages
            try (PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                 Scanner scanner = new Scanner(System.in)) {
                System.out.println("Start chatting! Type your message and press Enter to send. Type 'exit' to leave.");
                while (true) {
                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("exit")) {
                        writer.println(input);
                        break;
                    }
                    writer.println(input);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not connect to the server.");
        }
    }
}
