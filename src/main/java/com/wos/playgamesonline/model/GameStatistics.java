package com.wos.playgamesonline.model;

import javax.persistence.*;

@Entity
@Table(name = "game_statistics")
public class GameStatistics {

    private Integer score = 0;

    @EmbeddedId
    private GameStatisticsCompositeKey id;

    @ManyToOne
    @MapsId("playerId")
    @JoinColumn(name = "player_id")
    private User player;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    private Game game;

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public GameStatisticsCompositeKey getId() { return id; }
    public void setId(GameStatisticsCompositeKey id) { this.id = id; }
}
