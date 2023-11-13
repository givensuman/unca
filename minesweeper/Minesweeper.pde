Grid grid;

void setup() {
 size(500, 500);
 
 grid = new Grid();
}

void draw() {
 background(255);
 
 grid.draw();
}

void mousePressed() {
 this.grid.reveal(this.grid.get((int) mouseX / (height / Grid.COLUMNS), (int) mouseY / (width / Grid.ROWS)));
 
 for (int i = 0; i < Grid.ROWS; i++) {
  for (int j = 0; j < Grid.COLUMNS; j++) {
   println("("+i+", "+j+")"+": " + grid.get(i, j).getNearbyBombs()); 
  }
 }
}
