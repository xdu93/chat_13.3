import java.io.*;
import java.net.*;
import java.util.*;
class Client implements Runnable {
    Socket socket;
    // создаем удобные средства ввода и вывода
    Scanner in;
    PrintStream out;
    ChatServer server;
    int count;

    public Client(Socket socket, ChatServer server){
        this.socket = socket;
        new Thread(this).start();
        this.server = server;
        this.count = server.clientsArray.size() + 1;

    }

    public void recieveMessage(String message){
        out.println(message);
    }

    public void run() {
        try {
            // получаем потоки ввода и вывода
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            // создаем удобные средства ввода и вывода
            in = new Scanner(is);
            out = new PrintStream(os);

            // читаем из сети и пишем в сеть
            out.println("Welcome to new Chat!");
            String input = in.nextLine();
            while (!input.equals("end")) {
                server.sendMessage("User_" + count + " send message: " + input);
                input = in.nextLine();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}