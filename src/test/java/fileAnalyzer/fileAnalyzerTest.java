package fileAnalyzer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class fileAnalyzerTest {

    @DisplayName("Test splitIntoSentences with correct delimiter")
    @Test
    void testSplitIntoSentencesWithCorrectDelimiter() {
        String content = "test.Is this the text for the test? Yes, this is a test text!";
        List<String> expected = List.of("test.", "Is this the text for the test?", "Yes, this is a test text!");

        List<String> actual = FileAnalyzer.splitIntoSentences(content);

        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("Test splitIntoSentences with invalid delimiter")
    @Test
    void testSplitIntoSentencesWithInvalidDelimiter() {
        String content = "test;Is this the text for the test, Yes, this is a test text)";
        List<String> expected = List.of(content);

        List<String> actual = FileAnalyzer.splitIntoSentences(content);

        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("Test filter when all sentences contain the search word")
    @Test
    void testFilterWhenAllSentencesContainSearchWord() {
        List<String> splitSentences = List.of("test.", "Is this the text for the test?", "Yes, this is a test text!");
        String word = "test";

        List<String> actual = FileAnalyzer.filter(splitSentences, word);

        Assertions.assertEquals(splitSentences, actual);
    }

    @DisplayName("Test filter with some sentences without the search word")
    @Test
    void testFilterWithSentencesWithoutSearchWord() {
        List<String> splitSentences = List.of("test.", "Is this the text for the test?", "Yes, this is a test text!");
        List<String> expected = List.of("Is this the text for the test?", "Yes, this is a test text!");
        String word = "text";

        List<String> actual = FileAnalyzer.filter(splitSentences, word);

        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("Test filter when there is no search word in the sentences")
    @Test
    void testFilterWithoutSearchWord() {
        List<String> splitSentences = List.of("test.", "Is this the text for the test?", "Yes, this is a test text!");
        List<String> expected = List.of();
        String word = "hello";

        List<String> actual = FileAnalyzer.filter(splitSentences, word);

        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("Test countWord with one matching word ")
    @Test
    void testCountWordWithOneMatchingWord() {
        List<String> filteredSentences = List.of("test.", "Is this the text for the test?", "Yes, this is a test text!");
       int expected = 1;
        String word = "Yes";

       int actual = FileAnalyzer.countWord(filteredSentences, word);

        Assertions.assertEquals(expected, actual);
    }


    @DisplayName("Test countWord with none matching words ")
    @Test
    void testCountWordWitNoneMatchingWords() {
        List<String> filteredSentences = List.of("test.", "Is this the text for the test?", "Yes, this is a test text!");
        int expected = 0;
        String word = "hello";

        int actual = FileAnalyzer.countWord(filteredSentences, word);

        Assertions.assertEquals(expected, actual);
    }


    @DisplayName("Test countWord with few matching words ")
    @Test
    void testCountWordWithFewMatchingWords() {
        List<String> filteredSentences = List.of("test.", "Is this the text for the test?", "Yes, this is a test text!");
        int expected = 2;
        String word = "text";

        int actual = FileAnalyzer.countWord(filteredSentences, word);

        Assertions.assertEquals(expected, actual);
    }

}
