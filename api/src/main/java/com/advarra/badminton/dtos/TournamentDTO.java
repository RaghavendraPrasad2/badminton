package com.advarra.badminton.dtos;


import com.advarra.badminton.model.UserCredentials;
import java.sql.Timestamp;

public class TournamentDTO {
    private Long id;

    private UserDTO user;
    private String name;
    private Timestamp startTime;
    private Timestamp endTime;
    private String description;

    public TournamentDTO() {
    }

    public TournamentDTO(Long id, UserDTO user, String name, Timestamp startTime, Timestamp endTime, String description) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TournamentDTO{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", description='" + description + '\'' +
                '}';
    }
}
