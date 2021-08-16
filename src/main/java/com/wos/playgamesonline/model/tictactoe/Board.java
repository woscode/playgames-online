package com.wos.playgamesonline.model.tictactoe;

import java.util.Objects;

import org.springframework.stereotype.Component;


@Component
public class Board {
		
	private Square [] field = new Square[9];
	
	private static final int [][] WIN_COMBINATION = {
		{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, 
		{1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}
	};
	
	public Board() {
		for (int i = 0; i < field.length; i++)
			field[i] = new Square(i);
	}
	
	public void clear() {
		for (short i = 0; i < field.length; i++)
			field[i].setMark(Mark.EMPTY);
	}
	
	public boolean isFull() {
		for (short i = 0; i < field.length; i++)
			if (field[i].isEmpty())
				return false;
		return true;
	}
	
	public boolean isWinCombination() {
		for (short i = 0; i < WIN_COMBINATION.length; i++)
			if (!field[WIN_COMBINATION[i][0]].isEmpty() &&
				Objects.equals(field[WIN_COMBINATION[i][0]].getMark(), field[WIN_COMBINATION[i][1]].getMark()) &&
				Objects.equals(field[WIN_COMBINATION[i][1]].getMark(), field[WIN_COMBINATION[i][2]].getMark()))
				return true;
		return false;
	}
	
	
	public Square[][] get() {
		return new Square[][]  { {field[0], field[1], field[2]}, 
								 {field[3], field[4], field[5]}, 
								 {field[6], field[7], field[8]} };
	}
	

	public Square getSquare(int i) throws IllegalAccessException{
		if (isIllegalAcces(i))
			throw new IllegalAccessException();
		return field[i];
	}
	
	public boolean set(int i, Mark mark) {
		if (isIllegalAcces(i))
			return false;
		field[i].setMark(mark);
		return true;
	}
	
	public boolean setIfEmpty(int i, Mark mark) {
		if (isIllegalAcces(i) || !field[i].isEmpty())
			return false;
		field[i].setMark(mark);
		return true;
	}
	
	private boolean isIllegalAcces(int i) {
		return i < 0 || i >= field.length;
	}

	public int getRandomAvailable() {

		java.util.List<Integer> available = new java.util.ArrayList<>();

		for (int i = 0; i < field.length; i++)
			if (field[i].isEmpty())
				available.add(i);

		if (available.isEmpty())
			return 0;

		int randomPlace = new java.util.Random().nextInt(available.size());
		return available.get(randomPlace);
	}
}
