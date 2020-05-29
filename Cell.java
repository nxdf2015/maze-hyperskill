package maze;


public enum Cell {
    WALL("\u2588\u2588"),
    SPACE("  "),
    EXIT("  "),
    PATH("**"),
    ENTRANCE("  ");
    private final String s;

    private Cell(String s) {
        this.s = s;
    }


    @Override
    public String toString() {
        return  s;
    }


}
