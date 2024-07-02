@FunctionalInterface
interface DiscriminantCalculator {
    double calculate(double a, double b, double c);
}

public class Main {
    public static void main(String[] args) {
        // Лямбда-выражение для вычисления дискриминанта
        DiscriminantCalculator discriminant = (a, b, c) -> b * b - 4 * a * c;

        // Пример использования
        double a = 5.0;
        double b = -3.0;
        double c = 2.0;
        double result = discriminant.calculate(a, b, c);

        System.out.println("Дискриминант: " + result);
    }
}