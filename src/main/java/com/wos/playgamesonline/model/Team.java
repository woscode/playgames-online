package com.wos.playgamesonline.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


public class Team implements Serializable {

    private Long id;

    private String name;

    private Set<User> players;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<User> getPlayers() { return players; }
    public void setPlayers(Set<User> players) { this.players = players; }
}
