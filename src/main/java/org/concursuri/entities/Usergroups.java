package org.concursuri.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usergroups")
public class Usergroups {
    @Column(name = "usergroup", length = 40)
    public String usergroup;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "username", length = 40)
    private String username;

    public Usergroups(){

    }

    public Usergroups(String username, String usergroup) {
        this.username = username;
        this.usergroup = usergroup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsergroup() {
        return usergroup;
    }

    public void setUsergroup(String usergroup) {
        this.usergroup = usergroup;
    }
}