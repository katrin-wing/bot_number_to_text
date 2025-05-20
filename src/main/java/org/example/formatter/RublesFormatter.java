package org.example.formatter;

import org.example.util.TextConstants;

public class RublesFormatter implements MoneyUnitFormatter<Long>{

    @Override
    public String format(Long rubles) {

        if (rubles == 0) return "Ноль руб. ";

        StringBuilder result = new StringBuilder();

        NumberSplitter numberSplitter = new NumberSplitter(rubles);
        int[] groups = numberSplitter.getAllGroups();

        for (int i = 0; i < groups.length; i++) {
            result.append(formatGroup(groups[i], i));
        }

        return result.toString();
    }

    public String formatGroup (int ranks, int index) {

        int sot = ranks / 100;
        int dec = (ranks / 10) % 10;
        int ed = ranks % 10;

        int indexEd;
        if (index == 2) indexEd = 1;
        else indexEd = 0;

        String text;

        if (dec == 1 && ed != 0)
            text = TextConstants.HUNDREDS[sot] + TextConstants.FROM_11_TO_19[ed];
        else text = TextConstants.HUNDREDS[sot] + TextConstants.TENS[dec] + TextConstants.UNITS[indexEd][ed];

        int indexDegree = 0;

        if (ranks != 0) {
            if (dec == 1) indexDegree = 3;
            else if (ed == 1) indexDegree = 1;
            else if (ed >= 2 && ed <= 4) indexDegree = 2;
            else indexDegree = 3;
        }
        return text + TextConstants.DEGREES[indexDegree][index];
    }

}
