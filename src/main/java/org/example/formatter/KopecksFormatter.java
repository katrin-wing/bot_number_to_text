package org.example.formatter;

import org.example.util.TextConstants;

public class KopecksFormatter implements MoneyUnitFormatter<Integer> {

    @Override
    public String format(Integer kopecks) {
        if (kopecks == 0) return "";

        int dec = kopecks / 10;
        int ed = kopecks % 10;

        String text;
        if (kopecks < 10) {
            text = TextConstants.UNITS[1][kopecks];
        } else if (dec == 1 && ed != 0) text = TextConstants.FROM_11_TO_19[ed];
        else text = TextConstants.TENS[dec] + TextConstants.UNITS[1][ed];

        return text;
    }
}
