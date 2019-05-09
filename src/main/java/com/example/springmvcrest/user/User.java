package com.example.springmvcrest.user;

import com.example.springmvcrest.reservation.Reservation;
import com.example.springmvcrest.userRole.UserRole;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "{com.example.springmvcrest.model.User.NotEmpty}")
    private String nickname;
    @NotEmpty(message = "{com.example.springmvcrest.model.User.NotEmpty}")
    @Email(message = "{com.example.springmvcrest.model.User.Email}")
    @Column(unique = true)
    private String email;
    @NotEmpty(message = "{com.example.springmvcrest.model.User.NotEmpty}")
    @Size(min = 4, message = "{com.example.springmvcrest.model.User.Size}")
    private String password;
    private boolean activated;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<UserRole> roles = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
