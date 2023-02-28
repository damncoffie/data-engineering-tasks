package org.parser.impl;

import org.parser.CSVCreator;
import org.parser.GitEventParser;
import org.parser.ParserCSVCreatorMediator;

import java.util.List;

public class ParserCSVCreatorMediatorImpl implements ParserCSVCreatorMediator {

    private final CSVCreator csvCreator;
    private final GitEventParser gitEventParser;

    // spring would be an overkill for this task, probably
    public ParserCSVCreatorMediatorImpl() {
        csvCreator = new CSVCreatorImpl();
        gitEventParser = new GitEventParserImpl();
    }

    @Override
    public void createCSVWithTop5NGrams() {
        List<List<String>> parseResult = gitEventParser.parse("10K.github.jsonl");
        csvCreator.create("output.csv", parseResult);
    }
}
