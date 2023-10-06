package com.advarra.badminton.dtos.tournament_dtos.upcoming;

public class Player2DTO {
    private String playerName;
    private String playerGender;
    private int rank;
    private Long tournamentId;
    private boolean isSelected;

    public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public Player2DTO() {
    }

    public Player2DTO(String playerName, String playerGender, int rank, Long tournamentId) {
        this.playerName = playerName;
        this.playerGender = playerGender;
        this.rank = rank;
        this.tournamentId = tournamentId;
    }

    
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerGender() {
        return playerGender;
    }

    public void setPlayerGender(String playerGender) {
        this.playerGender = playerGender;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    @Override
    public String toString() {
        return "Player2DTO{" +
                "playerName='" + playerName + '\'' +
                ", playerGender='" + playerGender + '\'' +
                ", rank=" + rank +
                ", tournamentId=" + tournamentId +
                '}';
    }
}
