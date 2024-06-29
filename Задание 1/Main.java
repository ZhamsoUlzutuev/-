import java.util.Scanner;

class PalindromeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество чисел:");
        int n = scanner.nextInt();

        int[] numbers = new int[n];

        System.out.println("Введите числа:");
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        System.out.println("Числа-палиндромы:");
        for (int number : numbers) {
            if (isPalindrome(number)) {
                System.out.println(number);
            }
        }
        scanner.close();
    }

    private static boolean isPalindrome(int number) {
        String str = Integer.toString(number);
        int len = str.length();
        for (int i = 0; i < len / 2; i++) {
            if (str.charAt(i) != str.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}