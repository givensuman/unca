package edu.unca.csci201;

public class IllegalMoveException extends Exception {
	IllegalMoveException(String message) {
		super(message);
	}
	
	IllegalMoveException() {
		super("The move you attempted is not possible.");
	}
}
