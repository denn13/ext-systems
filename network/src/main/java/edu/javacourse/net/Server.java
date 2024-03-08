package edu.javacourse.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket socket = new ServerSocket(25225,2000);

        System.out.println("Server started: ");

        while (true) {
            Socket client = socket.accept();
            new SimpleServer(client).start();
        }

    }

}

class SimpleServer extends Thread {
    private Socket client;

    SimpleServer(Socket client) {
        this.client = client;
    }


    @Override
    public void run() {
        handleRequest();
    }

    private void handleRequest() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            String request = br.readLine();
            String[] lines = request.split("\\s+");
            String command = lines[0];
            String userName = lines[1];
            System.out.println("Server got string 1:" + command);
            System.out.println("Server got string 2:" + userName);
//            Thread.sleep(2000);

            String response = buildResponse(command, userName);
            bw.write(response);
            bw.newLine();
            bw.flush();

            br.close();
            bw.close();

            client.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private String buildResponse(String command, String userName) {
        switch (command) {
            case "HELLO": return "Hello, " + userName;
            case "DAY": return "Good day, " + userName;
            case "MORNING": return "Good mornig, " + userName;
            case "EVENING": return "Good evening, " + userName;
            default: return "Hi, " + userName;
        }
    }
}
