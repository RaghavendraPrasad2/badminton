package com.advarra.badminton.dtos.tournament_dtos;

public class TournamentDataDTO {
    private String leagueName;
    private String startDate;
    private String endDate;
    private String description;
    private String conductorEmail;

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getConductorEmail() {
        return conductorEmail;
    }

    public void setConductorEmail(String conductorEmail) {
        this.conductorEmail = conductorEmail;
    }

    public TournamentDataDTO(String leagueName, String startDate, String endDate, String description, String conductorEmail) {
        this.leagueName = leagueName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.conductorEmail = conductorEmail;
    }

    public TournamentDataDTO() {
    }

    @Override
    public String toString() {
        return "TournamentDataDTO{" +
                "leagueName='" + leagueName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", description='" + description + '\'' +
                ", conductorEmail='" + conductorEmail + '\'' +
                '}';
    }
}
