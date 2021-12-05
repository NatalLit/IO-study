package fileManager;

import java.io.*;

import java.util.Arrays;

import static java.io.File.separator;

public class FileManager {
    //принимает путь к папке,возвращает количество файлов в папке и всех подпапках по пути
    public static int countFiles(String path) {
        int count = 0;
        File file = new File(path);
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files)
                if (f.isDirectory())
                    count += countFiles(f.getPath());
                else
                    count++;
        }
        return count;
    }

    //принимает путь к папке,возвращает количество папок в папке и всех подпапках по пути
    public static int countDirs(String path) {
        int count = 0;
        File file = new File(path);
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory())
                    count++;
                count += countDirs(f.getPath());
            }
        }
        return count;
    }

    // метод по копированию папок и файлов. Параметр from - путь к файлу или папке, параметр to - путь к папке куда будет производиться копирование.

    public static void copy(String from, String to) throws IOException {
        File sourceLocation = new File(from);
        File targetLocation = new File(to);

        if (!targetLocation.exists()) {
            targetLocation.mkdir();
        }

        File[] innerFiles = sourceLocation.listFiles();
        for (File file : innerFiles) {

            if (file.isDirectory()) {
                copy(file.getPath(), to + File.separator + file.getName());
            } else {

                File sourceFile = new File(file.getPath());
                File targetFile = new File(to + separator + file.getName());
                InputStream inputStream = new FileInputStream(sourceFile);
                OutputStream outputStream = new FileOutputStream(targetFile);

                int fileLength = (int) sourceFile.length();
                byte[] contentArray = new byte[fileLength];
                while ((fileLength = inputStream.read(contentArray)) > 0) {
                    outputStream.write(contentArray, 0, fileLength);
                }
                inputStream.close();
                outputStream.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        copy("/myDirectory/Hello", "/myDirectory/NewHello");
        move("/myDirectory/Hello","/myDirectory/RemoveHello");
    }


    // метод по перемещению папок и файлов.Параметр from - путь к файлу или папке, параметр to - путь к папке куда будет производиться копирование.
    public static void move(String from, String to) {
        new File(from).renameTo(new File(to));
    }

}

