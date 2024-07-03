/*В каждой строке найти и удалить заданную подстроку.*/

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String input_filename = "input.txt";
        String output_filename = "output.txt";
        String remove = "Java";
        try {
            FileReader file_read = new FileReader(new File(input_filename));
            BufferedReader reader = new BufferedReader(file_read);
            FileWriter file_write = new FileWriter(new File(output_filename));
            BufferedWriter writer = new BufferedWriter(file_write);
            String line;
            while ((line = reader.readLine()) != null) {
                String modified_line = line.replaceAll(remove, "");
                writer.write(modified_line + "\n");
            }
            reader.close();
            writer.close();
            System.out.println("Подстроки удалены.");
        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
