package models;

import java.io.Serializable;
import java.util.Objects;

public abstract class SportsClub implements Serializable {
    private String clubName;
    private String clubLocation;
    private Date dateFounded;
    private String headCoach;

    public SportsClub() {}
    public SportsClub(String clubName, String clubLocation, Date dateFounded, String headCoach) {
        this.clubName = clubName;
        this.clubLocation = clubLocation;
        this.dateFounded = dateFounded;
        this.headCoach = headCoach;
    }

    public String getClubName() { return clubName; }
    public void setClubName(String clubName) { this.clubName = clubName; }

    public String getClubLocation() { return clubLocation; }
    public void setClubLocation(String clubLocation) { this.clubLocation = clubLocation; }

    public Date getDateFounded() { return dateFounded; }
    public void setDateFounded(Date dateFounded) { this.dateFounded = dateFounded; }

    public String getHeadCoach() { return headCoach; }
    public void setHeadCoach(String headCoach) { this.headCoach = headCoach; }

    @Override
    public String toString() {
        return "Club Name: " + this.clubName +
                "\nClub Location: " + this.clubLocation +
                "\nDate Founded: " + this.dateFounded.toString() +
                "\nHead Coach: " + this.headCoach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsClub that = (SportsClub) o;
        return Objects.equals(clubName, that.clubName) &&
                Objects.equals(clubLocation, that.clubLocation) &&
                Objects.equals(dateFounded, that.dateFounded) &&
                Objects.equals(headCoach, that.headCoach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubName, clubLocation, dateFounded, headCoach);
    }
}
