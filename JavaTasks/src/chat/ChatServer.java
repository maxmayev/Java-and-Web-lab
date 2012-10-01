package chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public enum ChatCommand {

    LOGIN("login:"),
    LOGOUT("logOut:"),
    MESSAGE("message:"),
    USER_LIST("userList:"),
    SHUTDOWN("shutdown");

    ChatCommand(String name){
        this.name = name;
    }

    private String name;

    public String getName(){
        return name;
    }

}


public class ChatServer {
    private ServerSocket serverSocket;
    private Thread serverThread;
    private int port;

    List<String> activeUserList = new ArrayList<String>();

    BlockingQueue<SocketProcessor> queue = new LinkedBlockingQueue<SocketProcessor>();

    public ChatServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        this.port = port;
    }

    void run() {
        serverThread = Thread.currentThread();
        while (true) {
            Socket socket = null;

            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                shutdownServer();
            }

            if (serverThread.isInterrupted()){
                break;
            } else if (socket != null){
                try {
                    final SocketProcessor processor = new SocketProcessor(socket);
                    final Thread thread = new Thread(processor);
                    thread.setDaemon(true);
                    thread.start();
                    queue.offer(processor);
                }
                catch (IOException ignored) {

                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        new ChatServer(45000).run();
    }


    private class SocketProcessor implements Runnable{
        Socket socket;
        BufferedReader br;
        BufferedWriter bw;

        SocketProcessor(Socket s) throws IOException {
            socket = s;
            br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8") );
        }

        public void run() {
            while (!socket.isClosed()) {
                String line = null;
                try {
                    line = br.readLine();
                } catch (IOException e) {
                    close();
                }

                if (line == null) {
                    close();
                } else if (line.equals(ChatCommand.SHUTDOWN.getName())) {
                    serverThread.interrupt();
                    try {
                        new Socket("localhost", port);
                    } catch (IOException e) {
                        System.out.println(e);
                    } finally {

                        shutdownServer();
                    }

                } else if (line.startsWith(ChatCommand.LOGIN.getName())){
                    activeUserList.add(line.substring(ChatCommand.LOGIN.getName().length()));

                    String newUserList = ChatCommand.USER_LIST.getName() +
                            Arrays.toString(activeUserList.toArray());

                    for (SocketProcessor sp : queue){
                        sp.send(newUserList);
                    }

                } else if (line.startsWith(ChatCommand.LOGOUT.getName())){
                    activeUserList.remove(line.substring(ChatCommand.LOGOUT.getName().length()));

                    String newUserList = ChatCommand.USER_LIST.getName() + Arrays.toString(activeUserList.toArray());

                    for (SocketProcessor sp : queue){
                        sp.send(newUserList);
                    }
                } else if (line.startsWith(ChatCommand.MESSAGE.getName())) {

//                    String stringToSend = line.substring(ChatCommand.MESSAGE.getName().length());
                    for (SocketProcessor sp: queue) {
                        sp.send(line);
                    }
                }
            }
        }


        public synchronized void send(String line) {
            try {
                bw.write(line + "\n");
                bw.flush();
            } catch (IOException e) {
                close();
            }
        }

        public synchronized void close() {
            queue.remove(this);
            if (!socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            close();
        }
    }
}

private synchronized void shutdownServer() {
        for (SocketProcessor s: queue) {
            s.close();
        }
        if (!serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException ignored) {}
        }
    }