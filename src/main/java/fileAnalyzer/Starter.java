package fileAnalyzer;

import java.io.IOException;

import static fileAnalyzer.DataAnalytics.print;

public class Starter {
    public static void main(String[] args) throws IOException {

        System.out.println("Please enter the file path:");  //C:\\java_apps\\LuxCampus\\IOStudy\\src\\test\\resources\\testFile.txt

        String path = "";
        char charPath;
        while ((charPath = (char) System.in.read()) != '\n') {
            path += charPath;
        }


        System.out.println("Please enter search word:"); //world

        String word = "";
        char charWord;
        while ((charWord = (char) System.in.read()) != '\n') {
            word += charWord;
        }

        FileAnalyzer fileAnalyzer = new FileAnalyzer();
        DataAnalytics dataAnalytics = fileAnalyzer.analyze(path, word);
        print(dataAnalytics);


    }
}
