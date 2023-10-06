package com.advarra.badminton.dtos.tournament_dtos.ongoing;

public class GameFormatDTO {
	private Long tournamentId;
	private String gameFormat;
	public Long getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(Long tournamentId) {
		this.tournamentId = tournamentId;
	}
	public String getGameFormat() {
		return gameFormat;
	}
	public void setGameFormat(String gameFormat) {
		this.gameFormat = gameFormat;
	}
	

}
