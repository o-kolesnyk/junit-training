package frameworks.homework;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class CreateFileTest extends CreateFileFixture {

    @Test(groups = "positive")
    public void shouldCreateFile() throws IOException {
        File file = new File(path + "/test.txt");
        file.createNewFile();
        Assert.assertTrue(file.exists(), "File was not successfully created \n");
    }

    @Test(groups = "positive")
    public void shouldCreateFileWithLongName() throws IOException {
        File file = new File(path + "/" + RandomStringUtils.randomAlphabetic(30) + ".txt");
        file.createNewFile();
        Assert.assertTrue(file.exists(), "File with long name was not created \n");
    }

    @Test(groups = "positive")
    public void shouldCreateFileWithCyrillicName() throws IOException {
        File file = new File(path + "/тест.txt");
        file.createNewFile();
        Assert.assertTrue(file.exists(), "File with cyrillic name was not created \n");
    }

    @Test(groups = "negative")
    public void shouldReturnFalseWhenFileExists() throws IOException {
        File file = new File(path + "/test.txt");
        file.createNewFile();
        Assert.assertFalse(file.createNewFile(), "File with duplicate name was created, but shouldn't be \n");
    }

    @Test(groups = "negative", expectedExceptions = IOException.class)
    public void shouldThrowIOException() throws IOException {
        File file = new File("wrongpath/test.txt");
        file.createNewFile();
    }

}
