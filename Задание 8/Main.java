/*В стихотворении найти одинаковые буквы, которые встречаются во всех
словах*/

public class Main {
    public static void main(String[] args) {
        String poem = "Привет ПРивет\n";

        String[] words = poem.split("\\s+");

        if (words.length == 0) {
            System.out.println("Стихотворение не содержит слов.");
            return;
        }

        int[] commonLetters = new int[Character.MAX_CODE_POINT + 1];
        for (char c : words[0].toCharArray()) {
            commonLetters[c]++;
        }

        for (int i = 1; i < words.length; i++) {
            int[] currentWordLetters = new int[Character.MAX_CODE_POINT + 1];
            for (char c : words[i].toCharArray()) {
                currentWordLetters[c]++;
            }
            for (int j = 0; j < commonLetters.length; j++) {
                if (commonLetters[j] > 0 && currentWordLetters[j] == 0) {
                    commonLetters[j] = 0;
                }
            }
        }

        System.out.print("Общие буквы во всех словах: ");
        boolean foundCommonLetters = false;
        for (int i = 0; i < commonLetters.length; i++) {
            if (commonLetters[i] > 0) {
                System.out.print((char) i + " ");
                foundCommonLetters = true;
            }
        }
        if (!foundCommonLetters) {
            System.out.print("Нет общих букв во всех словах.");
        }
    }
}