package com.wos.playgamesonline.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 4048798961366546485L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)  @PrimaryKeyJoinColumn  private Player player;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String username;

    private String password;

    @NotNull
    @NotEmpty
    private String nickname;

    @NotNull
    private LocalDateTime creationDate;

    @Transient
    private String passwordConfirm;

    @ManyToMany
    private Set<Role> roles;

    @Transient
    private PlayerState state;

    public User() { }

    public User(Long id, String username, String nickname, String password, LocalDateTime creationDate) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.creationDate = creationDate;
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

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public LocalDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate;  }
    
    public PlayerState getState() { return state; }
    public void setState(PlayerState state) { this.state = state; }

    public Set<Role> getRoles() {  return roles;  }
    public void setRoles(Set<Role> roles) { this.roles = roles; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
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
                ", creationDate=" + creationDate +
                '}';
    }
}

