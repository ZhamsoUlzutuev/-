/*16. Дана матрица из целых чисел. Найти в ней прямоугольную подматрицу,
состоящую из максимального количества одинаковых элементов. Использовать класс ArrayDeque.*/

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 3, 3},
                {1, 2, 3, 2, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };

        Result result = findMaxRectangle(matrix);
        System.out.println("Максимальный прямоугольник из одинаковых элементов:");
        System.out.println("Значение элемента: " + result.value);
        System.out.println("Начальная точка: (" + result.startRow + ", " + result.startCol + ")");
        System.out.println("Конечная точка: (" + result.endRow + ", " + result.endCol + ")");
        System.out.println("Размер: " + result.maxSize);
    }

    static class Result {
        int value;
        int startRow;
        int startCol;
        int endRow;
        int endCol;
        int maxSize;

        Result(int value, int startRow, int startCol, int endRow, int endCol, int maxSize) {
            this.value = value;
            this.startRow = startRow;
            this.startCol = startCol;
            this.endRow = endRow;
            this.endCol = endCol;
            this.maxSize = maxSize;
        }
    }

    public static Result findMaxRectangle(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new Result(0, 0, 0, 0, 0, 0);
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] height = new int[rows][cols];
        Result result = new Result(0, 0, 0, 0, 0, 0);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                height[i][j] = (i == 0 || matrix[i][j] != matrix[i - 1][j]) ? 1 : height[i - 1][j] + 1;
            }
        }

        for (int i = 0; i < rows; i++) {
            int[] h = new int[cols];
            for (int j = 0; j < cols; j++) {
                h[j] = height[i][j];
            }
            Result tempResult = maxRectangleInHistogram(h, matrix[i]);
            if (tempResult.maxSize > result.maxSize) {
                result = tempResult;
            }
        }

        return result;
    }

    private static Result maxRectangleInHistogram(int[] heights, int[] row) {
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        int value = 0;
        int start = 0;
        int end = 0;

        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i : i - 1 - stack.peek();
                if (h * w > maxArea) {
                    maxArea = h * w;
                    value = row[i - 1];
                    start = i - w;
                    end = i - 1;
                }
            }
            stack.push(i);
        }

        return new Result(value, 0, start, 0, end, maxArea);
    }
}
