package com.advarra.badminton.dtos.tournament_dtos.ongoing;

public class PlayerDTO {
    private String name;
    private String gender;

    public PlayerDTO(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public PlayerDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
