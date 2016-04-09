package com.epam.composite.service;

public class RegularExpressions {

    public static final String PACKAGE_IMPORT_REGEX = "(((package)|(import))\\s+)(\\w|\\.)+;\\s*";

    public static final String OPERATOR_REGEX = "[^\\{\\};]*;";
    public static final String LEXEME_REGEX = "\\S+";
    public static final String NAME = "[A-z_]\\w*\\s*";
    public static final String GENERIC = "(<\\s*([A-z_]\\w*,\\s*)*[A-z_]\\w*\\s*>)?\\s*";
    public static final String OBJECT_TYPE = NAME + GENERIC;
    public static final String METHOD_PARAM = OBJECT_TYPE + NAME;
    public static final String FIELD_DECLARATION = OBJECT_TYPE + NAME;

    public static final String BLOCK_REGEX = "((static\\s*)|(synchronized(\\(\\s*" + NAME + "\\s*)\\))?\\s*)?\\{[^\\{\\}]*\\}";

    public static final String FIELD_REGEX = "((public|private|protected|static|final|volatile)\\s+)*" + FIELD_DECLARATION +
            "(=\\s*(\".*\"|[^;]*)\\s*)?;";

    public static final String METHOD_REGEX = "(@" + NAME + ")*\\s*(((public|private|protected)|static|abstract|final|synchronized|default)\\s+)*" +
            "(" + METHOD_PARAM + ")" + "\\s*\\(\\s*((" + METHOD_PARAM + ",\\s*)*" + METHOD_PARAM + ")?\\s*\\)\\s*\\{" +
            "(\\s*((" + OPERATOR_REGEX + ")|(" + BLOCK_REGEX + "))\\s*)*\\}";

    public static final String ABSTRACT_METHOD = "(public|private|protected)?\\s*(static)?\\s*(abstract)?\\s*" + METHOD_PARAM +
            "\\s*\\(\\s*((" + METHOD_PARAM + ",\\s*)*" + METHOD_PARAM + ")?\\s*\\)\\s*;";

    /*public static final String CLASS_REGEX = "(public)?(\\s+)?(abstract|final)?(\\s+)?(class|interface)\\s+"+OBJECT_TYPE+
            "(extends\\s+"+OBJECT_TYPE+")?(implements(\\s+"+NAME+",)*(\\s+"+NAME+"))?\\s*"+
             "\\{\\s*(("+FIELD_REGEX+")|("+BLOCK_REGEX+")|("+METHOD_REGEX+")\\s*)*\\}";*/

}
