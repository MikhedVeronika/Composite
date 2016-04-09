package com.epam.composite.handler;

import com.epam.composite.entity.ComponentType;
import com.epam.composite.entity.LexemeLeaf;
import com.epam.composite.service.RegularExpressions;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassHandler extends Handler {

    private static Logger logger = Logger.getLogger(ClassHandler.class);
    public static ClassHandler handler = new ClassHandler(ComponentType.CLASS);

    public ClassHandler(ComponentType componentType) {
        super(componentType);
    }

    public static ClassHandler getHandler() {
        return handler;
    }

    @Override
    public void parse(String text) {
        logger.info("Parsing class");
        Pattern packageImportPattern = Pattern.compile(RegularExpressions.PACKAGE_IMPORT_REGEX);
        Matcher packageImportMatcher = packageImportPattern.matcher(text);
        setSuccessor(OperatorHandler.getHandler());
        while (packageImportMatcher.find()) {
            String group = packageImportMatcher.group();
            text = text.replace(group, "");
            element.add(successor.chain(packageImportMatcher.group()));
        }

        // parsing declaration of class (declaration consists of lexemes)
        String declaration = text.substring(0, text.indexOf('{'));
        Pattern lexemePattern = Pattern.compile(RegularExpressions.LEXEME_REGEX);
        Matcher lexemeMatcher = lexemePattern.matcher(declaration);
        while (lexemeMatcher.find()) {
            String lexemeGroup = lexemeMatcher.group();
            element.add(new LexemeLeaf(ComponentType.LEXEME, lexemeGroup));
        }

        // parsing class content
        element.add(new LexemeLeaf(ComponentType.LEXEME, "{"));
        text = text.substring(text.indexOf('{') + 1);

        // parsing fields
        String codeFields = text;
        codeFields = codeFields.replaceAll(RegularExpressions.METHOD_REGEX, "");
        codeFields = codeFields.replaceAll(RegularExpressions.BLOCK_REGEX, "");

        Pattern fieldPattern = Pattern.compile(RegularExpressions.FIELD_REGEX);
        Matcher fieldMatcher = fieldPattern.matcher(codeFields);
        setSuccessor(FieldHandler.getHandler());
        while (fieldMatcher.find()) {
            String group = fieldMatcher.group();
            text = text.replace(group, "");
            element.add(successor.chain(fieldMatcher.group()));
        }

        // parsing blocks
        String codeBlocks = text;
        codeBlocks = codeBlocks.replaceAll(RegularExpressions.METHOD_REGEX, "");

        Pattern blockPattern = Pattern.compile(RegularExpressions.BLOCK_REGEX);
        Matcher blockMatcher = blockPattern.matcher(codeBlocks);
        setSuccessor(BlockHandler.getHandler());
        while (blockMatcher.find()) {
            String group = blockMatcher.group();
            element.add(successor.chain(blockMatcher.group()));
        }

        // parsing abstract methods
        String abstractMethods = text;
        abstractMethods = abstractMethods.replaceAll(RegularExpressions.METHOD_REGEX, "");
        abstractMethods = abstractMethods.replaceAll(RegularExpressions.BLOCK_REGEX, "");

        Pattern abstractPattern = Pattern.compile(RegularExpressions.ABSTRACT_METHOD);
        Matcher abstractMatcher = abstractPattern.matcher(abstractMethods);
        setSuccessor(OperatorHandler.getHandler());
        while (abstractMatcher.find()) {
            String group = abstractMatcher.group();
            element.add(successor.chain(abstractMatcher.group()));
        }

        // parsing methods
        Pattern methodPattern = Pattern.compile(RegularExpressions.METHOD_REGEX);
        Matcher methodMatcher = methodPattern.matcher(text);
        setSuccessor(MethodHandler.getHandler());
        while (methodMatcher.find()) {
            String group = methodMatcher.group();
            text = text.replace(group, "");
            element.add(successor.chain(methodMatcher.group()));
        }
        element.add(new LexemeLeaf(ComponentType.LEXEME, "}"));
    }
}


