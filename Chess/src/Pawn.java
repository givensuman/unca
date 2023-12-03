import java.util.HashMap;

public class Pawn extends ChessPiece {

	private boolean hasMoved = false;

	Pawn(int color) {
		super(color);
		super.abbreviation = 'P';
	}

	Pawn(int color, String position) {
		super(color, position);
		super.abbreviation = 'P';
	}

	public boolean validMove(String code) {
		char letter = code.toUpperCase().charAt(0);
		int number = Integer.parseInt(code.substring(1));

		if ((letter >= 'A' && letter <= 'H')
				&& (number >= 1 && number <= 8)) {
			char currLetter = super.getPosition().toUpperCase().charAt(0);
			int currNumber = Integer.parseInt(super.getPosition().substring(1));

			HashMap<String, Integer> landingPosition = ChessBoard.extractPositionFromCode(code);

			if (currLetter == letter
					&& ChessBoard.board[landingPosition.get("row")][landingPosition.get("column")] == null) {
				if (number == currNumber + 1 && super.color == 0)
					return true;

				if (number == currNumber - 1 && super.color == 1)
					return true;

				if (number == currNumber + 2 && super.color == 0 && this.hasMoved == false)
					return true;

				if (number == currNumber - 2 && super.color == 1 && this.hasMoved == false)
					return true;

			} else if (ChessBoard.board[landingPosition.get("row")][landingPosition.get("column")] != null
					&& Math.abs(letter - currLetter) == 1) {

				if (number == currNumber + 1 && super.color == 0)
					return true;

				if (number == currNumber - 1 && super.color == 1)
					return true;
			}
		}

		return false;
	}

	public void setHasMoved() {
		this.hasMoved = true;
	}
}
