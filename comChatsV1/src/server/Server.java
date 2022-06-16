package server;

import muc.ThatsAppException;
import muc.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Server Klasse
 */


public class Server {

    public static User SRVUser;


    static {
        try {
            SRVUser = new User("SERVER");
        } catch (ThatsAppException e) {
            e.printStackTrace();
        }
    }

    public final static int PORT = 4999;
    public static HashMap<User, Socket> connectedUsers = new HashMap<>();
    public static HashMap<Socket, ObjectOutputStream> outputstreams = new HashMap<>();



    public static void main(String[] args) {

        UserCatalog.getInstance().restore();

        ServerSocket serverSocket;


        //final Scanner sc = new Scanner(System.in);


        try {
            serverSocket = new ServerSocket(PORT);


            System.out.println("Server runnning on " + PORT);

           /* System.out.print("User: ");
            User user = new User(sc.nextLine());
            System.out.print("Target: ");
            User target = new User(sc.nextLine());
            Chat chat = new Chat(new LinkedList<User>(Arrays.asList(user, target)));*/

            while (true) {


                Socket clientSocket = serverSocket.accept();


                outputstreams.put(clientSocket, new ObjectOutputStream(clientSocket.getOutputStream()));
                System.out.println("Connection accepted!");


                new ReceiverThread(clientSocket).start();

                /*


                Thread sender = new Thread(new Runnable() {
                    String msg;
                    @Override
                    public void run() {
                        while(true){
                            msg = sc.nextLine();
                            try {
                                out.writeObject(new Message(msg, chat, user));
                                out.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                sender.start();*/

/*
                Thread receiver = new Thread(new Runnable() {
                    Message msg;
                    @Override
                    public void run() {
                        try {
                            do {
                                Object o = in.readObject();

                                if(o instanceof Message) {
                                    msg = (Message) o;

                                    if(msg.getChat().equals(chat))
                                        System.out.println("Server: ID: "+msg.getSrc().getID() + " msg: "+msg);
                                }

                            }while(msg!=null);

                            System.out.println("Server out of service");
                            out.close();
                            clientSocket.close();
                            serverSocket.close();
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
                receiver.start();
            }
        } catch(IOException e){
            e.printStackTrace();
        }*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}