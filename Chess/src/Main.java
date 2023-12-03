public class Main {

	public static void main(String[] args) {
		// uncomment to run some level3 test code
		// level3Test();
		ChessBoard board = new ChessBoard();
		Bishop b = new Bishop(ChessPiece.WHITE);
		Pawn p = new Pawn(ChessPiece.BLACK);
		board.place(b, "a7");
		board.place(p, "b6");
		System.out.println(board);

		try {
			board.move("a7", "c5");
		} catch (IllegalMoveException e) {
			System.out.println(e.getMessage());
		}

		System.out.println(board);
	}

	private static void level3Test() {
		ChessBoard board = new ChessBoard();
		Pawn p = new Pawn(ChessPiece.WHITE);
		Pawn p2 = new Pawn(ChessPiece.WHITE);// note same color!
		Pawn p3 = new Pawn(ChessPiece.BLACK);
		board.place(p2, "d2");
		board.place(p, "d1");
		board.place(p3, "d6");
		Knight wn = new Knight(ChessPiece.WHITE);
		Knight bn = new Knight(ChessPiece.BLACK);
		board.place(wn, "b2");
		board.place(bn, "c4");
		Bishop wb = new Bishop(ChessPiece.WHITE);
		Bishop bb = new Bishop(ChessPiece.BLACK);
		board.place(wb, "g3");
		board.place(bb, "f8");
		System.out.println(board);

		if (p.validMove("d2")) {
			System.out.println("no!");
		} else {
			System.out.println("so far, so good...");
		}
		try {
			board.move("d2", "d4");
			System.out.println("this should print...");
			board.move("d4", "d6"); // this should fail...
			System.out.println("this should not...");
		} catch (IllegalMoveException e) {
			System.out.println("Good! (if you saw \"this should print...\")");
		}
		System.out.println(board);
		ChessPiece captured = null;
		try {
			captured = board.move("d6", "d4");
		} catch (IllegalMoveException e) {
			System.out.println("good, this move was not valid");
		}
		if (captured != null) {
			System.out.println("Bad");
		}
		if (bb.validMove("d6")) {
			System.out.println("No, black bishop can't move to space occupied by black pawn");
		} else {
			System.out.println("correct!");
		}
		if (wb.validMove("d6")) {
			System.out.println("Good, white bishop can capture black pawn");
			System.out.println("making the move...");
			try {
				board.move("g3", "d6");
			} catch (IllegalMoveException e) {
				System.out.println("should not have seen this!");
			}
		} else {
			System.out.println("mistake somewhere because it should have been valid");
		}
		System.out.println("before knight tests...");
		System.out.println(board);
		// two quick knight tests
		try {
			if (wn.validMove("d1")) {
				System.out.println("No, white knight can't move where white bishop is");
			}
			captured = board.move("c4", "b2");
			if (captured == wn) {
				System.out.println("good, captured the white knight");
			}
		} catch (IllegalMoveException e) {
			System.out.println("darn, you shouldn't have seen this!");
		}
		System.out.println(board);
	}
}
