package maze;

public enum Cell {
    WALL("\u2588\u2588"),
    SPACE("  ");

    private final String value;

    private Cell(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
