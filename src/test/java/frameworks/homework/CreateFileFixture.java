package frameworks.homework;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateFileFixture {

    protected Path path;

    @BeforeSuite
    public void createDirectory() {
        try {
            path = Files.createTempDirectory("TestDirectory");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void cleanDirectory() throws IOException {
        FileUtils.cleanDirectory(new File(path.toString()));
    }

    @AfterSuite
    public void deleteDirectory() {
        path.toFile().deleteOnExit();
    }
}
