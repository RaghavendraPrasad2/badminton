package com.advarra.badminton.dtos.tournament_dtos.ongoing;

import com.advarra.badminton.dtos.tournament_dtos.recent.RecentTournamentDTO;

public class OngoingTournamentDTO {
    private RecentTournamentDTO recentTournamentDTO;


    public RecentTournamentDTO getRecentTournamentDTO() {
        return recentTournamentDTO;
    }

    public void setRecentTournamentDTO(RecentTournamentDTO recentTournamentDTO) {
        this.recentTournamentDTO = recentTournamentDTO;
    }
}
