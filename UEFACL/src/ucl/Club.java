package ucl;

public class Club {
	private int clubID, groupID;
	private String name, coach;
	private int wins, draws, losses, goalsFor, goalsAgainst, points;

	public Club(int clubID, String name, String coach, int groupID) {
		this.clubID = clubID;
		this.groupID = groupID;
		this.name = name;
		this.coach = coach;
	}

	public void setClubID(int clubID) {
		this.clubID = clubID;
	}

	public int getClubID() {
		return clubID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public int getGroupID() {
		return groupID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}

	public String getCoach() {
		return coach;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getWins() {
		return wins;
	}

	public void setDraws(int draws) {
		this.draws = draws;
	}

	public int getDraws() {
		return draws;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getLosses() {
		return losses;
	}

	public void setGoalsFor(int goalsFor) {
		this.goalsFor = goalsFor;
	}

	public int getGoalsFor() {
		return goalsFor;
	}

	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPoints() {
		return points;
	}
}
