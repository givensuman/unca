public class Board {
    static final int COLUMNS = 7;
    private Column[] board;

    Board() {
        this.board = new Column[COLUMNS];
        for (int i = 0; i < COLUMNS; i++) {
            this.board[i] = new Column();
        }
    }

    public Column get(int columnIndex) {
        return this.board[columnIndex];
    }

    /**
     * @param disk - The disk to insert
     * @param columnIndex - The index in which to insert a disk
     * @return true if insertion is possible
     */
    public boolean insert(Disk disk, int columnIndex) {
        if (columnIndex >= COLUMNS || columnIndex < 0)
            return false;

        boolean result = this.board[columnIndex].insert(disk);
        return result;
    }

    public String toString() {
        String result = "";
        // Print column labels
        for (int i = 0; i < COLUMNS; i++) {
            result += "| " + (i + 1) + " ";
        }
        result += "|\n";
        // Print divider ---
        result += "-".repeat(4 * COLUMNS) + "-\n";

        // Print column contents
        for (int row = Column.ROWS - 1; row >= 0; row--) {
            for (int column = 0; column < COLUMNS; column++) {
                result += ("|" + this.board[column].get(row).toString());
            }

            result += "|\n";
        }

        // Print divider ---
        result += "-".repeat(4 * COLUMNS) + "-";
        return result;
    }

    public boolean isFull() {
        for (Column column : this.board) {
            if (!column.isFull())
                return false;
        }

        return true;
    }
}
