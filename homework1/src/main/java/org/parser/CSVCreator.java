package org.parser;

import java.util.List;

public interface CSVCreator {
    void create(String fileName, List<List<String>> rows);
}
