package com.advarra.badminton.model;

import jakarta.persistence.*;


@Entity
@Table(name = "game_formats", schema = "tournaments")
public class GameFormat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private boolean MenSingles;
    private boolean WomenSingles;
    private boolean MenDoubles;
    private boolean WomenDoubles;
    private boolean MixedDoubles;
    @ManyToOne
    private Tournament tournament;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public boolean isMenSingles() {
		return MenSingles;
	}
	public void setMenSingles(boolean menSingles) {
		MenSingles = menSingles;
	}
	public boolean isWomenSingles() {
		return WomenSingles;
	}
	public void setWomenSingles(boolean womenSingles) {
		WomenSingles = womenSingles;
	}
	public boolean isMenDoubles() {
		return MenDoubles;
	}
	public void setMenDoubles(boolean menDoubles) {
		MenDoubles = menDoubles;
	}
	public boolean isWomenDoubles() {
		return WomenDoubles;
	}
	public void setWomenDoubles(boolean womenDoubles) {
		WomenDoubles = womenDoubles;
	}
	public boolean isMixedDoubles() {
		return MixedDoubles;
	}

	public void setMixedDoubles(boolean mixedDoubles) {
		MixedDoubles = mixedDoubles;
	}
	public Tournament getTournament() {
		return tournament;
	}
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}


}
