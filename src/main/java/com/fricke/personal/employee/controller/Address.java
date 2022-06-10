package com.fricke.personal.employee.controller;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table()
public class Address {
    @Id
    @Column(nullable = false, unique = true)
    private String address;
    private String plz;
    private String loc;
    private String number;

    public Address() {
    }

    public Address(String address, String plz, String loc, String number) {
        this.address = address;
        this.plz = plz;
        this.loc = loc;
        this.number = number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public String getPlz() {
        return plz;
    }

    public String getLoc() {
        return loc;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(address, address1.address) &&
                Objects.equals(plz, address1.plz) &&
                Objects.equals(loc, address1.loc) &&
                Objects.equals(number, address1.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, plz, loc, number);
    }
}
