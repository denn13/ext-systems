package edu.javacourse.net;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            SimpleClient sc = new SimpleClient(i);
            sc.start();
        }
    }
}

class SimpleClient extends Thread{
    private final int counter;

    public SimpleClient(int i) {
        this.counter = i;
    }

    @Override
    public void run() {
        try {
            System.out.println("Started: " + counter + " " + LocalDateTime.now());
            Socket socket = new Socket("127.0.0.1", 25225);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String s = "Denis " + counter ;

            bw.write(s);
            bw.newLine();
            bw.flush();

            String answer = br.readLine();
            System.out.println("Client got string: " + answer);

            br.close();
            bw.close();
            System.out.println("Finished: "  + counter + " "  + LocalDateTime.now());
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}