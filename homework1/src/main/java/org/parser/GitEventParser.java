package org.parser;

import java.util.List;

public interface GitEventParser {

    record Event(String type, Payload payload) {
        public record Payload(List<Commit> commits) {
        }

        public record Author(String name, String email) {
        }

        public record Commit(Author author, String message) {
        }
    }

    List<List<String>> parse(String fileName);
}
