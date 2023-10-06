package com.advarra.badminton.dtos.tournament_dtos.ongoing;

public class MatchDTO {
    private Long id;
    private String match;

    public MatchDTO() {
    }

    public MatchDTO(Long id, String match) {
        this.id = id;
        this.match = match;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    @Override
    public String toString() {
        return "MatchDTO{" +
                "id=" + id +
                ", match='" + match + '\'' +
                '}';
    }
}
