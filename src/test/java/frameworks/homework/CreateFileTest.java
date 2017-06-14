package frameworks.homework;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class CreateFileTest extends CreateFileFixture {

    @Test
    public void shouldCreateFile() throws IOException {
        File file = new File(path + "/test.txt");
        Assert.assertTrue(file.createNewFile(), "File was not successfully created");
    }

    @Test
    public void shouldCreateFileWithLongName() throws IOException {
        File file = new File(path + "/" + RandomStringUtils.randomAlphabetic(30) + ".txt");
        Assert.assertTrue(file.createNewFile(), "File with long name was successfully created");
    }

    @Test
    public void shouldCreateFileWithCyrillicName() throws IOException {
        File file = new File(path + "/тест.txt");
        Assert.assertTrue(file.createNewFile(), "File with cyrillic name was successfully created");
    }

    @Test
    public void shouldReturnFalseWhenFileExists() throws IOException {
        File file = new File(path + "/test.txt");
        file.createNewFile();
        Assert.assertFalse(file.createNewFile(), "File with duplicate name was created, but shouldn't be");
    }

    @Test
    public void shouldThrowIOException() {
        File file = new File("wrongpath/test.txt");
        boolean isCreated = true;
        try {
            file.createNewFile();
        } catch (IOException e) {
            isCreated = false;
        }
        Assert.assertFalse(isCreated, "The method should throw an exception when the path is wrong");
    }

}
