package Java2.task5Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class KlausurenServer {

    public static void main(String[] args) {
        ServerSocket serverSo = null;
        //int port = Integer.valueOf(args[0]).intValue();
        int port = 2000;
        try {
            serverSo = new ServerSocket(port);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        File  Anfragen = new File("C:\\Users\\ReneW\\IdeaProjects\\Practise\\src\\Java2\\task5Test\\Anfragen.txt");
        System.out.println("Server lauscht auf Port " + port);
        int clientNr = 0;
        while (true) {
            try {
                Socket clientSo = serverSo.accept();
                clientNr++;
                System.out.println("Mit Client " + clientNr + " verbunden");
                KlausurenServerThread klausurenServerThread = new KlausurenServerThread(clientSo, clientNr, Anfragen);
                klausurenServerThread.start();


            } catch (IOException e ) {
                e.printStackTrace();
            } finally {
                try(BufferedWriter writer = new BufferedWriter(new PrintWriter("C:/Users/ReneW/IdeaProjects/Practise/src/Java2/task5Test/Anfragen.txt"))) {
                    writer.write("");
                    Anfragen.delete();

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
