package com.epam.composite.handler;

import com.epam.composite.entity.ComponentType;
import com.epam.composite.entity.LexemeLeaf;
import com.epam.composite.service.RegularExpressions;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BlockHandler extends Handler {

    private static Logger logger = Logger.getLogger(BlockHandler.class);
    private static BlockHandler handler = new BlockHandler(ComponentType.BLOCK);

    public BlockHandler(ComponentType componentType) {
        super(componentType);
    }

    public static BlockHandler getHandler() {
        return handler;
    }

    @Override
    public void parse(String text) {
        logger.info("Parsing block");
        String declaration = text.substring(0, text.indexOf('{'));
        Pattern lexemePattern = Pattern.compile(RegularExpressions.LEXEME_REGEX);
        Matcher lexemeMatcher = lexemePattern.matcher(declaration);
        while (lexemeMatcher.find()) {
            String group = lexemeMatcher.group();
            element.add(new LexemeLeaf(ComponentType.LEXEME, group));
        }

        // block consists of lexeme '{' , operators and lexeme '}'
        element.add(new LexemeLeaf(ComponentType.LEXEME, "{"));
        Pattern operatorPattern = Pattern.compile(RegularExpressions.OPERATOR_REGEX);
        Matcher operatorMatcher = operatorPattern.matcher(text);
        setSuccessor(OperatorHandler.getHandler());
        while (operatorMatcher.find()) {
            String group = operatorMatcher.group();
            element.add(successor.chain(group));
        }
        element.add(new LexemeLeaf(ComponentType.LEXEME, "}"));
    }
}
