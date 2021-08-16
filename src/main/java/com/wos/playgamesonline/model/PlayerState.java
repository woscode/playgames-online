package com.wos.playgamesonline.model;

public enum PlayerState {
	
	NONE, PLAYING, WIN, LOSS, DRAW;
	
	public boolean isNone() {
		return this == NONE;
	}
	public boolean isPlaying() {
		return this == PLAYING;
	}
	public boolean isWin() {
		return this == WIN;
	}
	public boolean isLose() {
		return this == LOSS;
	}
	public boolean isDraw() {
		return this == DRAW;
	}
	public boolean isGameOver() {
		return this == WIN || this == LOSS ||  this == DRAW;
	}
}
