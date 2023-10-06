package com.advarra.badminton.dtos;

import com.advarra.badminton.model.Tournament;

import java.util.List;

public class TournamentCategoriesDTO {
    private List<TournamentDTO> onGoingTournaments;
    private List<TournamentDTO> upcomingTournaments;

    private List<TournamentDTO> recentTournaments;

    public TournamentCategoriesDTO() {
    }

    public TournamentCategoriesDTO(List<TournamentDTO> onGoingTournaments, List<TournamentDTO> upcomingTournaments, List<TournamentDTO> recentTournaments) {
        this.onGoingTournaments = onGoingTournaments;
        this.upcomingTournaments = upcomingTournaments;
        this.recentTournaments = recentTournaments;
    }

    public List<TournamentDTO> getOnGoingTournaments() {
        return onGoingTournaments;
    }

    public void setOnGoingTournaments(List<TournamentDTO> onGoingTournaments) {
        this.onGoingTournaments = onGoingTournaments;
    }

    public List<TournamentDTO> getUpcomingTournaments() {
        return upcomingTournaments;
    }

    public void setUpcomingTournaments(List<TournamentDTO> upcomingTournaments) {
        this.upcomingTournaments = upcomingTournaments;
    }

    public List<TournamentDTO> getRecentTournaments() {
        return recentTournaments;
    }

    public void setRecentTournaments(List<TournamentDTO> recentTournaments) {
        this.recentTournaments = recentTournaments;
    }
}
