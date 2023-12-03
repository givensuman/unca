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
		) {
			char currLetter = super.getPosition().toUpperCase().charAt(0);
			int currNumber = Integer.parseInt(super.getPosition().substring(1));
			
			int letterDistance = Math.abs(currLetter - letter);
			int numberDistance = Math.abs(currNumber - number);

			if (letterDistance == 2 && numberDistance == 1) return true;
			if (numberDistance == 2 && letterDistance == 1) return true;
		}
	
		return false;
	}
}
