
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String nationality;
    private int assists;
    private int goals;
    private int penalties; 
    private String team;
    private int games;
    
    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality) {
	this.nationality = nationality;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public int getAssists() {
	return assists;
    }

    public int getGoals() {
	return goals;
    }

    public int getPenalties() {
	return penalties;
    }

    public String getTeam() {
	return team;
    }

    public int getGames() {
	return games;
    }

    @Override
    public String toString() {
        int points = goals + assists;
        return name + " " + team + " " + goals + " + " + assists + " = " + points;
    }

    @Override
    public int compareTo(Player p) {
        int points1 = this.goals + this.assists;
        int points2 = p.goals + p.goals;
        if (points1 > points2) {
            return 1;
        } else if (points2 > points1) {
            return -1;
        } else {
            return 0;
        }
    }
    
      
}
