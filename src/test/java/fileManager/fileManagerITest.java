package fileManager;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class fileManagerITest {
    String sourceLocation = "src/test/testData/testDirectory";
    String targetLocation = "src/test/testData/copyTestDirectory";
    String emptyDirectory = "src/test/testData/testDirectory/TEST/EmptyDirectory";

    @BeforeEach
    void create() throws IOException {
        File sourcePath = new File("src/test/testData/testDirectory");
        sourcePath.mkdir();
        File pathOne = new File("src/test/testData/testDirectory/fileOne.txt");
        pathOne.createNewFile();
        File pathTwo = new File("src/test/testData/testDirectory/fileTwo.txt");
        pathTwo.createNewFile();
        File pathThree = new File("src/test/testData/testDirectory/fileThree.txt");
        pathThree.createNewFile();
        File innerPath = new File("src/test/testData/testDirectory/TEST");
        innerPath.mkdir();
        File pathFour = new File("src/test/testData/testDirectory/TEST/fileFour.txt");
        pathFour.createNewFile();
        File pathFive = new File("src/test/testData/testDirectory/TEST/fileFive.txt");
        pathFive.createNewFile();
        File innerEmptyPath = new File("src/test/testData/testDirectory/TEST/EmptyDirectory");
        innerEmptyPath.mkdir();

    }

    @DisplayName("test countFiles return correct value of files")
    @Test
    void testCountFilesWorkCorrectly() {

        Assertions.assertEquals(5, FileManager.countFiles(sourceLocation));
    }

    @DisplayName("test countFiles return correct value on empty directory")
    @Test
    void testCountFilesWorkCorrectlyWithEmptyDirectory() {

        Assertions.assertEquals(0, FileManager.countFiles(emptyDirectory));
    }


    @DisplayName("test countDirs return correct value of directories")
    @Test
    void testCountDirsWorkCorrectly() {

        Assertions.assertEquals(2, FileManager.countDirs(sourceLocation));
    }

    @DisplayName("test countDirs return correct value on empty directory")
    @Test
    void testCountDirsWorkCorrectlyWithEmptyDirectory() {

        Assertions.assertEquals(0, FileManager.countDirs(emptyDirectory));
    }

    @DisplayName("test copy create new directory")
    @Test
    void testCopyCreateNewDirectory() throws IOException {
        String targetLocation = "src/test/testData/testCopyCreateNewDirectory";
        File targetFile = new File(targetLocation);
        Assertions.assertFalse(targetFile.exists());
        FileManager.copy(sourceLocation, targetLocation);
        Assertions.assertTrue(targetFile.exists());
        targetFile.delete();
    }

    @DisplayName("test copy work correctly")
    @Test
    void testCopy() throws IOException {
        String from = sourceLocation;
        String to = targetLocation;
        File fromFile = new File(from);
        File toFile = new File(to);
        File[] fromFileContent = fromFile.listFiles();
        File[] toFileContentBeforeCopy = toFile.listFiles();
        Assertions.assertNull(toFileContentBeforeCopy);
        FileManager.copy(from, to);
        File[] toFileContentAfterCopy = toFile.listFiles();

        Assertions.assertNotNull(toFileContentAfterCopy);
        Assertions.assertEquals(fromFileContent.length, toFileContentAfterCopy.length);
        Assertions.assertEquals(fromFileContent[0].getName(), toFileContentAfterCopy[0].getName());
        Assertions.assertEquals(fromFileContent[1].getName(), toFileContentAfterCopy[1].getName());
        Assertions.assertEquals(fromFileContent[2].getName(), toFileContentAfterCopy[2].getName());

    }

    @DisplayName("test move delete old directory ")
    @Test
    void testMoveWorkDeleteOldDirectory() {
        String directoryToMove = "src/test/testData/directoryToMove";
        File moveDirectory = new File(directoryToMove);
        moveDirectory.mkdir();
        String to = targetLocation;
        String directoryAfterMove = "src/test/testData/directoryAfterMove";



        Assertions.assertTrue(moveDirectory.exists());
        FileManager.move(directoryToMove, directoryAfterMove);
        Assertions.assertFalse(moveDirectory.exists());

    }


    @AfterEach
    void delete() throws IOException {
        File innerEmptyPath = new File("src/test/testData/testDirectory/TEST/EmptyDirectory");
        innerEmptyPath.delete();
        File pathFive = new File("src/test/testData/testDirectory/TEST/fileFive.txt");
        pathFive.delete();
        File pathFour = new File("src/test/testData/testDirectory/TEST/fileFour.txt");
        pathFour.delete();
        File innerPath = new File("src/test/testData/testDirectory/TEST");
        innerPath.delete();
        File pathThree = new File("src/test/testData/testDirectory/fileThree.txt");
        pathThree.delete();
        File pathTwo = new File("src/test/testData/testDirectory/fileTwo.txt");
        pathTwo.delete();
        File pathOne = new File("src/test/testData/testDirectory/fileOne.txt");
        pathOne.delete();
        File sourcePath = new File("src/test/testData/testDirectory");
        sourcePath.delete();


    }
}

