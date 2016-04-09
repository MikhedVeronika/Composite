package com.epam.composite.logic;


import java.util.ArrayList;

public class LexemesSort {
    public static void sortLexemes(ArrayList<String> lexemes) {
        lexemes.sort(String::compareToIgnoreCase);
    }
}
