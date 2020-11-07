package io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class SaveLoader {
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private static boolean isSaving;

    public static boolean hasSaving() {
        return isSaving;
    }

    public static void saveString(String saved, String path) {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(path), UTF_8)) {
            writer.write(saved);
            isSaving = true;
        } catch (IOException exception) {
            System.out.println(exception.getLocalizedMessage());
            isSaving = false;
        }
    }

    public static String loadString(String path) {
        StringBuilder builder = new StringBuilder();
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(path), UTF_8)) {
            while (reader.ready()) builder.append((char) reader.read());
        } catch (IOException exception) {
            System.out.println(exception.getLocalizedMessage());
        } return builder.toString();
    }

    public static void saveInt(int [] saved, String path) {
        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(path))) {
            for (int element : saved) outputStream.writeInt(element);
            isSaving = true;
        } catch (IOException exception) {
            System.out.println(exception.getLocalizedMessage());
            isSaving = false;
        }
    }

    public static int [] loadInt(String path) {
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(path))) {
            int [] tempArray = new int [inputStream.available() / Integer.BYTES];
            for (int index = 0; index < tempArray.length; index++)
                tempArray[index] = inputStream.readInt();
            return tempArray;
        } catch (IOException exception) {
            System.out.println(exception.getLocalizedMessage());
        } return null;
    }
}