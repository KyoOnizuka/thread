package Server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class ListenThread extends Thread {
    public static  List<BufferedWriter> osList = new ArrayList<BufferedWriter>();
    public int port = 0;
    Socket listener =null;
    public ListenThread(Socket listener) {
        this.listener= listener;
    }


    public synchronized void addList(BufferedWriter os)
    {
        osList.add(os);
    }
    @Override
    public void run() {
        String line;
        BufferedReader is,is2;
        BufferedWriter os,os2;
        Socket socketOfServer = null;
        Socket socketOfServer2 = null;
        Scanner sc = new Scanner(System.in);





        try {



            is = new BufferedReader(new InputStreamReader(this.listener.getInputStream()));
            os = new BufferedWriter(new OutputStreamWriter(this.listener.getOutputStream()));
            addList(os);
            // Mở luồng vào ra trên Socket tại Server.
            //is2 = new BufferedReader(new InputStreamReader(socketOfServer2.getInputStream()));
            //os2 = new BufferedWriter(new OutputStreamWriter(socketOfServer2.getOutputStream()));


            // Nhận được dữ liệu từ người dùng và gửi lại trả lời.
            while (true) {

                // Đọc dữ liệu tới server (Do client gửi tới).
                line = is.readLine();
                if (line.equals("QUIT")) os.write(">> PP");
                    // Ghi vào luồng đầu ra của Socket tại Server.
                    // (Nghĩa là gửi tới Client).
                else {
                    for (BufferedWriter osinList:osList
                         ) {
                        osinList.write(">> " + line);
                        // Kết thúc dòng
                        osinList.newLine();
                        // Đẩy dữ liệu đi
                        osinList.flush();}
                    }


            }

        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        System.out.println("Sever stopped!");
    }


}

