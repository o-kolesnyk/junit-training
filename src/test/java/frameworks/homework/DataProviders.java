package frameworks.homework;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    @DataProvider
    public static Iterator<Object[]> generateNames() {
        List<Object[]> names = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            names.add(new Object[]{
                    RandomStringUtils.randomAlphabetic(10) + ".txt"
            });
        }
        return names.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> generateLongNames() {
        List<Object[]> names = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            names.add(new Object[]{
                    RandomStringUtils.randomAlphabetic(40) + ".txt"
            });
        }
        return names.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> loadCyrillicNamesFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                DataProviders.class.getResourceAsStream("/filenames.data")));
        List<Object[]> names = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            names.add(new Object[]{line});
            line = reader.readLine();
        }
        reader.close();
        return names.iterator();
    }
}
