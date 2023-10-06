package com.advarra.badminton.model;

import jakarta.persistence.*;

@Entity
@Table(name = "players", schema = "tournaments")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Tournament tournament;
    private String name;

    private String gender;
    private Integer rank;
    private boolean isSelected;


    public Player() {
    }



    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tournament getTournamentId() {
        return tournament;
    }

    public void setTournamentId(Tournament tournament) {
        this.tournament = tournament;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }



	public Player(Long id, Tournament tournament, String name, String gender, Integer rank, boolean isSelected) {
		super();
		this.id = id;
		this.tournament = tournament;
		this.name = name;
		this.gender = gender;
		this.rank = rank;
		this.isSelected = isSelected;
	}



	public boolean isSelected() {
		return isSelected;
	}



	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}



	@Override
	public String toString() {
		return "Player [id=" + id + ", tournament=" + tournament + ", name=" + name + ", gender=" + gender + ", rank="
				+ rank + ", isSelected=" + isSelected + "]";
	}


}
