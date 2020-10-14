package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SimpleServer {

    public static void main(String args[]) {


/*Thread t1= new ListenThread(listener);
        t1.start();
        Thread t2= new ListenThread(listener);
        t2.start();*/
        Thread connect = new ConnectionThread();
        connect.start();


    }
}
