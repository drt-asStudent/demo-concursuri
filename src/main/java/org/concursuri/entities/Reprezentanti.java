package org.concursuri.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "reprezentanti")
public class Reprezentanti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idu", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idf", referencedColumnName = "id")
    private Firme firma;

    public Reprezentanti() {
    }

    public Reprezentanti(User user, Firme firma) {
        this.user = user;
        this.firma = firma;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Firme getFirma() {
        return firma;
    }

    public void setFirma(Firme firma) {
        this.firma = firma;
    }
}
