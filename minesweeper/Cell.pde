class Cell {
  public final float HEIGHT = height/Grid.COLUMNS;
  public final float WIDTH = width/Grid.ROWS;

  private int row;
  private int column;
  private boolean isBomb = false;
  private boolean isFlag = false;
  private int nearbyBombs = 0;
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

  public void toggleFlag() {
    this.isFlag = !this.isFlag;
  }

  public boolean getIsFlag() {
    return this.isFlag;
  }

  public void addNearbyBomb() {
    this.nearbyBombs++;
  }

  public int getNearbyBombs() {
    return this.nearbyBombs;
  }
  
  public boolean getIsRevealed() {
   return this.isRevealed; 
  }

  public void reveal() {
    this.isRevealed = true;
  }

  public void draw() {
    strokeWeight(1);
    fill(255);
    rect(this.row * WIDTH, this.column * HEIGHT, WIDTH, HEIGHT);

    if (!isRevealed) {
      fill(#d8d8d8);
      rect(this.row * WIDTH, this.column * HEIGHT, WIDTH, HEIGHT);
      return;
    }

    PImage image;

    if (this.isFlag) {
      image = loadImage("flag.png");
    } else if (this.isBomb) {
      image = loadImage("bomb.png");
    } else {
      image = null;
    }

    float imgWidth = WIDTH/3;
    float imgHeight = HEIGHT/3;

    if (image != null) {
      image(
        image,
        (this.row * WIDTH) + WIDTH/2 - imgWidth/3,
        (this.column * HEIGHT) + HEIGHT/2 - imgHeight/3,
        imgWidth,
        imgHeight
        );
    } else if (this.nearbyBombs > 0) {
      textSize(64);

      switch(this.nearbyBombs) {
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

      text(this.nearbyBombs, (this.row + 0.5) * WIDTH - 16, (this.column + 0.5) * HEIGHT + 16);
    }
  }
}
