package student.java.homework.weeks.week3_1;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Connection from " + clientSocket.getRemoteSocketAddress());
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    String availableOps = "+, -, *, /";
                    out.println(availableOps);

                    String input = in.readLine();
                    String[] parts = input.split(" ");
                    if (parts.length != 3) {
                        out.println("Error: Invalid input format");
                        continue;
                    }

                    String op = parts[0];
                    double num1, num2;

                    try {
                        num1 = Double.parseDouble(parts[1]);
                        num2 = Double.parseDouble(parts[2]);
                    } catch (NumberFormatException e) {
                        out.println("Error: Invalid number format");
                        continue;
                    }

                    double result;
                    switch (op) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 == 0) {
                                out.println("Error: Division by zero");
                                continue;
                            }
                            result = num1 / num2;
                            break;
                        default:
                            out.println("Error: Invalid operation");
                            continue;
                    }
                    out.println("Result: " + result);
                } catch (IOException e) {
                    System.err.println("Client connection error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}
