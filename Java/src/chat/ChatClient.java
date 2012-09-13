package chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

/**
 * Класс-клиент чат-сервера. Работает в консоли. Командой с консоли shutdown посылаем сервер в оффлайн
 */
public class ChatClient {
    final Socket s;  // это будет сокет для сервера
    final BufferedReader socketReader; // буферизированный читатель с сервера
    final BufferedWriter socketWriter; // буферизированный писатель на сервер
    final BufferedReader userInput; // буферизированный читатель пользовательского ввода с консоли
    private JTextField tf;
    private JTextPane jTextPane;
    /**
     * Конструктор объекта клиента
     * @param host - IP адрес или localhost или доменное имя
     * @param port - порт, на котором висит сервер
     * @throws java.io.IOException - если не смогли приконнектиться, кидается исключение, чтобы
     * предотвратить создание объекта
     */
    public ChatClient(String host, int port) throws IOException {
        s = new Socket(host, port); // создаем сокет
        // создаем читателя и писателя в сокет с дефолной кодировкой UTF-8
        socketReader = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
        socketWriter = new BufferedWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8"));
        // создаем читателя с консоли (от пользователя)
        userInput = new BufferedReader(new InputStreamReader(System.in));
        new Thread(new Receiver()).start();// создаем и запускаем нить асинхронного чтения из сокета
    }

    /**
     * метод, где происходит главный цикл чтения сообщений с консоли и отправки на сервер
     */
    public void run() {
        //Creating the Frame
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        //Creating the MenuBar and adding components
        jTextPane = new JTextPane();



        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        panel.setBackground(Color.blue);
        JLabel label = new JLabel("Enter Text");
        tf = new JTextField(10);// accepts upto 10 characters
        JButton send = new JButton("Send");
        JButton exit = new JButton("Exit");
        panel.add(label);// Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(exit);
        panel.add(jTextPane);
        // Text Area at the Center
        JTextArea ta = new JTextArea();
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH,panel);
        frame.getContentPane().add(BorderLayout.NORTH,jTextPane);
        frame.getContentPane().add(BorderLayout.CENTER,ta);
        frame.setVisible(true);

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    socketWriter.write(tf.getText()); //пишем строку пользователя
                    socketWriter.write("\n"); //добавляем "новою строку", дабы readLine() сервера сработал
                    socketWriter.flush(); // отправляем
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close(); // в любой ошибке - закрываем.
            }
        });
    }

    /**
     * метод закрывает коннект и выходит из
     * программы (это единственный  выход прервать работу BufferedReader.readLine(), на ожидании пользователя)
     */
    public synchronized void close() {//метод синхронизирован, чтобы исключить двойное закрытие.
        if (!s.isClosed()) { // проверяем, что сокет не закрыт...
            try {
                s.close(); // закрываем...
                System.exit(0); // выходим!
            } catch (IOException ignored) {
                ignored.printStackTrace();
            }
        }
    }

    public static void main(String[] args)  {
        try {
            new ChatClient("localhost", 45000).run();
        } catch (IOException e) {
            System.out.println("Unable to connect. Server not running?");
        }
    }

    /**
     * Вложенный приватный класс асинхронного чтения
     */
    private class Receiver implements Runnable{

        public void run() {
            //проверяем коннект
            while (!s.isClosed()) {
                String line = null;
                try {
                    // пробуем прочесть
                    line = socketReader.readLine();
                } catch (IOException e) {
                    if ("Socket closed".equals(e.getMessage())) {
                        break;
                    }
                    System.out.println("Connection lost");
                    // закрываем сокет
                    close();
                }
                if (line == null) {  // строка будет null если сервер прикрыл коннект по своей инициативе, сеть работает
                    System.out.println("Server has closed connection");
                    close(); // ...закрываемся
                } else { // иначе печатаем то, что прислал сервер.
                    jTextPane.setText(jTextPane.getText()+line+"\n");
                }
            }
        }
    }
}


