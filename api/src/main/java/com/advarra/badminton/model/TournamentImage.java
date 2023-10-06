package com.advarra.badminton.model;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table(name = "tournament_images", schema = "tournaments")
public class TournamentImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @ManyToOne
    private Tournament tournament;

    @Lob
    private byte[] image;

    private String imageName;

    private String imageContentType;

    public TournamentImage() {
    }

    public TournamentImage(Tournament tournament, byte[] image, String imageName, String imageContentType) {
        this.tournament = tournament;
        this.image = image;
        this.imageName = imageName;
        this.imageContentType = imageContentType;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    @Override
    public String toString() {
        return "TournamentImage{" +
                "tournament=" + tournament +
                ", image=" + Arrays.toString(image) +
                ", imageName='" + imageName + '\'' +
                ", imageContentType='" + imageContentType + '\'' +
                '}';
    }
}
