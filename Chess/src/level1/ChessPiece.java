public abstract class ChessPiece {
	public static final int WHITE=0;
	public static final int BLACK=1;
	
	protected int color; //0 for white, 1 for black as seen above
	protected char abbreviation; //e.g. 'P' for Pawn, 'N' for Knight, 'B' for Bishop.
	protected ChessBoard board;
	protected String position; //e.g. "c5", or "g1", etc.
	
	protected ChessPiece(int color) {
		this.color=color;
		this.position=null;
	}
	
	protected ChessPiece(int color, String position) {
		this.color=color;
		this.position=position;
	}
	
	public void setBoard(ChessBoard b) {
		board=b;
	}
	
	public void setPosition(String code) {
		position=code;
	}
	
	public String getPosition() {
		return position;
	}
		
	public abstract boolean validMove(String code);
	
	public boolean isWhite() {
		return color==WHITE;
	}
	
	public String toString() {
		return (color==WHITE ? "W" : "B")+abbreviation;
	}
}
