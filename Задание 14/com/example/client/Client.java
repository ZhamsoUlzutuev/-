package com.example.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 9999;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream())) {

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter your name: ");
            String clientName = scanner.nextLine();

            System.out.print("Enter the path to the image: ");
            String filePath = scanner.nextLine();

            File imageFile = new File(filePath);
            if (!imageFile.exists()) {
                System.out.println("File does not exist.");
                return;
            }

            byte[] imageBytes = new byte[(int) imageFile.length()];
            try (FileInputStream fileIn = new FileInputStream(imageFile)) {
                fileIn.read(imageBytes);
            }

            out.writeInt(imageBytes.length);
            out.write(imageBytes);
            out.writeUTF(clientName);

            System.out.println("Image sent successfully.");

            // Create a new thread to listen for incoming messages
            new Thread(() -> {
                try {
                    while (true) {
                        int imageSize = in.readInt();
                        byte[] receivedImageBytes = new byte[imageSize];
                        in.readFully(receivedImageBytes);

                        String senderName = in.readUTF();
                        System.out.println("Received image from " + senderName);

                        try (FileOutputStream fileOut = new FileOutputStream("received_" + senderName + ".jpg")) {
                            fileOut.write(receivedImageBytes);
                        }

                        System.out.println("Image saved as received_" + senderName + ".jpg");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Keep the main thread alive
            while (true) {
                Thread.sleep(1000);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}