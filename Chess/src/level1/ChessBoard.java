import java.util.HashMap;

public class ChessBoard {
	private ChessPiece[][] board;
	
	public ChessBoard() {
		board = new ChessPiece[8][8];
	}
	
	private boolean isValidBoardPosition(String code) {
		char letter = code.toUpperCase().charAt(0);
		int number = Integer.parseInt(code.substring(1));
		
		if (
			(letter >= 'A' && letter <= 'H')
			&& (number >= 1 && number <= 8)
		) return true;
	
		return false;
	}
	
	/**
	 * @param code
	 * @return positional indices of row and column as a HashMap with keys "row" and "column"
	 */
	public HashMap<String, Integer> extractPositionFromCode(String code) {
		int columnIndex = Character.getNumericValue(code.toUpperCase().charAt(0)) - 10;
		int rowIndex = Integer.parseInt(code.substring(1)) - 1;
		
		HashMap<String, Integer> positions = new HashMap<String, Integer>();
		positions.put("column", columnIndex);
		positions.put("row", rowIndex);

		// System.out.println("You're in extractPositionFromCode");
		// System.out.println("code="+code);
		// System.out.println("rowIndex="+rowIndex + ", columnIndex="+columnIndex);
		
		return positions;
	}
	
	public void place(ChessPiece piece, String code) {
		HashMap<String, Integer> position = extractPositionFromCode(code);
		
		if (isValidBoardPosition(code)) {
			this.board[position.get("row")][position.get("column")] = piece;
			piece.setPosition(code);
		}
	}

	// returns the captured ChessPiece or null
	public ChessPiece move(String codeStart, String codeEnd) throws IllegalMoveException {
		if (!isValidBoardPosition(codeStart)) throw new IllegalMoveException("Starting position [" + codeStart + "] is not valid.");
		if (!isValidBoardPosition(codeEnd)) throw new IllegalMoveException("Ending position [" + codeEnd + "] is not valid.");
		
		HashMap<String, Integer> piecePosition = extractPositionFromCode(codeStart);
		ChessPiece piece = this.board[piecePosition.get("row")][piecePosition.get("column")];
		
		if (!piece.validMove(codeEnd)) throw new IllegalMoveException();
		
		HashMap<String, Integer> capturedPiecePosition = extractPositionFromCode(codeEnd);
		ChessPiece capturedPiece = this.board[capturedPiecePosition.get("row")][capturedPiecePosition.get("column")];
		
		this.board[capturedPiecePosition.get("row")][capturedPiecePosition.get("column")] = piece;
		piece.setPosition(codeEnd);
		this.board[piecePosition.get("row")][piecePosition.get("column")] = null;
		
		return capturedPiece;
	}
			
	public String toString() {
		String out="    ";
		for (char c='a'; c<='h'; c++) {
			out+=c+"    ";
		}
		out+="\n ";
		
		for (int k=0; k<41; k++) {
			out+="-";
		}
		out+="\n";

		for (int i=board.length - 1; i>=0; i--) {
			out+=(i+1)+" | ";
			for (int j=0; j<board[i].length; j++) {
				ChessPiece piece = board[i][j];
				if (piece==null) {
					out+="  ";
				} else {
					out+=piece;
				}
				out+=" | ";
			}
			out+="\n ";
			for (int k=0; k<41; k++) {
				out+="-";
			}
			out+="\n";
		}
		return out;
	}
}
