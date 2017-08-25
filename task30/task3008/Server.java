package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Администратор on 08.08.2017.
 */
public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message)
    {
        for(Map.Entry<String, Connection> entry:connectionMap.entrySet()) {
            try {
                entry.getValue().send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Отправка сообщения для "+ entry.getKey() + " не удалась.");
            }
        }
    }

    public static void main(String[] args)
    {
        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt())) {
            ConsoleHelper.writeMessage("Сервер запущен.");
            while (true) {
                Handler handler = new Handler(serverSocket.accept());
                handler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class Handler extends Thread
    {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException
        {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message answer = connection.receive();
                if (answer.getType() == MessageType.USER_NAME) {
                    if (!answer.getData().isEmpty()) {
                        if (!connectionMap.containsKey(answer.getData())) {
                            connectionMap.put(answer.getData(), connection);
                            connection.send(new Message(MessageType.NAME_ACCEPTED));
                            return answer.getData();
                        }
                    }
                }
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException
        {
            for(Map.Entry<String, Connection> entry:connectionMap.entrySet())
            {
                if(!entry.getKey().equals(userName))
                {
                    connection.send(new Message(MessageType.USER_ADDED, entry.getKey()));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException
        {
            while (true)
            {
                Message clientMessage = connection.receive();
                if(clientMessage.getType()==MessageType.TEXT)
                    sendBroadcastMessage(new Message(MessageType.TEXT, userName + ": " + clientMessage.getData()));
                else
                    ConsoleHelper.writeMessage(userName + ": Неверный тип сообщения");
            }
        }

        public void run()
        {
            SocketAddress remoteAddress = socket.getRemoteSocketAddress();
            ConsoleHelper.writeMessage("Установлено соединение с " + remoteAddress);

            String username = "";
            try (Connection connection = new Connection(socket)) {
                username = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, username));
                sendListOfUsers(connection, username);
                serverMainLoop(connection, username);
            } catch (IOException|ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Ошибка при обмене сообщениями с " + remoteAddress);
            }
            if(!username.isEmpty()&&connectionMap.containsKey(username))
            {
                connectionMap.remove(username);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, username));
            }

            ConsoleHelper.writeMessage("Закрыто соединение с " + remoteAddress);
        }
    }
}
