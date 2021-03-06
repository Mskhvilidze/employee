package com.fricke.personal.employee.controller;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table()
public class Gamer {
    @Id
    @Column(nullable = false, unique = true)
    private String nickname;
    private String firstname;
    private String lastname;
    private String password;
    private String eMail;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
    private Address address;

    public Gamer(String nickname, String password, String firstname, String lastname, String eMail) {
        this.nickname = nickname;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.eMail = eMail;
    }

    public Gamer(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public Gamer() {

    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getNickname() {
        return nickname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPassword() {
        return password;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gamer gamer = (Gamer) o;
        return Objects.equals(nickname, gamer.nickname) &&
                Objects.equals(firstname, gamer.firstname) &&
                Objects.equals(lastname, gamer.lastname) &&
                Objects.equals(password, gamer.password) &&
                Objects.equals(eMail, gamer.eMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, firstname, lastname, password, eMail);
    }
}
