package com.epam.composite.main;

import com.epam.composite.handler.CodeParser;
import com.epam.composite.logic.LexemesList;
import com.epam.composite.logic.LexemesSort;
import com.epam.composite.logic.OperatorsList;
import com.epam.composite.logic.OperatorsSort;
import com.epam.composite.service.FileWriteService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.ArrayList;

public class Main {

    static {
        new DOMConfigurator().doConfigure("resources/log4j.xml", LogManager.getLoggerRepository());
    }
    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        CodeParser parser = new CodeParser();
        parser.parseText("resources/input.txt");
        logger.info("Trying write results to file");
        parser.writeCodeToFile("results/output.txt");

        ArrayList<String> list = OperatorsList.getOperatorList();
        OperatorsSort.sortOperators(list);
        FileWriteService.writeOperators("results/output2.txt", list);

        FileWriteService.writeLexemesDeletedFirstLetter("results/output8.txt", LexemesList.getLexemeList(), LexemesList.deleteSymbolsFirst());

        ArrayList<String> lexemeList = LexemesList.getLexemeList();
        LexemesSort.sortLexemes(lexemeList);
        FileWriteService.writeLexemesSortedByAlphabet("results/output4.txt", lexemeList);
        logger.info("Program finishing");
    }
}
