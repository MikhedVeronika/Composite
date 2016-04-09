package com.epam.composite.logic;

import java.util.ArrayList;

public class OperatorsList {

    private static ArrayList<String> operatorList = new ArrayList<>();

    public static void addOperator(String operator) {
        operatorList.add(operator);
    }

    public static ArrayList<String> getOperatorList() {
        return new ArrayList<>(operatorList);

    }
}
