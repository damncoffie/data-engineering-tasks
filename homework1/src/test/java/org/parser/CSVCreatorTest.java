package org.parser;

import org.parser.impl.CSVCreatorImpl;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CSVCreatorTest {

    CSVCreator csvCreator = new CSVCreatorImpl();

    @Test
    public void whenCreateIsCalled_thenCSVIsCreated() {
        csvCreator.create("test.csv", getTestRows());
        File file = new File("test.csv");

        assertTrue(file.exists());
        assertTrue(file.delete());
    }

    private List<List<String>> getTestRows() {
        List<List<String>> testRows = new ArrayList<>();
        List<String> testRow = new ArrayList<>();
        testRow.add("1");
        testRow.add("2");
        testRows.add(testRow);

        return testRows;
    }
}
