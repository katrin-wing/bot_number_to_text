package org.example.formatter;

public class NumberSignText {

    public static String signText(double number) {
        if (number < 0) return "Минус ";
        else return "";
    }
}
