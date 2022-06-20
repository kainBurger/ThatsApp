package client;

import muc.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;


public class ClientSenderThread extends Thread {

    private final Message msg;
    private final ObjectOutputStream out;

    public ClientSenderThread(Message msg, ObjectOutputStream out) throws IOException {
        this.msg = msg;
        this.out = out;
    }

    @Override
    public void run() {

        try {
            out.writeObject(msg);
            out.flush();
            System.out.println("Sent Message to Server");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
