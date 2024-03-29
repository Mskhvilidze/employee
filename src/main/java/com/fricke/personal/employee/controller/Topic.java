package com.fricke.personal.employee.controller;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table()
public class Topic {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String Identifier;
    private String name;
    private String description;
    private String countLikes;
    @ManyToOne(optional = false)
    @JoinColumn
    private Gamer gamer;

    public Topic() {
    }

    public Topic(String Identifier, String name, String description) {
        this.Identifier = Identifier;
        this.name = name;
        this.description = description;
    }

    public Gamer getGamer() {
        return gamer;
    }

    public void setGamer(Gamer gamer) {
        this.gamer = gamer;
    }

    public void setIdentifier(String id) {
        this.Identifier = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCountLikes(String countLikes) {
        this.countLikes = countLikes;
    }

    public String getIdentifier() {
        return Identifier;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCountLikes() {
        return countLikes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return Objects.equals(Identifier, topic.Identifier) &&
                Objects.equals(name, topic.name) &&
                Objects.equals(description, topic.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Identifier, name, description);
    }
}
