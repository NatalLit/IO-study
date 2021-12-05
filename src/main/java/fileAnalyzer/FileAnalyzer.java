package fileAnalyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileAnalyzer {

    private static final Pattern SENTENCE_PATTERN = Pattern.compile("((?<=[.?!]))");


    public static DataAnalytics analyze(String path, String word) throws IOException {
        String content = readContent(path);
        List<String> sentences = splitIntoSentences(content);
        List<String> filteredSentences = filter(sentences, word);
        int count = countWord(filteredSentences, word);

        return new DataAnalytics(filteredSentences, count);
    }

    static String readContent(String path) throws IOException {//читаем строки из файла

        File pathToFile = new File(path);
        InputStream inputStream = new FileInputStream(pathToFile);

        int fileLength = (int) pathToFile.length();
        byte[] contentArray = new byte[fileLength];
        inputStream.read(contentArray);
        inputStream.close();
        return new String(contentArray);
    }

    static List<String> splitIntoSentences(String content) {//делим текст на предложения
        String[] sentences = SENTENCE_PATTERN.split(content);
        List<String> splitSentences = new ArrayList<>();
        for (String sentence : sentences) {
            splitSentences.add(sentence.replaceAll("^\\s+|\\s+$", ""));
        }

        return splitSentences;
    }

    static List<String> filter(List<String> sentences, String word){//ищем предложения с искомым словом, и возвращаем лист строк
        List<String> filteredSentences = new ArrayList<>();
        for (String sentence: sentences) {
            if(sentence.contains(word)){
                filteredSentences.add(sentence);
            }
        }
        return filteredSentences;
    }


    static int countWord(List<String>filteredSentences, String word){
        Pattern pattern = Pattern.compile("\\b" + word +"\\b");
        int count = 0;
        for (String filteredSentence: filteredSentences) {
            Matcher matcher = pattern.matcher(filteredSentence);
            if(matcher.find()){
                count++;
            }
        }
        return count;
    }

}
