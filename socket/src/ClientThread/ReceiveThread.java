package ClientThread;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
public class ReceiveThread extends Thread{
    Socket socketOfClient = null;
    BufferedReader is = null;

    public ReceiveThread( Socket socketOfClient) {
        this.socketOfClient = socketOfClient;
    }

    @Override
    public void run() {
        try {
            // Luồng đầu vào tại Client (Nhận dữ liệu từ server).
            is = new BufferedReader(new InputStreamReader(this.socketOfClient.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " );
            return;
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " );
            return;
        }
        try {
            while (true) {
                // Đọc dữ liệu trả lời từ phía server
                // Bằng cách đọc luồng đầu vào của Socket tại Client.
                String responseLine=is.readLine() ;
                System.out.println("Server: " + responseLine);
            }
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }
}
