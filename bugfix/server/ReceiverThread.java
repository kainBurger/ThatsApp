package server;

import muc.Chat;
import muc.Message;
import muc.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ReceiverThread extends Thread {

    final private Socket clientSocket;
    private final ObjectInputStream in;
    private Message msg;

    public ReceiverThread(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.in = new ObjectInputStream(clientSocket.getInputStream());
    }

    @Override
    public void run() {
        try {

            while (clientSocket.isConnected()) {

                Object o = in.readObject();     //read Objects

                if (o instanceof Message) {
                    msg = (Message) o;

                    System.out.println("Server received from " + msg.getSrc().getName() + " msg: " + msg);

                    //check if it´s login msg
                    if (msg.getChat() == null) {
                        Server.connectedUsers.put(msg.getSrc(), clientSocket);
                        System.out.println("\nconnected Users: "+ Server.connectedUsers);
                    } else {
                        //start new SenderThread that sends msg to all connected clients
                        new SenderThread(msg, getSockets(msg.getChat())).start();
                    }


                }
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private List<Socket> getSockets(Chat chat) {

        List<Socket> sockets = new LinkedList<>();

        //Get all Sockets from Users in Chat
        System.out.println("connected User: "+ Server.connectedUsers);
        for (User u : chat.getUsers()) {
            System.out.println("user: "+u.toString());
            if (Server.connectedUsers.containsKey(u)) {
                sockets.add(Server.connectedUsers.get(u));
            }
        }

        return sockets;
    }

}
