package org.example.service;

import org.example.formatter.*;

public class ConvertService {

    private final MoneyUnitFormatter<Long> rubFormatter;
    private final MoneyUnitFormatter<Integer> kopFormatter;

    public ConvertService(MoneyUnitFormatter<Long> rubFormatter,
                          MoneyUnitFormatter<Integer> kopFormatter) {
        this.rubFormatter = rubFormatter;
        this.kopFormatter = kopFormatter;
    }

    public String convert(double number) {

        NumberSplitter numberSplitter = new NumberSplitter(number);

        String signText = NumberSignText.signText(number);
        String rubText = rubFormatter.format(numberSplitter.getRubles());
        String kopText = kopFormatter.format(numberSplitter.getKopecks());

        MonetaryUnits monetaryUnits = new MonetaryUnits(numberSplitter.getRubles(), numberSplitter.getKopecks());

        return signText + rubText + monetaryUnits.getRubUnits() + kopText + monetaryUnits.getKopUnits();
    }

    public static String convertAuto(double number) {

        return new ConvertService(
                new RublesFormatter(),
                new KopecksFormatter()
        ).convert(number);

    }
}
