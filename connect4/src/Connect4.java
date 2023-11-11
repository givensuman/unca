import java.util.Scanner;

public class Connect4 {
    private Board board;
    private Scanner scanner;
    private Player turn = Player.RED;

    Connect4() {
        this.board = new Board();
        this.scanner = new Scanner(System.in);
    }

    public void play() {
        System.out.println(this.board);
        while (!this.board.isFull()) {
            getMove();
            // hasAnybodyWonYet();
        }

        System.out.println("Tie game!");
        System.exit(0);
    }

    private void getMove() {
        boolean isValid = false;

        while (!isValid) {
            switch (this.turn) {
                case RED:
                    System.out.println("Red's turn:");
                    break;
                case BLACK:
                    System.out.println("Black's turn:");
                    break;
                default:
                    break;
            }

            System.out.println("Choose a column (1-7):");
            String userInput = scanner.nextLine();

            if (userInput.toLowerCase().equals("exit")) {
                System.out.println("Thanks for playing!");
                System.exit(200);
            }

            // (ノÒ益Ó)ノ彡▔▔▏
            if (userInput.toLowerCase().equals("table flip")) {
                System.out.println("Alright then, I guess everybody loses.");
                System.exit(400);
            }

            int columnIndex = 0;
            boolean result;
            try {
                columnIndex = Integer.parseInt(userInput) - 1;

                result = this.board.insert(new Disk(this.turn), columnIndex);

                if (columnIndex < 0 || columnIndex > Board.COLUMNS - 1) result = false;

            } catch (NumberFormatException err) {
                result = false;
            }

            if (result) {                
                hasAnybodyWonYet(evaluateVerticalWin(columnIndex));
                hasAnybodyWonYet(evaluateHorizontalWin(columnIndex));
                hasAnybodyWonYet(evaluateLowerDiagonalWin(columnIndex));
                hasAnybodyWonYet(evaluateUpperDiagonalWin(columnIndex));

                isValid = true;

                switch (this.turn) {
                    case RED:
                        this.turn = Player.BLACK;
                        break;
                    case BLACK:
                        this.turn = Player.RED;
                        break;
                    default:
                        break;
                }

                System.out.println(this.board);

            } else {
                System.err.println("Invalid move, please try again.");
            }
        }
    }

    /**
     * Runs over the column last inserted into, checks the four top disks,
     * and evaluates if they are of the same color
     * @param column - Column object last inserted into
     * @param rowIndex - Index of the row last inserted into
     * @return RED, BLACK, or NULL
     */
    private Player evaluateVerticalWin(int columnIndex) {
        Column column = this.board.get(columnIndex);
        int rowIndex = column.size() - 1;

        if (rowIndex < 3) return Player.NULL;

        if (column.get(rowIndex).isBlack()
            && column.get(rowIndex - 1).isBlack()
            && column.get(rowIndex - 2).isBlack()
            && column.get(rowIndex - 3).isBlack()
        ) return Player.BLACK;

        if (column.get(rowIndex).isRed()
            && column.get(rowIndex - 1).isRed()
            && column.get(rowIndex - 2).isRed()
            && column.get(rowIndex - 3).isRed()
        ) return Player.RED;

        return Player.NULL;
    }

    /**
     * Checks the three disks to the left and right of the disk last 
     * inserted into the board, and evaluates if they are the same color
     * @param columnIndex - Index of the column last inserted into
     * @param rowIndex - Index of the row last inserted into
     * @return RED, BLACK, or NULL
     */
    private Player evaluateHorizontalWin(int columnIndex) {
        int rowIndex = this.board.get(columnIndex).size() - 1;

         /**
         * Note that we need to check horizontals with every disk in the 
         * horizontal as a potential starting point
         */
        int startingColumnIndex;

        // Go left, then traverse back
        startingColumnIndex = Math.max(0, columnIndex - 3);
        if (columnIndex > 0) {
            for (int i = 0; i < 4; i++) {
                // Guard for indexing out of bounds
                if (startingColumnIndex + i >= Board.COLUMNS - 3) break;

                if (this.board.get(startingColumnIndex + i).get(rowIndex).isBlack()
                    && this.board.get(startingColumnIndex + i + 1).get(rowIndex).isBlack()
                    && this.board.get(startingColumnIndex + i + 2).get(rowIndex).isBlack()
                    && this.board.get(startingColumnIndex + i + 3).get(rowIndex).isBlack()
                ) return Player.BLACK;

                if (this.board.get(startingColumnIndex + i).get(rowIndex).isRed()
                    && this.board.get(startingColumnIndex + i + 1).get(rowIndex).isRed()
                    && this.board.get(startingColumnIndex + i + 2).get(rowIndex).isRed()
                    && this.board.get(startingColumnIndex + i + 3).get(rowIndex).isRed()
                ) return Player.RED;
            }
        }


        // Go right, then traverse back
        startingColumnIndex = Math.min(Board.COLUMNS - 1, columnIndex + 3);
        System.out.println("STARTINGCOLUMNINDEX: " + startingColumnIndex);

        if (columnIndex < Board.COLUMNS - 1) {
            for (int i = 0; i < 4; i++) {
                // Guard for indexing out of bound
                if (startingColumnIndex - i < 3) break;

                if (this.board.get(startingColumnIndex - i).get(rowIndex).isBlack()
                    && this.board.get(startingColumnIndex - i - 1).get(rowIndex).isBlack()
                    && this.board.get(startingColumnIndex - i - 2).get(rowIndex).isBlack()
                    && this.board.get(startingColumnIndex - i - 3).get(rowIndex).isBlack()
                ) return Player.BLACK;

                if (this.board.get(startingColumnIndex - i).get(rowIndex).isRed()
                    && this.board.get(startingColumnIndex - i - 1).get(rowIndex).isRed()
                    && this.board.get(startingColumnIndex - i - 2).get(rowIndex).isRed()
                    && this.board.get(startingColumnIndex - i - 3).get(rowIndex).isRed()
                ) return Player.RED;
            }
        }

        return Player.NULL;
    }

    // When it comes to some of the math logic for the next two methods... just trust me bro

    /**
     * Checks diagonals going down from the disk last inserted 
     * into the board, and evaluates if they are the same color
     * @return RED, BLACK, or NULL
     */
    private Player evaluateLowerDiagonalWin(int columnIndex) {
        int rowIndex = this.board.get(columnIndex).size() - 1;

        // At bottom column, a diagonal win going down isn't possible
        if (rowIndex == 0) return Player.NULL;

        /**
         * Note that we need to check diagonals with every disk in the 
         * diagonal as a potential starting point
         */
        int startingColumnIndex;
        int startingRowIndex;

        // Go down and left, then traverse back up
        startingColumnIndex = Math.max(0, columnIndex - Math.min(rowIndex, 3));
        startingRowIndex = Math.max(0, rowIndex - 3);

        if (columnIndex > 0 && rowIndex > 0) {
            for (int i = 0; i < Math.min(4, Math.min(rowIndex, columnIndex) + 1); i++) {
                // Guard for indexing out of bounds
                if (startingColumnIndex + i >= Board.COLUMNS - 3 || startingRowIndex + i >= Column.ROWS - 3) break;

                if (this.board.get(startingColumnIndex + i).get(startingRowIndex + i).isBlack()
                    && this.board.get(startingColumnIndex + i + 1).get(startingRowIndex + i + 1).isBlack()
                    && this.board.get(startingColumnIndex + i + 2).get(startingRowIndex + i + 2).isBlack()
                    && this.board.get(startingColumnIndex + i + 3).get(startingRowIndex + i + 3).isBlack()
                ) return Player.BLACK;

                if (this.board.get(startingColumnIndex + i).get(startingRowIndex + i).isRed()
                    && this.board.get(startingColumnIndex + i + 1).get(startingRowIndex + i + 1).isRed()
                    && this.board.get(startingColumnIndex + i + 2).get(startingRowIndex + i + 2).isRed()
                    && this.board.get(startingColumnIndex + i + 3).get(startingRowIndex + i + 3).isRed()
                ) return Player.RED;
            }
        }

        // Go down and right, then traverse back up
        startingColumnIndex = Math.min(6, columnIndex + Math.min(rowIndex, 3));

        if (columnIndex < Board.COLUMNS - 1 && rowIndex < Column.ROWS - 1) {
            for (int i = 0; i < Math.min(4, Math.min(rowIndex + 1, Board.COLUMNS - columnIndex)); i++) {
                // Guard for indexing out of bounds
                if (startingColumnIndex - i < 3 || startingRowIndex + i >= Column.ROWS - 3) break;

                if (this.board.get(startingColumnIndex - i).get(startingRowIndex + i).isBlack()
                    && this.board.get(startingColumnIndex - i - 1).get(startingRowIndex + i + 1).isBlack()
                    && this.board.get(startingColumnIndex - i - 2).get(startingRowIndex + i + 2).isBlack()
                    && this.board.get(startingColumnIndex - i - 3).get(startingRowIndex + i + 3).isBlack() 
                ) return Player.BLACK;

                if (this.board.get(startingColumnIndex - i).get(startingRowIndex + i).isRed()
                    && this.board.get(startingColumnIndex - i - 1).get(startingRowIndex + i + 1).isRed()
                    && this.board.get(startingColumnIndex - i - 2).get(startingRowIndex + i + 2).isRed()
                    && this.board.get(startingColumnIndex - i - 3).get(startingRowIndex + i + 3).isRed() 
                ) return Player.RED;
            }
        }

        return Player.NULL;
    }

    /**
     * Checks diagonals going up from the disk last inserted 
     * into the board, and evaluates if they are the same color
     * @return RED, BLACK, or NULL
     */
    private Player evaluateUpperDiagonalWin(int columnIndex) {
        int rowIndex = this.board.get(columnIndex).size() - 1;

        // At top column, a diagonal win going up isn't possible
        if (rowIndex == Column.ROWS - 1) return Player.NULL;

        /**
         * Note that we need to check diagonals with every disk in the 
         * diagonal as a potential starting point
         */
        int startingColumnIndex;
        int startingRowIndex;

        // Go up and left, then traverse back down
        startingColumnIndex = Math.max(0, columnIndex - 3);
        startingRowIndex = Math.min(Column.ROWS - 1, rowIndex + Math.min(columnIndex, 3));

        if (rowIndex < Column.ROWS - 1 && columnIndex > 0) {
            for (int i = 0; i < Math.min(4, Math.min(columnIndex + 1, Column.ROWS - rowIndex)); i++) {
                // Guard for indexing out of bounds
                if (startingColumnIndex + i >= Board.COLUMNS - 3 || startingRowIndex - i < 3) break;

                if (this.board.get(startingColumnIndex + i).get(startingRowIndex - i).isBlack()
                    && this.board.get(startingColumnIndex + i + 1).get(startingRowIndex - i - 1).isBlack()
                    && this.board.get(startingColumnIndex + i + 2).get(startingRowIndex - i - 2).isBlack()
                    && this.board.get(startingColumnIndex + i + 3).get(startingRowIndex - i - 3).isBlack()
                ) return Player.BLACK;

                if (this.board.get(startingColumnIndex + i).get(startingRowIndex - i).isRed()
                    && this.board.get(startingColumnIndex + i + 1).get(startingRowIndex - i - 1).isRed()
                    && this.board.get(startingColumnIndex + i + 2).get(startingRowIndex - i - 2).isRed()
                    && this.board.get(startingColumnIndex + i + 3).get(startingRowIndex - i - 3).isRed()
                ) return Player.RED;
            }
        }

        // Go up and right, then traverse back down
        startingColumnIndex = Math.min(Board.COLUMNS - 1, columnIndex + 3);
        startingRowIndex = Math.min(Column.ROWS - 1, rowIndex + Math.min(Board.COLUMNS - 1 - columnIndex, 3));

        if (rowIndex < Column.ROWS - 1 && columnIndex < Board.COLUMNS - 1) {
            for (int i = 0; i < Math.min(4, Math.min(Board.COLUMNS - columnIndex, Column.ROWS - rowIndex)); i++) {
                if (startingColumnIndex - i < 3 || startingRowIndex - i < 3) break;

                if (this.board.get(startingColumnIndex - i).get(startingRowIndex - i).isBlack()
                    && this.board.get(startingColumnIndex - i - 1).get(startingRowIndex - i - 1).isBlack()
                    && this.board.get(startingColumnIndex - i - 2).get(startingRowIndex - i - 2).isBlack()
                    && this.board.get(startingColumnIndex - i - 3).get(startingRowIndex - i - 3).isBlack()
                ) return Player.BLACK;

                if (this.board.get(startingColumnIndex - i).get(startingRowIndex - i).isRed()
                    && this.board.get(startingColumnIndex - i - 1).get(startingRowIndex - i - 1).isRed()
                    && this.board.get(startingColumnIndex - i - 2).get(startingRowIndex - i - 2).isRed()
                    && this.board.get(startingColumnIndex - i - 3).get(startingRowIndex - i - 3).isRed()
                ) return Player.RED;
            }
        }

        return Player.NULL;
    }

    /**
     * Simple utility method to evaluate if one of the above win conditions
     * was met and returned a player RED or BLACK, and ends the program if that's the case
     * @param player - Player enum to be tested
     */
    private void hasAnybodyWonYet(Player player) {
        if (player != Player.NULL) {
            switch (player) {
                case RED:
                    System.out.println(this.board);
                    System.out.println("Red wins!");
                    System.exit(200);
                    break;
                case BLACK:
                    System.out.println(this.board);
                    System.out.println("Black wins!");
                    System.exit(200);
                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Connect4 game = new Connect4();
        game.play();
    }
}
