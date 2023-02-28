package org.parser;

import org.parser.impl.GitEventParserImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ParserTest {

    GitEventParser parser = new GitEventParserImpl();

    @Test
    public void whenParseIsCalled_thenResultWithFixedCount() {
        List<List<String>> parsedRows = parser.parse("10K.github.jsonl");
        assertFalse(parsedRows.isEmpty());
        assertEquals(2797, parsedRows.size());
    }
}
