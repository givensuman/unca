public class Column {
    static final int ROWS = 6;
    private Disk[] disks;
    private int height = 0;

    Column() {
        this.disks = new Disk[ROWS];
        for (int i = 0; i < ROWS; i++) {
            /**
             * It will be useful to fill empty spaces in the column with
             * dummy disks to keep this.disks 
             *  a) of constant length, and
             *  b) not reliant on nulls
             */
            this.disks[i] = new Disk(Player.NULL);
        }
    }

    public boolean insert(Disk disk) {
        if (this.height >= ROWS)
            return false;

        this.disks[height] = disk;
        this.height++;
        return true;
    }

    public int size() {
        return this.height;
    }

    public boolean isFull() {
        return this.height == ROWS;
    }

    public Disk get(int rowIndex) {
        return this.disks[rowIndex];
    }
}
