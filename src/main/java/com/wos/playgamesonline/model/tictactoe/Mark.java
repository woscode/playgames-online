package com.wos.playgamesonline.model.tictactoe;

public enum Mark {
	
	EMPTY ('\0'),
	X ('x'), 
	O ('o');
	
	private char symbol;
	
	Mark (char symbol) { 
		this.symbol = symbol; 
	}
	
	Mark () { 
		this('\0'); 
	}
	
	@Override
	public String toString() {
		return String.valueOf(symbol);
	}
}
