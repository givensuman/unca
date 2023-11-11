enum Player {
    RED, BLACK, NULL
}

public class Disk {
    private Player color;

    Disk(Player color) {
        this.color = color;
    }

    public boolean isRed() {
        return this.color == Player.RED;
    }

    public boolean isBlack() {
        return this.color == Player.BLACK;
    }

    public boolean isNull() {
        return this.color == Player.NULL;
    }

    public String toString() {
        switch (this.color) {
            case RED:
                return " R ";
            case BLACK:
                return " B ";
            case NULL:
                return "   ";
            default:
                return "   ";
        }
    }
}
