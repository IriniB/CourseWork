package dancingLinks;

import java.util.Random;

public class IndexPair {
    private int rowNumber;
    private int colNumber;

    public int getRowNumber() { return rowNumber; }
    public int getColNumber() { return colNumber; }

    public IndexPair(int rowNumber, int colNumber) {
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
    }

    @Override
    public boolean equals(Object o) {
        IndexPair other = (IndexPair) o;
        return (rowNumber == other.rowNumber) && (colNumber == other.colNumber);
    }

    protected static IndexPair generateRandom() {
        Random random = new Random();
        return new IndexPair(random.nextInt(0, 9), random.nextInt(0, 9));
    }

}
