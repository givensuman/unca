import java.util.Arrays;

enum GameState {
  WIN, LOSE, PLAYING
}

class Grid {
  public static final int ROWS = 10;
  public static final int COLUMNS = 10;
  public static final int BOMBS = 3;

  private Cell[][] grid;
  private GameState gameState = GameState.PLAYING;

  Grid() {

    if (Grid.ROWS < 4 || Grid.COLUMNS < 4) {
      throw new Error("Grid must be at least 4x4 in size");
    }

    if (Grid.BOMBS >= (ROWS * COLUMNS - 9)) {
      throw new Error("[ERROR] Too many bombs declared for grid of size " + ROWS * COLUMNS);
    }

    if (ROWS != COLUMNS) {
      System.err.println("Warning: Game does not work well when using unequal rows and columns");
    }

    this.grid = new Cell[ROWS][COLUMNS];
    for (int row = 0; row < Grid.ROWS; row++) {
      for (int column = 0; column < Grid.COLUMNS; column++) {
        this.grid[row][column] = new Cell(row, column);
      }
    }
  }

  public GameState getGameState() {
    return this.gameState;
  }

  public void setGameState(GameState gameState) {
    this.gameState = gameState;
  }

  public void instantiateGridWithClickAt(int row, int column) {
    assignBombsAroundClickAt(row, column);
    calculateNearbyBombs();
  }

  public Cell get(int row, int column) {
    return this.grid[row][column];
  }

  private void assignBombsAroundClickAt(int row, int column) {
    PVector[] bombs = new PVector[BOMBS];

    int whileIndex = 0;
    while (Arrays.asList(bombs).contains(null)) {
      PVector nextBomb = new PVector(random(0, COLUMNS), random(0, ROWS));
      while (
        Arrays.asList(bombs).contains(nextBomb)
        || (nextBomb.x >= column - 1 && nextBomb.x <= column + 1)
        || (nextBomb.y >= row - 1 && nextBomb.y <= row + 1)
        ) {
        nextBomb = new PVector(random(0, ROWS), random(0, COLUMNS));
      }

      bombs[whileIndex] = nextBomb;
      whileIndex++;
    }

    for (int i = 0; i < bombs.length; i++) {
      this.grid[(int) bombs[i].y][(int) bombs[i].x].makeBomb();
    }
  }

  private void calculateNearbyBombs() {
    for (int row = 0; row < Grid.ROWS; row++) {
      for (int column = 0; column < Grid.COLUMNS; column++) {
        Cell cell = this.grid[row][column];

        if (!cell.getIsBomb()) {
          int nearbyBombs = 0;

          for (int subRow = Math.max(0, cell.getRow() - 1); subRow < Math.min(cell.getRow() + 2, Grid.ROWS); subRow++) {
            for (int subColumn = Math.max(0, cell.getColumn() - 1); subColumn < Math.min(cell.getColumn() + 2, Grid.COLUMNS); subColumn++) {
              if (this.grid[subRow][subColumn].getIsBomb()) nearbyBombs++;
            }
          }

          cell.setBombsNearby(nearbyBombs);
        }
      }
    }
  }

  public void checkIfGameIsWon() {
    for (int row = 0; row < Grid.ROWS; row++) {
      for (int column = 0; column < Grid.COLUMNS; column++) {
        if (!this.grid[row][column].getIsRevealed() && !this.grid[row][column].getIsBomb()) {
          return;
        }
      }
    }

    this.gameState = GameState.WIN;
  }

  public void draw() {
    for (int row = 0; row < Grid.ROWS; row++) {
      for (int column = 0; column < Grid.COLUMNS; column++) {
        this.grid[row][column].draw();
      }
    }
  }
}
