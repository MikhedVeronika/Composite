package com.epam.composite.handler;

import com.epam.composite.entity.ComponentType;
import com.epam.composite.entity.LexemeLeaf;
import com.epam.composite.service.RegularExpressions;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldHandler extends Handler {

    private static Logger logger = Logger.getLogger(FieldHandler.class);
    private static FieldHandler handler = new FieldHandler(ComponentType.FIELD);

    public FieldHandler(ComponentType type) {
        super(type);
    }

    public static FieldHandler getHandler() {
        return handler;
    }

    @Override
    public void parse(String text) {
        Pattern pattern = Pattern.compile(RegularExpressions.LEXEME_REGEX);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String group = matcher.group();
            element.add(new LexemeLeaf(ComponentType.LEXEME, group));
        }
    }
}
