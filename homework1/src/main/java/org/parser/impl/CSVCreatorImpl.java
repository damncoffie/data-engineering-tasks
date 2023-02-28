package org.parser.impl;

import com.opencsv.CSVWriter;
import org.parser.CSVCreator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVCreatorImpl implements CSVCreator {

    private static final String[] HEADERS = new String[]{"author", "first 3-gram", "second 3-gram", "third 3-gram",
            "fourth 3-gram", "fifth 3-gram"};

    @Override
    public void create(String fileName, List<List<String>> rows) {
        if (rows == null || rows.isEmpty()) return;

        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {

            writer.writeNext(HEADERS);

            for (List<String> row : rows) {
                writer.writeNext(row.toArray(String[]::new));
            }

            System.out.println("CSV file created successfully!");

        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }
}
