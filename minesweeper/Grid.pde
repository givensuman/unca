import java.util.Arrays;

class Grid {
  public static final int ROWS = 5;
  public static final int COLUMNS = 5;
  public static final int BOMBS = 10;

  private Cell[][] grid;

  Grid() {
    this.grid = new Cell[ROWS][COLUMNS];
    for (int row = 0; row < Grid.ROWS; row++) {
      for (int column = 0; column < Grid.COLUMNS; column++) {
        this.grid[row][column] = new Cell(row, column);
      }
    }

    assignBombs();
  }

  public Cell get(int row, int column) {
    return this.grid[row][column];
  }

  private void assignBombs() {
    if (Grid.BOMBS >= ROWS * COLUMNS) {
      System.err.println("Too many bombs declared for grid of size " + ROWS * COLUMNS);
      System.exit(400);
    }

    PVector[] bombs = new PVector[BOMBS];

    int whileIndex = 0;
    while (Arrays.asList(bombs).contains(null)) {
      PVector nextBomb = new PVector((int) random(0, COLUMNS), (int) random(0, ROWS));
      while (Arrays.asList(bombs).contains(nextBomb)) {
        nextBomb = new PVector((int) random(0, COLUMNS), (int) random(0, ROWS));
      }

      bombs[whileIndex] = nextBomb;
      whileIndex++;
    }

    for (int i = 0; i < bombs.length; i++) {
      this.grid[(int) bombs[i].x][(int) bombs[i].y].makeBomb();

      for (int row = Math.max(0, (int) bombs[i].x - 1); row < Math.min(ROWS, (int) bombs[i].x + 2); row++) {
        for (int column = Math.max(0, (int) bombs[i].y - 1); column < Math.min(COLUMNS, (int) bombs[i].y + 2); column++) {
          this.grid[row][column].addNearbyBomb();
        }
      }
    }
  }

  public void reveal(Cell cell) {
    if (cell.getIsRevealed()) return;

    cell.reveal();

    if (cell.getNearbyBombs() == 0) {
      for (int row = Math.max(0, cell.getRow() - 1); row < Math.min(ROWS, cell.getRow() + 2); row++) {
        for (int column = Math.max(0, cell.getColumn() - 1); column < Math.min(COLUMNS, cell.getColumn() + 2); column++) {
          reveal(this.grid[row][column]);
        }
      }
    }
  }

  public void draw() {
    for (int row = 0; row < Grid.ROWS; row++) {
      for (int column = 0; column < Grid.COLUMNS; column++) {
        this.grid[row][column].draw();
      }
    }
  }
}
