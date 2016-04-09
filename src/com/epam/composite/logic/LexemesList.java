package com.epam.composite.logic;

import java.util.ArrayList;

public class LexemesList {
    private static ArrayList<String> lexemeList = new ArrayList<>();

    public static void addLexeme(String lexeme) {
        lexemeList.add(lexeme);
    }

    public static ArrayList<String> getLexemeList() {
        return new ArrayList<>(lexemeList);
    }

    public static ArrayList<String> deleteSymbolsFirst() {
        ArrayList<String> newList = new ArrayList<>();
        for (int i = 0; i < lexemeList.size(); i++) {
            String str = "";
            String charFirst = Character.toString(lexemeList.get(i).charAt(0));
            if (charFirst.matches("\\{|\\}|\\(|\\)|\\+|\\*")) {
                str = "\\";
            }
            newList.add(lexemeList.get(i).replaceAll(str + Character.toString(lexemeList.get(i).charAt(0)), ""));
        }
        return newList;
    }
}
