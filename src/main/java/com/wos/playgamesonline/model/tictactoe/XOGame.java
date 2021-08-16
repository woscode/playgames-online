package com.wos.playgamesonline.model.tictactoe;

import com.wos.playgamesonline.model.GameSessionState;
import com.wos.playgamesonline.model.PlayerState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class XOGame {

    final Logger logger = LoggerFactory.getLogger(XOGame.class);

    private int id;

    private GameSessionState state = GameSessionState.NEW;

    Board board = new Board();

    private XOPlayer playerOne;
    private XOPlayer playerTwo;

    private boolean isCurrentPlayerMove = true;
    private boolean isPlayerOneMoveFirst = true;

    public XOGame() { }

    public XOGame(XOPlayer playerX, XOPlayer playerO) {
        setPlayers(playerX, playerO);
        setGameState(GameSessionState.PLAYING);
    }

    public boolean move(int position) {
        return move(position, currentPlayer().getMark());
    }

    public boolean move(int position, Mark mark) {

        if (getGameState().isPlaying() &&
                (currentPlayer().getMark() == mark) &&
                board.setIfEmpty(position, mark)) {

            logger.info("Player {} move on {}", currentPlayer().getMark(), position);

            if (board.isWinCombination()) {
                logger.info("Player {} win", currentPlayer().getMark());
                currentPlayer().setState(PlayerState.WIN);
                nextPlayer().setState(PlayerState.LOSS);
                setGameState(GameSessionState.FINISHED);

            } else if(board.isFull()) {
                logger.info("Draw");
                playerOne.setState(PlayerState.DRAW);
                playerTwo.setState(PlayerState.DRAW);
                setGameState(GameSessionState.FINISHED);
            }
            else
                isCurrentPlayerMove = !isCurrentPlayerMove;

            return true;
        }
        return false;
    }

    public void randomMove() {
        logger.info("PC is move");
        move(board.getRandomAvailable(), currentPlayer().getMark());
    }

    public Board board() {
        return board;
    }

    public void swapMarks() {

        playerOne.setMark(playerTwo.getMark());
        playerTwo.setMark(playerOne.getMark() == Mark.X ? Mark.O : Mark.X);
    }

    public void setPlayers(XOPlayer playerX, XOPlayer playerO) {

        playerOne = playerX;
        playerOne.setMark(Mark.X);

        playerTwo = playerO;
        playerTwo.setMark(Mark.O);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public GameSessionState getGameState() { return state; }
    public void setGameState(GameSessionState state) { this.state = state; }

    public XOPlayer currentPlayer() {
        return isCurrentPlayerMove ? playerOne : playerTwo;
    }
    public XOPlayer nextPlayer() {
        return isCurrentPlayerMove ? playerTwo : playerOne;
    }

    public boolean isPlayerMoveFirst() {  return isPlayerOneMoveFirst; }

    public Collection<XOPlayer> players() {
        return new ArrayList<XOPlayer>() {{ add(playerOne); add(playerTwo); }};
    }

    public void restart() {

        logger.info("Restarting");

        setGameState(GameSessionState.PLAYING);
        isCurrentPlayerMove = isPlayerOneMoveFirst;
        playerOne.setState(PlayerState.PLAYING);
        playerTwo.setState(PlayerState.PLAYING);
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
        XOGame other = (XOGame) obj;
        return Objects.equals(getId(), other.getId());
    }

    @Override
    public String toString() {
        return "TicTacToeGame [getId()=" + getId() + "]";
    }
}
