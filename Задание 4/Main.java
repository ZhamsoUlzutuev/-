/*Создать объект класса Текст, используя классы Предложение, Слово.
Методы: дополнить текст, вывести на консоль текст, заголовок текста.*/

import java.util.Arrays;
import java.util.Objects;

class Word {
    private String value;

    public Word(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(value, word.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}

class Sentence {
    private Word[] words;
    private int wordCount;

    public Sentence(int size) {
        this.words = new Word[size];
        this.wordCount = 0;
    }

    public void addWord(Word word) {
        if (wordCount == words.length) {
            words = Arrays.copyOf(words, words.length * 2);
        }
        words[wordCount++] = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return wordCount == sentence.wordCount && Arrays.equals(words, sentence.words);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(wordCount);
        result = 31 * result + Arrays.hashCode(words);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordCount; i++) {
            sb.append(words[i].toString()).append(" ");
        }
        return sb.toString().trim() + ".";
    }
}

class Text {
    private String title;
    private Sentence[] sentences;
    private int sentenceCount;

    public Text(String title, int size) {
        this.title = title;
        this.sentences = new Sentence[size];
        this.sentenceCount = 0;
    }

    public void addSentence(Sentence sentence) {
        if (sentenceCount == sentences.length) {
            sentences = Arrays.copyOf(sentences, sentences.length * 2);
        }
        sentences[sentenceCount++] = sentence;
    }

    public void printText() {
        System.out.println(title);
        for (int i = 0; i < sentenceCount; i++) {
            System.out.println(sentences[i]);
        }
    }

    public void printTitle() {
        System.out.println(title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text = (Text) o;
        return sentenceCount == text.sentenceCount &&
                Objects.equals(title, text.title) &&
                Arrays.equals(sentences, text.sentences);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(title, sentenceCount);
        result = 31 * result + Arrays.hashCode(sentences);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(title).append("\n");
        for (int i = 0; i < sentenceCount; i++) {
            sb.append(sentences[i]).append("\n");
        }
        return sb.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        Word word1 = new Word("Привет");
        Word word2 = new Word("Мир");

        Sentence sentence1 = new Sentence(2);
        sentence1.addWord(word1);
        sentence1.addWord(word2);

        Text text = new Text("Добавленное приложение", 2);
        text.addSentence(sentence1);
        text.printText();
        text.printTitle();
    }
}
