package edu.unca.csci201;

import java.util.HashMap;

public class ChessBoard {
	private ChessPiece[][] board;
	
	public ChessBoard() {
		board = new ChessPiece[8][8];
	}
	
	private boolean isValidBoardPosition(String code) {
		char letter = code.toUpperCase().charAt(0);
		char number = code.toUpperCase().charAt(1);
		
		if (
			(letter >= 'A' && letter <= 'H')
			&& (number >= 1 && number <= 8)
		) return true;
	
		return false;
	}
	
	private HashMap<String, Integer> extractPositionFromCode(String code) {
		int columnIndex = Character.getNumericValue(code.toUpperCase().charAt(0)) - 10;
		int rowIndex = Integer.parseInt(code.substring(1)) - 1;
		
		HashMap<String, Integer> positions = new HashMap<String, Integer>();
		positions.put("column", columnIndex);
		positions.put("row", rowIndex);
		
		return positions;
	}
	
	public void place(ChessPiece piece, String code) {
		HashMap<String, Integer> position = extractPositionFromCode(code);
		
		if (isValidBoardPosition(code)) {
			this.board[position.get("column")][position.get("row")] = piece;
		}
	}

	// returns the captured ChessPiece or null
	public ChessPiece move(String codeStart, String codeEnd) throws IllegalMoveException {
		if (!isValidBoardPosition(codeStart)) throw new IllegalMoveException("Starting position is not valid.");
		if (!isValidBoardPosition(codeEnd)) throw new IllegalMoveException("Ending position is not valid.");
		
		HashMap<String, Integer> piecePosition = extractPositionFromCode(codeStart);
		ChessPiece piece = this.board[piecePosition.get("column")][piecePosition.get("row")];
		
		if (!piece.validMove(codeEnd)) throw new IllegalMoveException();
		
		HashMap<String, Integer> capturedPiecePosition = extractPositionFromCode(codeEnd);
		ChessPiece capturedPiece = this.board[capturedPiecePosition.get("column")][capturedPiecePosition.get("row")];
		
		this.board[capturedPiecePosition.get("column")][capturedPiecePosition.get("row")] = piece;
		this.board[piecePosition.get("column")][piecePosition.get("row")] = null;
		
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

		for (int i=0; i<board.length; i++) {
			out+=(8-i)+" | ";
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
