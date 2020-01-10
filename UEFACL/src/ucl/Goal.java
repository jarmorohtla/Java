package ucl;

public class Goal {
	private int goalID, playerID, matchID;
	private String time;
	private boolean isPenalty, isOwnGoal;

	public Goal(int goalID, String time, int playerID,  boolean isPenalty, boolean isOwnGoal, int matchID) {
		this.setGoalID(goalID);
		this.setPlayerID(playerID);
		this.setMatchID(matchID);
		this.setTime(time);
		this.setPenalty(isPenalty);
		this.setOwnGoal(isOwnGoal);
	}

	public void setGoalID(int goalID) {
		this.goalID = goalID;
	}

	public int getGoalID() {
		return goalID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setMatchID(int matchID) {
		this.matchID = matchID;
	}

	public int getMatchID() {
		return matchID;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTime() {
		return time;
	}

	public void setPenalty(boolean isPenalty) {
		this.isPenalty = isPenalty;
	}

	public boolean isPenalty() {
		return isPenalty;
	}

	public void setOwnGoal(boolean isOwnGoal) {
		this.isOwnGoal = isOwnGoal;
	}

	public boolean isOwnGoal() {
		return isOwnGoal;
	}

}
