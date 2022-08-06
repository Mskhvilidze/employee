package com.fricke.personal.employee.controller;

import javax.persistence.*;

@Entity
@Table()
public class Likes {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String topicId;
    @Column(nullable = false)
    private String userID;

    public Likes(String topicId, String userID) {
        this.topicId = topicId;
        this.userID = userID;
    }

    public Likes(long id, String topicId, String userID) {
        this.topicId = topicId;
        this.userID = userID;
        this.id = id;
    }

    public Likes() {

    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getTopicId() {
        return topicId;
    }

    public String getUserID() {
        return userID;
    }
}
