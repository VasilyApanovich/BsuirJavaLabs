package com.bsuir.lab5;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;


/**
 * Created by v.apanovich on 04.03.2016.
 */
public class ServerThread extends Thread {

    private Socket socket;
    private Server server;

    public ServerThread(Socket socket,Server srv){
        this.socket = socket;
        this.server = srv;
    }

    @Override
    public void run() {

        String message;
        DataInputStream dis;
        String userName;
        try {
            dis = new DataInputStream(socket.getInputStream());

            while(true){
                userName = dis.readUTF();
                if (userName.equals("**quit**")) {
                    server.breakConnection(socket);
                    break;
                }
                message = dis.readUTF();
                server.sendToUser(userName, message);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}