package frameworks.homework;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class CreateFileTest extends CreateFileFixture {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "generateNames", groups = "positive")
    public void shouldCreateFile(String fileName) throws IOException {
        File file = new File(path + fileName);
        file.createNewFile();
        Assert.assertTrue(file.exists(), "File was not successfully created \n");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "generateLongNames", groups = "positive")
    public void shouldCreateFileWithLongName(String fileName) throws IOException {
        File file = new File(path + fileName);
        file.createNewFile();
        Assert.assertTrue(file.exists(), "File with long name was not created \n");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "loadCyrillicNamesFromFile", groups = "positive")
    public void shouldCreateFileWithCyrillicName(String fileName) throws IOException {
        File file = new File(path + fileName);
        file.createNewFile();
        Assert.assertTrue(file.exists(), "File with cyrillic name was not created \n");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "generateNames", groups = "negative")
    public void shouldReturnFalseWhenFileExists(String fileName) throws IOException {
        File file = new File(path + fileName);
        file.createNewFile();
        Assert.assertFalse(file.createNewFile(), "File with duplicate name was created, but shouldn't be \n");
    }

    @Test(groups = "negative", expectedExceptions = IOException.class)
    public void shouldThrowIOException() throws IOException {
        File file = new File("wrongpath/test.txt");
        file.createNewFile();
    }

}
