package io;
import java.util.Scanner;

public class InputController {
    public static int input(int leftLimit, int rightLimit) {
        int value = leftLimit - 1;
        String error = "Некорректный ввод! Повторите ввод данных: ";

        do {
            Scanner scanner = new Scanner(System.in, "CP1251");

            if (!scanner.hasNextInt()) System.out.print(error);
            else {
                value = scanner.nextInt();

                if (value < leftLimit || value > rightLimit)
                    System.out.print(error);
            }
        } while (value < leftLimit || value > rightLimit);

        return value;
    }
}