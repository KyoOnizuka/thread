package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ConnectionThread extends Thread {
    public static int port1 = 9999;
    public static List<Socket> connectors = new ArrayList<Socket>();
    ServerSocket listener = null;

    public synchronized void addList2(Socket listener) {
    ConnectionThread.connectors.add(listener);
    }

    @Override
    public void run() {

        Socket socketOfServer = null;
        try {
            this.listener = new ServerSocket(9999);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
        while (true) {
            try {
                System.out.println("Server is waiting to accept user...");


                // Chấp nhận một yêu cầu kết nối từ phía Client.
                // Đồng thời nhận được một đối tượng Socket tại server.

                socketOfServer = this.listener.accept();
                System.out.println("Accept a client!");
                addList2(socketOfServer);

                    Thread test = new ListenThread(socketOfServer);
                    test.start();

                //Thread t = new ListenThread(socketOfServer);

            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }
}
