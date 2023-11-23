Grid grid;
boolean gameIsStarted = false;

void setup() {
  size(500, 500);

  grid = new Grid();
}

void draw() {
  background(255);

  grid.draw();
}

void mousePressed() {
  int row = (int) mouseY / (height / Grid.ROWS);
  int column = (int) mouseX / (width / Grid.COLUMNS);

  if (mouseButton == LEFT) {
    if (!gameIsStarted) {
      gameIsStarted = true;
      grid.instantiateGridWithClickAt(row, column);
    }

    grid.get(row, column).revealCell();
    grid.checkIfGameIsWon();

    GameState gameState = grid.getGameState();
    if (gameState == GameState.WIN) {
      print("You won!");
    } else if (gameState == GameState.LOSE) {
      print("You lost...");
    }
  }

  if (mouseButton == RIGHT) {
    grid.get(row, column).toggleIsFlag();
  }
}

/**
 TODO:
 
 Animations for win/loss
 Convert images to .svg
 Win condition met only when all flags placed
 
 Presentation sequence:
 
 Making and drawing the grid
 Making and drawing the cell
   Toggle flag state
 Assigning bombs
 Calculating nearby bombs and displaying number in cell
 Reveal logic
   Toggling reveal state
   Revealing whole board when bomb is clicked
 First click will always be empty
   gameIsStarted boolean
   instantiateGridWithClickAt changes
 Win/loss enum
 */
