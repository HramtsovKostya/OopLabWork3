import java.util.Scanner;
import lab.Matrix;
import lab.Sentence;
import io.InputController;
import io.SaveLoader;

public class ConsoleMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static Matrix matrix;
    private static Sentence sentence;

    public static void start() {
        System.out.println("Добро пожаловать в программу!");
        int choice;

        do {
            System.out.println("\nВыберите одно из доступных действий:");
            System.out.println("\t1 - Ввести квадратную матрицу;");
            System.out.println("\t2 - Ввести предложение (набор слов);");
            System.out.println("\t0 - Завершить выполнение программы.");

            System.out.print("Выбор действия >> ");
            choice = InputController.input(0, 2);

            switch (choice) {
                case 1:
                    createNewMatrix();
                    executeActionsOnMatrix();
                    break;
                case 2:
                    createNewSentence();
                    executeActionsOnSentence();
                    break;
            }
        } while (choice != 0);
        System.out.println("Завершение работы программы...");
    }

    private static void createNewMatrix() {
        System.out.println("\nВыберите способ ввода матрицы:");
        System.out.println("\t1 - Ручной ввод элементов матрицы;");
        System.out.println("\t2 - Заполнение матрицы случайными числами;");
        System.out.println("\t3 - Чтение квадратной матрицы из файла.");
        System.out.print("Выбор действия >> ");

        switch (InputController.input(1, 3)) {
            case 1:
                System.out.print("Введите размерность матрицы: ");
                matrix = new Matrix(InputController.input(1, 10));

                System.out.println("Введите элементы матрицы:");
                for (int row = 0; row < matrix.getSize(); row++) {
                    for (int column = 0; column < matrix.getSize(); column++) {
                        System.out.print("Элемент[" + (row + 1) + "," + (column + 1) + "] = " );
                        matrix.add(InputController.input(-1000, 1000));
                    }
                } saveIntToFile(matrix.getMatrix());
                break;
            case 2:
                System.out.print("Введите размерность матрицы: ");
                matrix = new Matrix(InputController.input(1, 10));
                matrix.fillRandom(1000);
                saveIntToFile(matrix.getMatrix());
                break;
            case 3:
                System.out.print("\nВведите название файла: ");
                int [] tempArray = SaveLoader.loadInt("save\\" + scanner.nextLine());

                if (tempArray != null) {
                    matrix = new Matrix((int) Math.sqrt(tempArray.length));
                    for (int element : tempArray) matrix.add(element);
                    System.out.println("Файл успешно загружен!");
                } else createNewMatrix();
                break;
        } System.out.println("\nКвадратная матрица:\n" + matrix);
    }

    private static void createNewSentence() {
        System.out.println("\nВыберите способ ввода строки:");
        System.out.println("\t1 - Ручной ввод слов через запятую;");
        System.out.println("\t2 - Ручной ввод преложения (слова через пробел);");
        System.out.println("\t3 - Чтение строки слов из файла.");
        System.out.print("Выбор действия >> ");

        switch (InputController.input(1, 3)) {
            case 1:
                System.out.println("\nВведите слова через запятую:");
                sentence = new Sentence(scanner.nextLine(), ", ");
                saveStringToFile(sentence.format(" "));
                break;
            case 2:
                System.out.println("\nВведите предложение (слова через пробел):");
                sentence = new Sentence(scanner.nextLine(), " ");
                saveStringToFile(sentence.format(" "));
                break;
            case 3:
                System.out.print("Введите название файла: ");
                String tempString = SaveLoader.loadString("save\\" + scanner.nextLine());

                if (!tempString.isEmpty()) {
                    sentence = new Sentence(tempString, " ");
                    System.out.println("Загруженная строка:\n" + sentence.format(" "));
                    System.out.println("Файл успешно загружен!");
                } else createNewSentence();
                break;
        }
    }

    private static void executeActionsOnMatrix() {
        System.out.println("\nВыберите действие над матрицей:");
        System.out.println("\t1 - Найти мин. элемент в матрице;");
        System.out.println("\t2 - Найти макс. элемент в матрице.");
        System.out.print("Выбор действия >> ");

        if (InputController.input(1, 2) == 1)
            System.out.println("Мин. элемент матрицы = " + matrix.minElement());
        else System.out.println("Макс. элемент матрицы = " + matrix.maxElement());
    }

    private static void executeActionsOnSentence() {
        System.out.println("\nВыберите действие над предложением:");
        System.out.println("\t1 - Перевернуть указанное слово;");
        System.out.println("\t2 - Удалить каждое 3-е слово в предложении.");
        System.out.print("Выбор действия >> ");

        switch (InputController.input(1, 2)) {
            case 1:
                System.out.print("\nВведите номер переворачиваемого слова: ");
                sentence.reverseAt(InputController.input(1, sentence.getCount()));
                System.out.println("\nСтрока с перевернутым словом:");
                System.out.println(sentence.format(", "));
                break;
            case 2:
                sentence.removeEachThirdWord();
                System.out.println("\nПредложение с удаленным каждым 3-им словом:");
                System.out.println(sentence.format(" "));
                break;
        }
    }

    private static void saveStringToFile(String sentence) {
        System.out.println("\nСохранить данные в файл?\n 1 - Да, 2 - Нет");
        System.out.print("Выбор действия >> ");

        if (InputController.input(1, 2) == 1) {
            System.out.print("Введите название файла: ");
            SaveLoader.saveString(sentence, "save\\" + scanner.nextLine());

            if (SaveLoader.hasSaving()) System.out.println("Файл успешно сохранен!");
                else System.out.println("Ошибка сохранения!");
        }
    }

    private static void saveIntToFile(int [] array) {
        System.out.println("\nСохранить данные в файл?\n 1 - Да, 2 - Нет");
        System.out.print("Выбор действия >> ");

        if (InputController.input(1, 2) == 1) {
            System.out.print("Введите название файла: ");
            SaveLoader.saveInt(array, "save\\" + scanner.nextLine());

            if (SaveLoader.hasSaving()) System.out.println("Файл успешно сохранен!");
                else System.out.println("Ошибка сохранения!");
        }
    }
}