package org.parser;

import org.parser.impl.ParserCSVCreatorMediatorImpl;

public class Application {

    private static final ParserCSVCreatorMediatorImpl MEDIATOR = new ParserCSVCreatorMediatorImpl();

    public static void main(String[] args) {
        MEDIATOR.createCSVWithTop5NGrams();
    }
}
