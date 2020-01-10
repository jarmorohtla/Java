package ucl;

public class Player {
	private int playerID, number, clubID;
	private String name, position;

	public Player(int playerID, int number, String name, String position, int clubID) {
		this.setPlayerID(playerID);
		this.setNumber(number);
		this.setClubID(clubID);
		this.setName(name);
		this.setPosition(position);
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setClubID(int clubID) {
		this.clubID = clubID;
	}

	public int getClubID() {
		return clubID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPosition() {
		return position;
	}
}
