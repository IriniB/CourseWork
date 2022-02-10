package dancingLinks;

import java.util.Random;

public class IndexPair {
    private final int rowNumber;
    private final int colNumber;

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

    protected static IndexPair generateRandom(int rowStart, int colStart) {
        Random random = new Random();
        return new IndexPair(random.nextInt(rowStart, rowStart + 3), random.nextInt(colStart, colStart + 3));
    }

}
