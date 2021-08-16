package com.wos.playgamesonline.model.tictactoe;

public class Square {
	
	private int id;
	private Mark mark;
	
	public Square(int id) {
		this.id = id;
		this.mark = Mark.EMPTY;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Mark getMark() {
		return mark;
	}
	
	public void setMark(Mark mark) {
		this.mark = mark;
	}
	
	public boolean isEmpty() {
		return mark == Mark.EMPTY;
	}
	
	@Override
	public String toString() {
		return mark.toString();
	}
}
