import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    ArrayList<Client> clientsArray = new ArrayList<>();
    String chatName;
    ServerSocket server;

    public ChatServer(String chatName) {
        this.chatName = chatName;
        try {
            server = new ServerSocket(1234);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            System.out.println("Chat: " + chatName + " waiting new users");
            try {
                Socket socket = server.accept();
                System.out.println("Client connected");
                clientsArray.add(new Client(socket, this));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatServer("WhatsAPP").run();

    }

    void sendMessage(String message){
        for (Client cl:clientsArray) {
            cl.recieveMessage(message);
        }
    }
}
