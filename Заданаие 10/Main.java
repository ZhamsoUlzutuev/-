import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Проверяем, что параметры командной строки переданы правильно
        if (args.length < 3) {
            System.out.println("Usage: java Main <inputFilePath> <outputFilePath> <substring>");
            return;
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];
        String substringToRemove = args[2];

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Удаляем подстроку из текущей строки
                String modifiedLine = line.replace(substringToRemove, "");
                // Записываем измененную строку в выходной файл
                writer.write(modifiedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлами: " + e.getMessage());
        }
    }
}