package frameworks.homework;

import org.apache.commons.io.FileUtils;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.ExternalResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateFileFixture {

    protected static Path path;

    @Rule
    public ExternalResource fileRule2 = new ExternalResource() {
        @Override
        protected void after() {
            try {
                FileUtils.cleanDirectory(new File(path.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    @ClassRule
    public static ExternalResource fileRule = new ExternalResource() {
        @Override
        protected void before() {
            try {
                path = Files.createTempDirectory("TestDirectory");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void after() {
            path.toFile().deleteOnExit();
        }
    };

}
