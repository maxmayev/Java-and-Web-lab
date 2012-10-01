package chat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;


public class ChatClientForm {
    final Socket socket;
    final BufferedReader socketReader;
    final BufferedWriter socketWriter;

    private String userName;

    private JTextPane outputTextPane;
    private JList userList;
    private JButton sendButton;
    private JButton exitButton;
    private JTextField inputTextField;
    private JPanel panel;

    private JFrame frame = new JFrame();

    public ChatClientForm(String host, int port) throws IOException {
        socket = new Socket(host, port);
        socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
        new Thread(new Receiver()).start();

        frame.add(panel);
        frame.setSize(500, 400);
        frame.setVisible(true);

        userName = JOptionPane.showInputDialog(frame, "Введите имя пользователя", "Ввод имени пользователя", 1);
        frame.setTitle("Клиент чата : " + userName);


        try {
            socketWriter.write(ChatCommand.LOGIN.getName() + userName + "\n");
            socketWriter.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }


    public void run() {

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    socketWriter.write(ChatCommand.MESSAGE.getName()+ " " +
                            userName + " " +
                            inputTextField.getText() + "\n");
                    socketWriter.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
    }


    public synchronized void close() {
        if (!socket.isClosed()) {
            try {
                socketWriter.write(ChatCommand.LOGOUT.getName() + userName + "\n");
                socketWriter.flush();
                socket.close();
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)  {
        try {
            new ChatClientForm("localhost", 45000).run();
        } catch (IOException e) {
            System.out.println("Не удалось подключится к серверу");
        }
    }


    private class Receiver implements Runnable{
        public void run() {
            while (!socket.isClosed()) {
                String line = null;
                try {
                    line = socketReader.readLine();
                } catch (IOException e) {
                    if ("Socket closed".equals(e.getMessage())) {
                        break;
                    }
                    System.out.println("Соединение потеряно");
                    close();
                }
                if (line != null) {
                    if (line.startsWith(ChatCommand.USER_LIST.getName())){
                        String toSplit = line.substring("userList:[".length(), line.length()-1);
                        String[] tokens = toSplit.split(", ");
                        userList.setListData(tokens);
                    }else if (line.startsWith(ChatCommand.MESSAGE.getName())){
                        outputTextPane.setText(outputTextPane.getText()+
                                line.substring(ChatCommand.MESSAGE.getName().length())+
                                "\n");
                    }
                } else {
                    System.out.println("Сервер закрыл соединение");
                    close();
                }
            }
        }
    }




}
