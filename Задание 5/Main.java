/*Создать класс Фильм с внутренним классом, с помощью объектов которого можно хранить информацию о продолжительности, жанре и режиссерах
фильма*/

import java.util.Arrays;
import java.util.Objects;

class Film {
    private String title;
    private FilmDetails details;

    public Film(String title, int duration, String genre, String[] directors) {
        this.title = title;
        this.details = new FilmDetails(duration, genre, directors);
    }

    class FilmDetails {
        private int duration;
        private String genre;
        private String[] directors;

        public FilmDetails(int duration, String genre, String[] directors) {
            this.duration = duration;
            this.genre = genre;
            this.directors = new String[directors.length];
            System.arraycopy(directors, 0, this.directors, 0, directors.length);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FilmDetails that = (FilmDetails) o;
            return duration == that.duration &&
                    Objects.equals(genre, that.genre) &&
                    Arrays.equals(directors, that.directors);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(duration, genre);
            result = 31 * result + Arrays.hashCode(directors);
            return result;
        }

        @Override
        public String toString() {
            return "Продолжительность: " + duration + " minutes, Genre: " + genre + ", Directors: " + Arrays.toString(directors);
        }
    }

    public FilmDetails getDetails() {
        return details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(title, film.title) &&
                Objects.equals(details, film.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, details);
    }

    @Override
    public String toString() {
        return "Название: " + title + ", " + details.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        String[] directors = {"Стивен Спилберг", "Дэвид Финчер"};
        Film film = new Film("Список Шиндлера", 195, "Биография", directors);

        System.out.println(film);

        Film.FilmDetails details = film.getDetails();
        System.out.println("Film Details: " + details);
    }
}