/* В приведенных ниже заданиях необходимо вывести внизу фамилию разработчика, дату и время получения задания, а также дату и время сдачи задания.
Добавить комментарии в программы в виде "комментарий". В заданиях на числа объект можно создавать в виде массива символов.
    Ввести n чисел с консоли.
    1. Найти самое короткое и самое длинное число. Вывести найденные числа и их длину */

//Ульзутуев Жамсо

import java.util.Scanner;
import java.util.Date;

class NumberLengthFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество чисел:");
        int n = scanner.nextInt();
        scanner.nextLine();

        String[] numbers = new String[n];
        System.out.println("Введите числа:");
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextLine();
        }

        String shortestNumber = numbers[0];
        String longestNumber = numbers[0];

        for (int i = 1; i < n; i++) {
            if (numbers[i].length() < shortestNumber.length()) {
                shortestNumber = numbers[i];
            }
            if (numbers[i].length() > longestNumber.length()) {
                longestNumber = numbers[i];
            }
        }

        System.out.println("Самое короткое число: " + shortestNumber + ", длина: " + shortestNumber.length());
        System.out.println("Самое длинное число: " + longestNumber + ", длина: " + longestNumber.length());

        System.out.println("Разработчик: Ульзутуев");
        System.out.println("Дата и время получения задания: 2024-06-07 00:00");

        Date date = new Date();
        System.out.println("Дата и время сдачи задания: " + date);
    }
}