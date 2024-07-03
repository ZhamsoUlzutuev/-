/*8. Клиент выбирает изображение из списка и пересылает его другому клиенту
через сервер.*/

package com.example.server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 9999;
    private static List<Socket> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server started on port " + PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            clients.add(clientSocket);
            new ClientHandler(clientSocket).start();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try (DataInputStream in = new DataInputStream(clientSocket.getInputStream())) {
                while (true) {
                    int imageSize = in.readInt();
                    byte[] imageBytes = new byte[imageSize];
                    in.readFully(imageBytes);

                    String clientName = in.readUTF();

                    System.out.println("Received image from " + clientName);

                    synchronized (clients) {
                        for (Socket socket : clients) {
                            if (socket != clientSocket) {
                                try (DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
                                    out.writeInt(imageSize);
                                    out.write(imageBytes);
                                    out.writeUTF(clientName);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clients.remove(clientSocket);
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
