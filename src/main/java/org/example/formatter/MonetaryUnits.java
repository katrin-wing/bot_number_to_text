package org.example.formatter;

public class MonetaryUnits {

    private final String rubUnits;
    private final String kopUnits;

    public MonetaryUnits(long rub, int kop) {
        if (rub == 0) this.rubUnits = "";
        else this.rubUnits = "руб. ";

        if (kop == 0) this.kopUnits = "";
        else this.kopUnits = "коп. ";
    }

    public String getRubUnits() {
        return this.rubUnits;
    }

    public String getKopUnits() {
        return this.kopUnits;
    }
}
