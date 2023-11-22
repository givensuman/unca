class Cell {
  public final float HEIGHT = height/Grid.ROWS;
  public final float WIDTH = width/Grid.COLUMNS;

  private int row;
  private int column;
  private int bombsNearby = 0;
  private boolean isBomb = false;
  private boolean isFlag = false;
  private boolean isRevealed = false;

  Cell(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public int getRow() {
    return this.row;
  }

  public int getColumn() {
    return this.column;
  }

  public void makeBomb() {
    this.isBomb = true;
  }

  public boolean getIsBomb() {
    return this.isBomb;
  }

  public void toggleIsFlag() {
    this.isFlag = !this.isFlag;
  }

  public boolean getIsFlag() {
    return this.isFlag;
  }

  public int getBombsNearby() {
    return this.bombsNearby;
  }

  public void setBombsNearby(int bombsNearby) {
    this.bombsNearby = bombsNearby;
  }

  public boolean getIsRevealed() {
    return this.isRevealed;
  }

  public void setIsRevealed(boolean isRevealed) {
    this.isRevealed = isRevealed;
  }

  public void revealCell() {
    if (grid == null || this.isRevealed) return;

    if (this.isBomb) {
      // Reveal the entire grid, the game's over!
      for (int row = 0; row < Grid.COLUMNS; row++) {
        for (int column = 0; column < Grid.ROWS; column++) {
          grid.get(row, column).setIsRevealed(true);
        }
      }

      grid.setGameState(GameState.LOSE);

      return;
    }

    this.isRevealed = true;
    this.isFlag = false;

    // If this is a blank tile, recursively reveal tiles around it
    if (this.bombsNearby == 0) {
      for (int row = Math.max(0, this.row - 1); row < Math.min(this.row + 2, Grid.COLUMNS); row++) {
        for (int column = Math.max(0, this.column - 1); column < Math.min(this.column + 2, Grid.ROWS); column++) {
          if (row == this.row && column == this.column) {
            continue;
          } else {
            grid.get(row, column).revealCell();
          }
        }
      }
    }
  }

  public void draw() {
    PImage image;

    if (!this.isRevealed) {
      // Gray rectangle
      strokeWeight(1);
      fill(255 * 0.9);
      rect(this.column * WIDTH, this.row * HEIGHT, WIDTH, HEIGHT);
      
      //textSize(Math.min(200/Grid.ROWS, 200/Grid.ROWS));
      //fill(0);
      //text(this.row + ", " + this.column, WIDTH * (this.column + 0.25), HEIGHT * (this.row + 0.75));

      if (this.isFlag) {
        image = loadImage("flag.png");
      } else {
        image = null;
      }
    } else {
      // White rectangle
      strokeWeight(1);
      fill(255);
      rect(this.column * WIDTH, this.row * HEIGHT, WIDTH, HEIGHT);

      if (this.isBomb) {
        image = loadImage("bomb.png");
      } else {
        image = null;
      }

      if (!this.isFlag && !this.isBomb && this.bombsNearby > 0) {
        textSize(Math.min(300/Grid.ROWS, 300/Grid.COLUMNS));
        switch(this.bombsNearby) {
        case 1:
          fill(#0000f2);
          break;
        case 2:
          fill(#007e00);
          break;
        case 3:
          fill(#f90000);
          break;
        case 4:
          fill(#00007a);
          break;
        case 5:
          fill(#7a0100);
          break;
        case 6:
          fill(#007f7b);
          break;
        case 7:
          fill(#010101);
          break;
        default:
          fill(#7b7b7b);
          break;
        }
        text(this.bombsNearby, WIDTH * (this.column + 0.33), HEIGHT * (this.row + 0.75));
      }
    }

    if (image != null) {
      float imgWidth = WIDTH/3;
      float imgHeight = HEIGHT/3;

      image(
        image,
        (this.column * WIDTH) + WIDTH/2 - imgWidth/3,
        (this.row * HEIGHT) + HEIGHT/2 - imgHeight/3,
        imgWidth,
        imgHeight
        );
    }
  }
}
