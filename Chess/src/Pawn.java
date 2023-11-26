public class Pawn extends ChessPiece {
	
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
		
		if (
			(letter >= 'A' && letter <= 'H')
			&& (number >= 1 && number <= 8)
		) return true;
	
		return false;
	}
}
