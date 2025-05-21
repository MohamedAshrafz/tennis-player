package io.spring.tennis_player.models;

import jakarta.persistence.*;

import java.sql.Date;

//@Table(name = "Player") // Not needed unless different Table name is required
@Entity
@NamedQuery(
        name = QueryNames.GET_ALL_PLAYERS,
        query = "SELECT p FROM Player p"
)
@NamedQuery(
        name = QueryNames.GET_PLAYERS_WITH_NATIONALITY,
        query = "SELECT p FROM Player p WHERE p.nationality = ?1"
)
public class Player {

    @Id
//    @GeneratedValue // If you want to add the ids yourself you must remove the @GeneratedValue
    private int id;

    private String name;

//    @Column(name="nationality") // Not needed unless different Column name is required
    private String nationality;

    private Date birthDate;

    private int titles;

    public Player() {
    }

    public Player(int id, String name, String nationality, Date birthDate, int titles) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.titles = titles;
    }

    public Player(String name, String nationality, Date birthDate, int titles) {
        super();
        this.name = name;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.titles = titles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getTitles() {
        return titles;
    }

    public void setTitles(int titles) {
        this.titles = titles;
    }

    @Override
    public String toString() {
        return "\nPlayer [id= " + id + ", name= " + name + ", nationality= " + nationality + ", birthDate= " + birthDate
                + ", titles= " + titles + "]";
    }
}
