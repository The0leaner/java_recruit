package cn.hinus.net;

import java.io.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.*;

/**
 * Created by hinus on 2017/1/16.
 */
public class Server {
    public static void main(String args[]) throws IOException{
        ServerSocket ss = new ServerSocket(8080);
        Socket conn = ss.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        String s = br.readLine();
        while (s != null) {
            System.out.println(s);
            bw.write(s.toUpperCase() + "\n");
            bw.flush();
            s = br.readLine();
        }

        br.close();
        bw.close();
        conn.close();
    }
}
