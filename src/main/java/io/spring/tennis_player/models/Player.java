package io.spring.tennis_player.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

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
//    @GeneratedValue(strategy = GenerationType.UUID) // If you want to add the ids yourself you must remove the @GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

//    @Column(name="nationality") // Not needed unless different Column name is required
    private String nationality;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    private int titles;

    public Player() {
    }

    public Player(String name, String nationality, LocalDate birthDate, int titles) {
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
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
