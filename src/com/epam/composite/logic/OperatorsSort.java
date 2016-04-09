package com.epam.composite.logic;

import java.util.ArrayList;

public class OperatorsSort {

    public static void sortOperators(ArrayList<String> operators) {
        operators.sort((o1, o2) -> o1.split(" ").length - o2.split(" ").length);
    }
}
