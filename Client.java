package student.java.homework.weeks.week3_1;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in))) {

            String availableOps = in.readLine();
            System.out.println("Available operations: " + availableOps);

            System.out.print("Enter operation (op num1 num2): ");
            String userInput = userIn.readLine();
            out.println(userInput);

            String result = in.readLine();
            System.out.println(result);

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}

