package com.wos.playgamesonline.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "game_pvp_session")
public class GamePvpSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String title;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player_one_id")
    private User playerOne;

    @ManyToOne
    @JoinColumn(name = "player_two_id")
    private User playerTwo;

    private Integer scoreOne = 0;
    private Integer scoreTwo = 0;

    private Integer totalPlayed = 0;

    private LocalDateTime started;
    private LocalDateTime finished;

    @Transient
    private String address;

    @Transient
    private GameSessionState gameSessionState;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }

    public User getPlayerOne() { return playerOne; }
    public void setPlayerOne(User playerOne) { this.playerOne = playerOne; }

    public User getPlayerTwo() { return playerTwo; }
    public void setPlayerTwo(User playerTwo) { this.playerTwo = playerTwo; }

    public Integer getScoreOne() { return scoreOne; }
    public void setScoreOne(Integer scoreOne) { this.scoreOne = scoreOne; }

    public void addScoreOne(int value) {

        if (value < 0)
            this.subScoreOne(-value);
        else if (Integer.MAX_VALUE - value >= scoreOne)
            this.scoreOne += value;
    }

    public void subScoreOne(int value) {

        if (value < 0)
            this.addScoreOne(-value);
        else if (Integer.MIN_VALUE + value <= scoreOne)
            this.scoreOne -= value;
    }

    public Integer getScoreTwo() { return scoreTwo; }
    public void setScoreTwo(int scoreTwo) { this.scoreTwo = scoreTwo; }

    public void addScoreTwo(int value) {

        if (value < 0)
            this.subScoreTwo(-value);
        else if (Integer.MAX_VALUE - value >= scoreTwo)
            this.scoreTwo += value;
    }

    public void subScoreTwo(int value) {

        if (value < 0)
            this.addScoreTwo(-value);
        else if (Integer.MIN_VALUE + value <= scoreTwo)
            this.scoreTwo -= value;
    }

    public void resetScore() {
        scoreOne = 0;
        scoreTwo = 0;
    }

    public Integer getTotalPlayed() { return totalPlayed; }
    public void setTotalPlayed(Integer totalPlayed) { this.totalPlayed = totalPlayed; }

    public LocalDateTime getStarted() { return started; }
    public void setStarted(LocalDateTime started) { this.started = started; }

    public LocalDateTime getFinished() { return finished; }
    public void setFinished(LocalDateTime finished) { this.finished = finished; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public GameSessionState getGameSessionState() { return gameSessionState; }
    public void setGameSessionState(GameSessionState gameSessionState) { this.gameSessionState = gameSessionState; }
}
