package com.javarush.task.task30.task3008;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by Администратор on 08.08.2017.
 */
public class Connection implements Closeable {
    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;

        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(this.socket.getInputStream());
    }

    public void send(Message message) throws IOException
    {
        synchronized (out)
        {
            out.writeObject(message);
            out.flush();
        }
    }

    public Message receive() throws IOException, ClassNotFoundException
    {
        synchronized (in)
        {
            return (Message) in.readObject();
        }
    }

    public SocketAddress getRemoteSocketAddress()
    {
        return socket.getRemoteSocketAddress();
    }

    public void close() throws IOException
    {
        in.close();
        out.close();
        socket.close();
    }
}