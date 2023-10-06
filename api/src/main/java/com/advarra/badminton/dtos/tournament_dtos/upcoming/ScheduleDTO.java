package com.advarra.badminton.dtos.tournament_dtos.upcoming;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ScheduleDTO {
    private Map<String, List<String>> matches = new HashMap<>();
    private String tournamentId;
    private String clearData;

    public ScheduleDTO() {
    }

    public ScheduleDTO(Map<String, List<String>> matches, String tournamentId, String clearData) {
        this.matches = matches;
        this.tournamentId = tournamentId;
        this.clearData = clearData;
    }

    public Map<String, List<String>> getMatches() {
        return matches;
    }

    public void setMatches(Map<String, List<String>> matches) {
        this.matches = matches;
    }

    public String getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getClearData() {
        return clearData;
    }

    public void setClearData(String clearData) {
        this.clearData = clearData;
    }

    @Override
    public String toString() {
        return "ScheduleDTO{" +
                "matches=" + matches +
                ", tournamentId='" + tournamentId + '\'' +
                ", clearData=" + clearData +
                '}';
    }
}
