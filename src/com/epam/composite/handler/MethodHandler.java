package com.epam.composite.handler;

import com.epam.composite.entity.ComponentType;
import com.epam.composite.entity.LexemeLeaf;
import com.epam.composite.service.RegularExpressions;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodHandler extends Handler {

    private static Logger logger = Logger.getLogger(MethodHandler.class);
    private static MethodHandler handler = new MethodHandler(ComponentType.METHOD);

    public MethodHandler(ComponentType type) {
        super(type);
    }

    public static MethodHandler getHandler() {
        return handler;
    }

    @Override
    public void parse(String text) {
        logger.info("Parsing method");
        // parsing method declaration (declaration is consists of lexemes)
        String declaration = text.substring(0, text.indexOf('{'));
        Pattern lexemePattern = Pattern.compile(RegularExpressions.LEXEME_REGEX);
        Matcher lexemeMatcher = lexemePattern.matcher(declaration);
        while (lexemeMatcher.find()) {
            String group = lexemeMatcher.group();
            element.add(new LexemeLeaf(ComponentType.LEXEME, group));
        }
        // parsing method content
        element.add(new LexemeLeaf(ComponentType.LEXEME, "{"));
        Pattern patternOperator = Pattern.compile(RegularExpressions.OPERATOR_REGEX);
        Matcher matcherOperator = patternOperator.matcher(text);
        setSuccessor(OperatorHandler.getHandler());
        while (matcherOperator.find()) {
            String group = matcherOperator.group();
            element.add(successor.chain(group));
        }
        element.add(new LexemeLeaf(ComponentType.LEXEME, "}"));
    }
}
