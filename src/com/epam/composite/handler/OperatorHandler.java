package com.epam.composite.handler;

import com.epam.composite.entity.ComponentType;
import com.epam.composite.entity.LexemeLeaf;
import com.epam.composite.logic.OperatorsList;
import com.epam.composite.service.RegularExpressions;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperatorHandler extends Handler {
    private static Logger logger = Logger.getLogger(OperatorsList.class);
    private static OperatorHandler handler = new OperatorHandler(ComponentType.OPERATOR);

    public OperatorHandler(ComponentType componentType) {
        super(componentType);
    }

    public static OperatorHandler getHandler() {
        return handler;
    }

    @Override
    public void parse(String text) {
        logger.info("Parsing operator");
        // operator is consists of lexemes
        OperatorsList.addOperator(text.trim());
        Pattern pattern = Pattern.compile(RegularExpressions.LEXEME_REGEX);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String group = matcher.group();
            element.add(new LexemeLeaf(ComponentType.LEXEME, group));
        }
    }
}
