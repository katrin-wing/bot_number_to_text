package org.example.formatter;

public class NumberSplitter {

    private final long rubles;
    private final int kopecks;
    private final int[] groups;

    public NumberSplitter(double number) {

        double absNumber = Math.abs(number);
        this.rubles = (long) absNumber;
        this.kopecks = (int) Math.round((absNumber - rubles) * 100);

        int billion = (int) (rubles / 1000000000);
        int million = (int) (rubles % 1000000000) / 1000000;
        int thousand = (int) (rubles % 1000000) / 1000;
        int hundred = (int) (rubles % 1000);

        this.groups = new int[4];
        groups[0] = billion;
        groups[1] = million;
        groups[2] = thousand;
        groups[3] = hundred;
    }
     public long getRubles() {
        return rubles;
     }

     public int getKopecks() {
        return kopecks;
     }

     public int getGroups(int index) {
         if (index < 0 || index >= groups.length) {
             throw new IllegalArgumentException("Invalid group index");
         }
        return groups[index];
     }

     public int[] getAllGroups() {
        return groups.clone();
     }
}
