package org.parser;

import org.junit.Test;
import org.parser.impl.ParserCSVCreatorMediatorImpl;

import java.io.File;

import static org.junit.Assert.assertTrue;


public class MediatorTest {

    ParserCSVCreatorMediatorImpl mediator = new ParserCSVCreatorMediatorImpl();

    @Test
    public void whenCreateMethodIsCalled_thenFileIsCreated() {
        mediator.createCSVWithTop5NGrams();

        File file = new File("output.csv");
        assertTrue(file.exists());
        assertTrue(file.delete());
    }
}
