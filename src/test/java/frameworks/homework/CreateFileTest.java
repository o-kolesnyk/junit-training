package frameworks.homework;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;

import static frameworks.homework.DataSource.Type.RESOURCE;

@RunWith(DataProviderRunner.class)
public class CreateFileTest extends CreateFileFixture implements MyCategories {

    @Category(PositiveTests.class)
    @Test
    @UseDataProvider(value = "generateNames", location = DataProviders.class)
    public void shouldCreateFile(String fileName) throws IOException {
        File file = new File(path + fileName);
        boolean isCreated = file.createNewFile();
        boolean exists = file.exists();
        Assert.assertTrue("File was not successfully created \n", isCreated & exists);
    }

    @Category(PositiveTests.class)
    @Test
    @UseDataProvider(value = "generateLongNames", location = DataProviders.class)
    public void shouldCreateFileWithLongName(String fileName) throws IOException {
        File file = new File(path + fileName);
        boolean isCreated = file.createNewFile();
        boolean exists = file.exists();
        Assert.assertTrue("File with long name was not created \n", isCreated & exists);
    }

    @Category(PositiveTests.class)
    @Test
    @UseDataProvider(value = "loadCyrillicNamesFromFile", location = DataProviders.class)
    @DataSource(value = "/filenames.data", type = RESOURCE)
    public void shouldCreateFileWithCyrillicName(String fileName) throws IOException {
        File file = new File(path + fileName);
        boolean isCreated = file.createNewFile();
        boolean exists = file.exists();
        Assert.assertTrue("File with cyrillic name was not created \n", isCreated & exists);
    }

    @Category(NegativeTests.class)
    @Test
    @UseDataProvider(value = "generateNames", location = DataProviders.class)
    public void shouldReturnFalseWhenFileExists(String fileName) throws IOException {
        File file = new File(path + fileName);
        file.createNewFile();
        Assert.assertFalse("File with duplicate name was created, but shouldn't be \n", file.createNewFile());
    }

    @Category(NegativeTests.class)
    @Test(expected = IOException.class)
    public void shouldThrowIOException() throws IOException {
        File file = new File("wrongpath/test.txt");
        file.createNewFile();
    }
}
