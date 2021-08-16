package com.wos.playgamesonline.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GameStatisticsCompositeKey implements Serializable {

    @Column (name = "game_id")
    private Long gameId;

    @Column (name = "user_id")
    private Long userId;

    public Long getGameId() { return gameId; }
    public void setGameId(Long gameId) { this.gameId = gameId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
