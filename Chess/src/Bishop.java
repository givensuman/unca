import java.util.HashMap;

public class Bishop extends ChessPiece {
    Bishop(int color) {
        super(color);
        super.abbreviation = 'B';
    }

    Bishop(int color, String position) {
        super(color, position);
        super.abbreviation = 'B';
    }

    public boolean validMove(String code) {
        char letter = code.toUpperCase().charAt(0);
        int number = Integer.parseInt(code.substring(1));

        if ((letter >= 'A' && letter <= 'H')
                && (number >= 1 && number <= 8)) {
            char currLetter = super.getPosition().toUpperCase().charAt(0);
            int currNumber = Integer.parseInt(super.getPosition().substring(1));

            if (Math.abs(currNumber - number) != Math.abs(currLetter - letter))
                return false;

            HashMap<String, Integer> landingPosition = ChessBoard.extractPositionFromCode(code);
            ChessPiece landingPiece = ChessBoard.board[landingPosition.get("row")][landingPosition.get("column")];
            if (landingPiece != null && landingPiece.color == super.color)
                return false;

            int whileIndex = 1;
            while (whileIndex < Math.abs(currNumber - number)) {

                HashMap<String, Integer> currPosition = ChessBoard.extractPositionFromCode(super.getPosition());

                System.out.println("You're in Bishop.isValidMove");
                System.out.println("row=" + currPosition.get("row") + ", col=" + currPosition.get("column"));

                if (letter < currLetter) {
                    if (number < currNumber) {
                        if (ChessBoard.board[currPosition.get("row") - whileIndex][currPosition.get("column")
                                - whileIndex] != null)
                            return false;
                    } else if (number > currNumber) {
                        if (ChessBoard.board[currPosition.get("row") + whileIndex][currPosition.get("column")
                                - whileIndex] != null)
                            return false;
                    }
                } else if (letter > currLetter) {
                    if (number < currNumber) {
                        if (ChessBoard.board[currPosition.get("row") - whileIndex][currPosition.get("column")
                                + whileIndex] != null)
                            return false;
                    } else if (number > currNumber) {
                        if (ChessBoard.board[currPosition.get("row") + whileIndex][currPosition.get("column")
                                + whileIndex] != null)
                            return false;
                    }
                }

                whileIndex++;
            }
        }

        return true;
    }
}
