package lab;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
    private String [] sentence;
    private int count;
    private int capacity;

    // Конструктор по умолчанию
    public Sentence() { this(10); }

    // Конструктор с параметрами
    public Sentence(int capacity) {
        if (capacity < 0 || capacity > 1000)
            throw new IllegalArgumentException("Размер массива не может быть отрицательным!");

        this.capacity = capacity;
        sentence = new String[capacity];
        count = 0;
    }

    // Конструктор с параметрами
    public Sentence(String str, String splitter) {
        StringBuilder builder = new StringBuilder(str);

        for (String deleted : new String [] { "  ", " ,", ",," })
            deleteChar(builder, deleted);

        String[] tmpSentence = builder.toString().split(splitter);

        count = tmpSentence.length;
        capacity = count * 2;
        sentence = new String[capacity];

        System.arraycopy(tmpSentence, 0, this.sentence, 0, count);
    }
    
    // Конструктор копирования
    public Sentence(Sentence other) {
        sentence = new String [other.count];
        this.count = other.count;
        this.capacity = other.capacity;

        System.arraycopy(other.sentence, 0, this.sentence, 0, other.count);
    }

    // Получить длину массива
    public int getCount() { return count; }

    // Получить массив слов
    public String [] getSentence() {
        String [] tempSentence = new String[count];
        System.arraycopy(sentence, 0, tempSentence, 0, count);
        return tempSentence;
    }

    // Получить элемент по указанному индексу
    public String elementAt(int index) {
        if (index < 0 || index >= count)
            throw new IndexOutOfBoundsException("Выход за границы массива!");
        return sentence[index];
    }

    public void addElement(String word) {
        if (count >= capacity) {
            String [] tmpSentence = new String[capacity *= 2];
            System.arraycopy(sentence, 0, tmpSentence, 0, sentence.length);

            tmpSentence[count] = word;
            sentence = tmpSentence;
        } else sentence[count] = word;
        count++;
    }

    public boolean removeAt(int index) {
        if (index < 0 || index >= count) return false;

        String [] tmpSentence = new String[--count];
        System.arraycopy(sentence, 0, tmpSentence, 0, index);
        System.arraycopy(sentence, index + 1, tmpSentence, index, count - index);

        sentence = tmpSentence; return true;
    }

    // Удаление 1-го символа в указанной подстроке
    private void deleteChar(StringBuilder builder, String deleted) {
        int index = builder.indexOf(deleted);

        while (index != -1) {
            builder.deleteCharAt(index);
            index = builder.indexOf(deleted);
        }
    }

    // Форматирование строки
    public String format(String splitter) {
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < getCount() - 1; index++)
            builder.append(sentence[index]).append(splitter);
        builder.append(sentence[getCount() - 1]);

        return builder.toString();
    }

    // Переворачивание слова
    public void reverseAt(int number) {
        StringBuilder builder = new StringBuilder(sentence[number - 1]);
        sentence[number - 1] = builder.reverse().toString();
    }
    
    // Удаление каждого 3-го слова из предложения
    public void removeEachThirdWord() {
        List<String> list = new ArrayList<>();

        for (int index = 0; index < getCount(); index++)
            if ((index + 1) % 3 != 0) list.add(sentence[index]);

        sentence = list.toArray(new String[0]);
        count = sentence.length;
    }
}