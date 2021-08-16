package com.wos.playgamesonline.model.tictactoe;


import com.wos.playgamesonline.model.PlayerState;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class XOPlayer {

    private Long id;

    private String username;
    private String password;

    private Mark mark;

    private PlayerState state;

    public XOPlayer() { }

    public XOPlayer(Long id, String username, String password, Mark mark) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mark = mark;
        setState(PlayerState.PLAYING);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public PlayerState getState() { return state; }
    public void setState(PlayerState state) { this.state = state; }

    public Mark getMark() { return mark; }

    public void setMark(Mark mark) { this.mark = mark; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XOPlayer user = (XOPlayer) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}

