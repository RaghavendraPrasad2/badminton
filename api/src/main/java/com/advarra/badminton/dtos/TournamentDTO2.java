package com.advarra.badminton.dtos;

public class TournamentDTO2 {
    private Long id;

    public TournamentDTO2(Long id) {
        this.id = id;
    }

    public TournamentDTO2() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TournamentDTO2{" +
                "id=" + id +
                '}';
    }
}
