package ClientThread;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import  java.util.Scanner;
public class SendThread extends Thread {
    public Socket socketOfClient = null;
    public BufferedWriter os = null;

    public SendThread(Socket socketOfClient) {
        this.socketOfClient = socketOfClient;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        String test = "";
        try {
            // Gửi yêu cầu kết nối tới Server đang lắng nghe
            // trên máy 'localhost' cổng 9999.

            // Tạo luồng đầu ra tại client (Gửi dữ liệu tới server)
            os = new BufferedWriter(new OutputStreamWriter(this.socketOfClient.getOutputStream()));

            // Luồng đầu vào tại Client (Nhận dữ liệu từ server).

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " );
            return;
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " );
            return;
        }

        try {
            while (true) {
                test = sc.nextLine();
                // Ghi dữ liệu vào luồng đầu ra của Socket tại Client.

                os.write(test);
                os.newLine();
                os.flush();


                if (test.equals("QUIT")) {
                    os.close();
                    socketOfClient.close();
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }
}
