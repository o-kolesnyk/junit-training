package frameworks.homework;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExternalResource;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RunWith(DataProviderRunner.class)
public class CreateFileTest extends CreateFileFixture implements MyCategories {

    @DataProvider
    public static Object[][] generateNames() {
        List<Object[]> names = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            names.add(new Object[]{
                    RandomStringUtils.randomAlphabetic(10) + ".txt"
            });
        }
        return names.toArray(new Object[][]{});
    }

    @DataProvider
    public static Object[][] generateLongNames() {
        List<Object[]> names = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            names.add(new Object[]{
                    RandomStringUtils.randomAlphabetic(40) + ".txt"
            });
        }
        return names.toArray(new Object[][]{});
    }

    @DataProvider
    public static Object[][] loadCyrillicNamesFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                CreateFileTest.class.getResourceAsStream("/filenames.data")));
        List<Object[]> names = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            names.add(new Object[]{line});
            line = reader.readLine();
        }
        reader.close();
        return names.toArray(new Object[][]{});
    }

    @Category(PositiveTests.class)
    @Test
    @UseDataProvider("generateNames")
    public void shouldCreateFile(String fileName) throws IOException {
        File file = new File(path + fileName);
        boolean isCreated = file.createNewFile();
        boolean exists = file.exists();
        Assert.assertTrue("File was not successfully created \n", isCreated & exists);
    }

    @Category(PositiveTests.class)
    @Test
    @UseDataProvider("generateLongNames")
    public void shouldCreateFileWithLongName(String fileName) throws IOException {
        File file = new File(path + fileName);
        boolean isCreated = file.createNewFile();
        boolean exists = file.exists();
        Assert.assertTrue("File with long name was not created \n", isCreated & exists);
    }

    @Category(PositiveTests.class)
    @Test
    @UseDataProvider("loadCyrillicNamesFromFile")
    public void shouldCreateFileWithCyrillicName(String fileName) throws IOException {
        File file = new File(path + fileName);
        boolean isCreated = file.createNewFile();
        boolean exists = file.exists();
        Assert.assertTrue("File with cyrillic name was not created \n", isCreated & exists);
    }

    @Category(NegativeTests.class)
    @Test
    @UseDataProvider("generateNames")
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
