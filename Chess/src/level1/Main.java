public class Main {

	public static void main(String[] args) {
		// uncomment to run some level1 test code
		level1Test();

		// uncomment to run some level2 test code
		//level2Test();

		// uncomment to run some level3 test code
		//level3Test();
	}
	
	private static void level1Test() {
		ChessBoard board = new ChessBoard();
		Pawn p = new Pawn(ChessPiece.WHITE);
		System.out.println(p.getPosition()); // null
		Pawn p2 = new Pawn(ChessPiece.BLACK);
		board.place(p2,"d5");
		board.place(p,"d4");
		System.out.println(p.getPosition());//d4
		System.out.println(board);
				
		ChessPiece captured=null;
		try {
			captured = board.move("d4","d5");
			if (captured.equals(p2)) { // uses Object.equals method
				System.out.println("Good! Captured "+captured); //BP
			}
		} catch (IllegalMoveException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(board);
		try {
			board.move("d5","i5");//illegal! (column "i" does not exist)
		} catch (IllegalMoveException e) {
			System.out.println(e.getMessage()); //ERROR: illegal move attempted
		}
		
		try {
			// this is fine for a pawn in Level 1:
			board.move("d5", "h1");
		} catch (IllegalMoveException e) {
			System.out.println("Shouldn't see this.");
		}
		System.out.println(board);
		// test a Knight
		Knight n = new Knight(ChessPiece.WHITE);
		board.place(n, "a8");
		try {
			// this is fine for a knight in Level 1:
			captured = board.move("a8", "h1");
			System.out.println("captured "+captured);
		} catch (IllegalMoveException e) {
			System.out.println("Shouldn't see this.");
		}
		System.out.println(board);
	}

// 	private static void level2Test() {
// 		ChessBoard board = new ChessBoard();
// 		Pawn p = new Pawn(ChessPiece.WHITE);
// 		System.out.println(p.getPosition()); // null
// 		Pawn p2 = new Pawn(ChessPiece.WHITE);// note same color!
// 		board.place(p2,"d5");
// 		board.place(p,"d4");
// 		ChessPiece captured=null;
// 		try {
// 			board.move("d5", "d4");
// 		} catch (IllegalMoveException e) {
// 			System.out.println("correctly caught invalid move");
// 		}
// 		try {
// 			captured = board.move("d4", "d5"); // can capture same color!
// 			if (captured==p2) {
// 				System.out.println("correct (captured is p2!)");
// 			}
// 		} catch (IllegalMoveException e) {
// 			System.out.println("shouldn't see this message");
// 		}
// 		if (p.validMove("e5") || p.validMove("c5") || p.validMove("d5")) {
// 			System.out.println("Incorrect!");
// 		} else {
// 			System.out.println("These tests passed");
// 		}
// 		System.out.println(board);
// 		Knight n = new Knight(ChessPiece.WHITE);
// 		board.place(n, "c7");
// 		System.out.println(board);
// 		if (n.validMove("b7") || n.validMove("c5") || n.validMove("d4")) {
// 			System.out.println("knight valid move returned incorrect value");
// 		} else {
// 			System.out.println("passed some knight validMove tests");
// 		}
// 		try {
// 			board.move("c7", "b5");
// 			System.out.println("next.");
// 			board.move("b5","c3");
// 			System.out.println("next..");
// 			captured = board.move("c3", "d5");
// 			if (captured!=p) {
// 				System.out.println("knight didn't capture that pawn");
// 			}
// 		} catch (IllegalMoveException e) {
// 			System.out.println("something went wrong");
// 		}
// 		System.out.println(board);
// 	}

// 	private static void level3Test() {
// 		ChessBoard board = new ChessBoard();
// 		Pawn p = new Pawn(ChessPiece.WHITE);
// 		Pawn p2 = new Pawn(ChessPiece.WHITE);// note same color!
// 		Pawn p3 = new Pawn(ChessPiece.BLACK);
// 		board.place(p2,"d2");
// 		board.place(p,"d1");
// 		board.place(p3, "d6");
// 		Knight wn = new Knight(ChessPiece.WHITE);
// 		Knight bn = new Knight(ChessPiece.BLACK);
// 		board.place(wn, "c2");
// 		board.place(bn, "b4");
// //		Bishop wb = new Bishop(ChessPiece.WHITE);
// //		Bishop bb = new Bishop(ChessPiece.BLACK);
// 		board.place(wb,"g1");
// 		board.place(bb,"h8");
// 		System.out.println(board);
		
// 		if (p.validMove("d2")) {
// 			System.out.println("no!");
// 		} else {
// 			System.out.println("so far, so good...");
// 		}
// 		try {
// 			board.move("d2","d4");
// 			System.out.println("this should print...");
// 			board.move("d4", "d6"); // this should fail...
// 			System.out.println("this should not...");
// 		} catch (IllegalMoveException e) {
// 			System.out.println("Good! (if you saw \"this should print...\")");
// 		}
// 		System.out.println(board);
// 		ChessPiece captured=null;
// 		try {
// 			captured = board.move("d6", "d4");
// 		} catch (IllegalMoveException e) {
// 			System.out.println("oops, this move should have worked!");
// 		}
// 		if (captured==p2) {
// 			System.out.println("Good, captured p2");
// 		}
// 		if (bb.validMove("d6")) {
// 			System.out.println("No, black bishop can't move to space occupied by black pawn");
// 		} else {
// 			System.out.println("correct!");
// 		}
// 		if (wb.validMove("d4")) {
// 			System.out.println("Good, white bishop can capture black pawn");
// 			System.out.println("making the move...");
// 			try {
// 				board.move("g1", "d4");
// 			} catch (IllegalMoveException e) {
// 				System.out.println("should not have seen this!");
// 			}
// 		}
// 		// two quick knight tests
// 		try {
// 			if (wn.validMove("d4")) {
// 				System.out.println("No, white knight can't move where white bishop is");
// 			}
// 			captured = board.move("b4", "c2");
// 			if (captured==wn) {
// 				System.out.println("good, captured the white knight");
// 			}
// 		} catch (IllegalMoveException e) {
// 			System.out.println("darn, you shouldn't have seen this!");
// 		}
// 		System.out.println(board);
// 	}

}
