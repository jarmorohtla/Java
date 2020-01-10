package ucl;

public class Match {
	private int matchID, groupID, homeClubID, awayClubID, homeGoals, awayGoals, homePoints, awayPoints;

	public Match(int matchID, int groupID, int homeClubID, int awayClubID,
			int homeGoals, int awayGoals, int homePoints, int awayPoints) {
		this.setMatchID(matchID);
		this.setGroupID(groupID);
		this.setHomeClubID(homeClubID);
		this.setAwayClubID(awayClubID);
		this.setHomeGoals(homeGoals);
		this.setAwayGoals(awayGoals);
		this.setHomePoints(homePoints);
		this.setAwayPoints(awayPoints);
	}

	public void setMatchID(int matchID) {
		this.matchID = matchID;
	}

	public int getMatchID() {
		return matchID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public int getGroupID() {
		return groupID;
	}

	public void setHomeClubID(int homeClubID) {
		this.homeClubID = homeClubID;
	}

	public int getHomeClubID() {
		return homeClubID;
	}

	public void setAwayClubID(int awayClubID) {
		this.awayClubID = awayClubID;
	}

	public int getAwayClubID() {
		return awayClubID;
	}

	public void setHomeGoals(int homeGoals) {
		this.homeGoals = homeGoals;
	}

	public int getHomeGoals() {
		return homeGoals;
	}

	public void setAwayGoals(int awayGoals) {
		this.awayGoals = awayGoals;
	}

	public int getAwayGoals() {
		return awayGoals;
	}

	public void setHomePoints(int homePoints) {
		this.homePoints = homePoints;
	}

	public int getHomePoints() {
		return homePoints;
	}

	public void setAwayPoints(int awayPoints) {
		this.awayPoints = awayPoints;
	}

	public int getAwayPoints() {
		return awayPoints;
	}
}
