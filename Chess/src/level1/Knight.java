public class Knight extends ChessPiece {

	Knight(int color) {
		super(color);
		super.abbreviation = 'N';
	}
	
	Knight(int color, String position) {
		super(color, position);
		super.abbreviation = 'N';
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
