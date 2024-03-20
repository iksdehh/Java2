package task5Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;

        String host = "localhost";

        int port = 2000;
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(-1);
        }

        try {
            BufferedReader ein = new BufferedReader(
                    new InputStreamReader(System.in));
            BufferedReader einSo = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter ausSo = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("> Eingabe:");
            String eingabe;
            while ((eingabe = ein.readLine()) != null && !eingabe.equals("quit")) {
                ausSo.println(eingabe);
                System.out.println("> Antwort vom Server:");
                System.out.println(einSo.readLine());
                System.out.println("> Eingabe:");


            }
            ein.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}