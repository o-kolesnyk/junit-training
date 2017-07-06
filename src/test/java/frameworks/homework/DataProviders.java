package frameworks.homework;

import com.tngtech.java.junit.dataprovider.DataProvider;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.runners.model.FrameworkMethod;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataProviders {

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
    public static Object[][] loadCyrillicNamesFromFile(FrameworkMethod method) throws IOException {

        DataSource dataSource = method.getAnnotation(DataSource.class);
        if (dataSource == null) {
            throw new Error("Test method has no @DataSource annotation: " + method.getName());
        }
        switch (dataSource.type()) {
            case RESOURCE:
                return loadDataFromResource(dataSource.value());

            case FILE:
                return loadDataFromFile(dataSource.value());

            default:
                throw new Error("Data source type is not supported: " + dataSource.type());
        }
    }

    private static Object[][] loadDataFromResource(String value) throws IOException {
        return loadDataFromInputStream(DataProviders.class.getResourceAsStream(value));
    }

    private static Object[][] loadDataFromFile(String value) throws IOException {
        return loadDataFromInputStream(new FileInputStream(new File(value)));
    }

    private static Object[][] loadDataFromInputStream(InputStream input) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(input));

        List<Object[]> userData = new ArrayList<>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }

        in.close();

        return userData.toArray(new Object[][]{});
    }

}
