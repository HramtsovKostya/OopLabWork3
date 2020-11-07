package lab;

import java.util.Random;
import java.util.function.BinaryOperator;

public class Matrix {
    private final int [][] matrix;
    private final int size;
    private int pointer;

    // Конструктор по умолчанию
    public Matrix() { this(10); }

    // Конструктор с параметрами
    public Matrix(int size) {
        this.size = size;
        pointer = 0;
        matrix = new int[size][size];
    }

    // Конструктор копирования
    public Matrix(Matrix other) {
        size = other.size;
        pointer = other.pointer;
        matrix = new int[size][size];

        for (int i = 0; i < size; i++)
            System.arraycopy(other.matrix[i], 0, matrix[i], 0, size);
    }

    // Получить размерность матрицы
    public int getSize() { return size; }

    public int [] getMatrix() {
        int [] tempArray = new int [size * size];

        for (int row = 0; row < size; row++)
            System.arraycopy(matrix[row], 0, tempArray, row * size, size);

        return tempArray;
    }

    // Добавление нового элемента
    public void add(int value) {
        if (pointer < size * size) {
            matrix[pointer / size][pointer % size] = value;
            pointer++;
        }
    }

    // Получение элемента по индексам
    public int elementAt(int row, int column) {
        if (row < 0 || row >= size || column < 0 || column >= size)
            throw new IndexOutOfBoundsException("Выход за границы массива!");

        return matrix[row][column];
    }

    // Заполнение матрицы случайними числами
    public void fillRandom(int bound) {
        Random random = new Random();

        for (int row = 0; row < size; row++)
            for (int column = 0; column < size; column++)
                matrix[row][column] = random.nextInt() % bound;
    }

    // Поиск макс. элемента матрицы
    public int maxElement() { return findElement(Math::max); }

    // Поиск мин. элемента матрицы
    public int minElement() { return findElement(Math::min); }

    private int findElement(BinaryOperator<Integer> operator) {
        int found = matrix[0][0];

        for (int [] array : matrix)
            for (int element : array)
                found = operator.apply(element, found);

        return found;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int row = 0; row < size; row++) {
            for (int element : matrix[row])
                builder.append(element).append("   ");

            if (row != size - 1) builder.append('\n');
        }
        return builder.toString();
    }
}