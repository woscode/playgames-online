package com.wos.playgamesonline.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table (name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @Column(columnDefinition="TEXT")
    private String description;

    @OneToMany(mappedBy = "game")
    Set<GameStatistics> gameStatistics;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Set<GameStatistics> getGameStatistics() { return gameStatistics; }
    public void setGameStatistics(Set<GameStatistics> gameStatistics) {  this.gameStatistics = gameStatistics; }
}
