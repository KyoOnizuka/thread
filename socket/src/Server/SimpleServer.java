package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SimpleServer {
    public static int port1 = 9999;
    public static int port2= 3000;
    public static void main(String args[]) {
        ServerSocket listener = null;
        try {
            listener = new ServerSocket(port1);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }

Thread t1= new ListenThread(listener);
        t1.start();
        Thread t2= new ListenThread(listener);
        t2.start();
    }
}
