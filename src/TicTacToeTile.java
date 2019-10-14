public class TicTacToeTile {
    private int row;
    private int column;
    private String value;

    public TicTacToeTile(int row, int column) {
        this.row = row;
        this.column = column;
        this.value = " ";
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
