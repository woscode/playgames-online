package com.wos.playgamesonline.model.tictactoe;

import com.wos.playgamesonline.model.GamePvpSession;
import com.wos.playgamesonline.model.GameSessionState;
import com.wos.playgamesonline.model.PlayerState;
import com.wos.playgamesonline.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;


public class TicTacToeGame extends GamePvpSession {
	
	final Logger logger = LoggerFactory.getLogger(TicTacToeGame.class);
	
	Board board = new Board();

	private Mark playerOneMark;
	private Mark playerTwoMark;

	private boolean isPlayerOneMove = true;
	private boolean isPlayerOneMoveFirst = true;
	
	public TicTacToeGame() {setGameSessionState(GameSessionState.NEW); }
	
	public TicTacToeGame(User playerX, User playerO, boolean isPlayerOneMoveFirst) {
		setPlayerOne(playerX); setPlayerTwo(playerO);
		getPlayerOne().setState(PlayerState.PLAYING);
		getPlayerTwo().setState(PlayerState.PLAYING);
		setGameSessionState(GameSessionState.PLAYING);
		this.isPlayerOneMoveFirst = isPlayerOneMoveFirst;
	}

	public boolean move(int position) {
		return move(position, currentPlayerMark());
	}

	public boolean move(int position, Mark mark) {

		if (getGameSessionState().isPlaying() &&
			(currentPlayerMark() == mark) &&
			board.setIfEmpty(position, mark)) {

			logger.info("Player {} move on {}", currentPlayerMark(), position);

			if (board.isWinCombination()) {

				logger.info("Player {} win", currentPlayerMark());

				currentPlayer().setState(PlayerState.WIN);
				if (isPlayerOneMove)
					addScoreOne(1);
				else
					addScoreTwo(1);
				nextPlayer().setState(PlayerState.LOSS);
				setGameSessionState(GameSessionState.FINISHED);
				
			} else if(board.isFull()) {

				logger.info("Draw");

				getPlayerOne().setState(PlayerState.DRAW);
				getPlayerTwo().setState(PlayerState.DRAW);
				setGameSessionState(GameSessionState.FINISHED);
			}
			else
				isPlayerOneMove = !isPlayerOneMove;
			
			return true;
		}
		return false;
	}

	public void randomMove() {
		logger.info("PC is move");
		move(board.getRandomAvailable(), currentPlayerMark());
	}

	public Board board() {
		return board;
	}
	
	public void swapMarks() {

		playerOneMark = playerTwoMark;
		playerTwoMark = playerOneMark== Mark.X ? Mark.O : Mark.X;
	}


	public Mark currentPlayerMark() {
		return isPlayerOneMove ? playerOneMark : playerTwoMark;
	}
	public Mark nextPlayerMark() {
		return isPlayerOneMove ? playerTwoMark : playerOneMark;
	}

	public User currentPlayer() {
		return isPlayerOneMove ? getPlayerOne() : getPlayerTwo();
	}
	public User nextPlayer() {
		return isPlayerOneMove ? getPlayerTwo() : getPlayerOne();
	}

	public boolean isPlayerMoveFirst() {  return isPlayerOneMoveFirst; }

	public void restart() {

		logger.info("Restarting");

		setGameSessionState(GameSessionState.PLAYING);
		isPlayerOneMove = isPlayerOneMoveFirst;
		getPlayerOne().setState(PlayerState.PLAYING);
		getPlayerTwo().setState(PlayerState.PLAYING);
		board.clear();
	}

	public void restart(boolean isPlayerOneMoveFirst) {

		this.isPlayerOneMoveFirst = isPlayerOneMoveFirst;
		restart();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		TicTacToeGame other = (TicTacToeGame) obj;
		return Objects.equals(getId(), other.getId());
	}

	@Override
	public String toString() {
		return "TicTacToeGame [getId()=" + getId() + "]";
	}
}
