package ucl;

public class Group {
	private int groupID;
	private String name;

	public Group(int groupID, String name) {
		this.groupID = groupID;
		this.name = name;
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
}
