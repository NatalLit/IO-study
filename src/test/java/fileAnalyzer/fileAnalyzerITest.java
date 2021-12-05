package fileAnalyzer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class fileAnalyzerITest {

    String path = "C:\\java_apps\\LuxCampus\\IOStudy\\src\\test\\resources\\testFile.txt";
    String word = "world";


    @DisplayName("test readContent work correctly")
    @Test
    void testReadContentReturnCorrectContent() throws IOException {
        String expected = "What a wonderful world.\r\n" +
                "I see trees of green.\r\n" +
                "Red roses too.\r\n" +
                "I see them bloom,\r\n" +
                "For me and you.\r\n" +
                "And I think to myself,\r\n" +
                "What a wonderful world!";

        String actual = FileAnalyzer.readContent(path);

        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("test readContent throws FileNotFoundException")
    @Test
    void testReadContentThrowsFileNotFoundException() {

        String content = "test\\resources\\testFile.txt";
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            FileAnalyzer.readContent(content);
        });
    }


    @DisplayName("test analyze method return correct words count")
    @Test
    void testAnalyzeReturnCorrectCount() throws IOException {
        DataAnalytics dataAnalytics = FileAnalyzer.analyze(path, word);


        Assertions.assertEquals(2, dataAnalytics.getCount());
    }


    @DisplayName("test analyze method return correct sentences")
    @Test
    void testAnalyzeReturnCorrectSentences() throws IOException {
        DataAnalytics dataAnalytics = FileAnalyzer.analyze(path, word);


        Assertions.assertEquals("What a wonderful world.", dataAnalytics.getSentences().get(0));
        Assertions.assertEquals("And I think to myself,\r\n" +
                "What a wonderful world!", dataAnalytics.getSentences().get(1));
    }

}
