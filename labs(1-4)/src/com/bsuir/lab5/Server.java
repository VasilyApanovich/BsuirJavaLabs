package com.bsuir.lab5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Created by v.apanovich on 04.03.2016.
 */

public class Server {

    final Map<Socket,DataOutputStream> socketOutputStream = new HashMap<>();
    final Map<String, Socket> userSocket = new HashMap<>();

    public Server(int port_number) throws IOException{
        startServer(port_number);
    }

    public static void main(String[] args) throws IOException {

        int port_number = 23;
        new Server(port_number);
    }

    private void startServer(int port_number) throws IOException{

        ServerSocket serverSocket = new ServerSocket(port_number);

        System.out.println("Server is ready!");

        while(true){

            Socket socket = serverSocket.accept();
            String clientName = new DataInputStream(socket.getInputStream()).readUTF();
            System.out.println("User connected: " + clientName);
            userSocket.put(clientName, socket);
            socketOutputStream.put(socket, new DataOutputStream(socket.getOutputStream()));
            sendConnectionNotification(clientName);
            new ServerThread(socket,this).start();
        }

    }

    private void sendConnectionNotification(String userName) throws IOException{

        Set<Socket> socketList = socketOutputStream.keySet();
        Iterator<Socket> itr = socketList.iterator();
        DataOutputStream outputStream;
        Socket socket;

        while(itr.hasNext()){
            socket = itr.next();
            String str = userName + " connected..." ;
            outputStream = socketOutputStream.get(socket);
            outputStream.writeUTF(str);
            outputStream.writeUTF("*" + userSocket.keySet().toString());
        }
    }

    public void sendToUser(String userName, String message) throws IOException {

        synchronized(socketOutputStream){
            Set<Socket> socketSet = socketOutputStream.keySet();
            Socket toSend = null;
            for (Socket s : socketSet) {
                if (userSocket.get(userName) == s) {
                    toSend = s;
                    break;
                }
            }
            if (toSend != null) {
                DataOutputStream outputStream = socketOutputStream.get(toSend);
                outputStream.writeUTF(message);
            }
        }
    }

    public void breakConnection(Socket s) throws IOException{

        synchronized(socketOutputStream){
            String userName = getKeyByValue(userSocket, s);
            DataOutputStream output = socketOutputStream.get(s);
            socketOutputStream.remove(s);
            userSocket.remove(userName);

            output.writeUTF("**quit**");
            output.close();
            s.close();
            System.out.println("User disconnected: " + userName);

            Set<Socket> socketSet = socketOutputStream.keySet();
            Iterator<Socket> itr = socketSet.iterator();
            DataOutputStream outputStream;
            Socket socket;

            while(itr.hasNext()){
                socket = itr.next();
                String str = userName + " disconnected..." ;
                outputStream = socketOutputStream.get(socket);
                outputStream.writeUTF(str);
                outputStream.writeUTF("*" + userSocket.keySet().toString());
            }
        }
    }

    private static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}


