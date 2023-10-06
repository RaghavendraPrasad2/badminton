package com.advarra.badminton.dtos.tournament_dtos.upcoming;

public class InitialCreateGroups {
    private Long id;
    private String name;

    public InitialCreateGroups() {
    }

    public InitialCreateGroups(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InitialCreateGroups{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
