package ucl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class EmbeddedDbCreator {
	private String protocol = "jdbc:derby:";
	private String username = "someuser";
	private String password = "somepassword";
	private String dbName = "myDatabase";
	private Properties userInfo;

	public EmbeddedDbCreator() {
		userInfo = new Properties();
		userInfo.put("user", username);
		userInfo.put("password", password);
	}

	public void createDatabase() {

		List<Group> groups = new ArrayList<Group>();
		List<Club> clubs = new ArrayList<Club>();
		List<Player> players = new ArrayList<Player>();
		List<Match> matches = new ArrayList<Match>();
		List<Goal> goals = new ArrayList<Goal>();

		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("ucl/ucl_db/groups.txt"),"UTF8"));
			String line = br.readLine();

			while (line != null) {

				String[] temp = line.split(",");
				groups.add(new Group(Integer.parseInt(temp[0]),temp[1]));

				line = br.readLine();
			}

			br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("ucl/ucl_db/clubs.txt"),"UTF8"));
			line = br.readLine();

			while (line != null) {

				String[] temp = line.split(",");
				clubs.add(new Club(Integer.parseInt(temp[0]),temp[1],temp[2],Integer.parseInt(temp[3])));

				line = br.readLine();
			}

			br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("ucl/ucl_db/players.txt"),"UTF8"));
			line = br.readLine();

			while (line != null) {

				String[] temp = line.split(",");
				players.add(new Player(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),temp[2],temp[3],Integer.parseInt(temp[4])));

				line = br.readLine();
			}

			br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("ucl/ucl_db/matches.txt"),"UTF8"));
			line = br.readLine();

			while (line != null) {

				String[] temp = line.split(",");
				matches.add(new Match(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]),Integer.parseInt(temp[3]),Integer.parseInt(temp[4]),Integer.parseInt(temp[5]),Integer.parseInt(temp[6]),Integer.parseInt(temp[7])));

				line = br.readLine();
			}

			br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("ucl/ucl_db/goals.txt"),"UTF8"));
			line = br.readLine();

			while (line != null) {

				String[] temp = line.split(",");
				goals.add(new Goal(Integer.parseInt(temp[0]),temp[1],Integer.parseInt(temp[2]),Boolean.parseBoolean(temp[3]),Boolean.parseBoolean(temp[4]),Integer.parseInt(temp[5])));

				line = br.readLine();
			}

		} 
		catch (Exception e) {
			System.err.println("Error: " + e);
		}

		try {
			String dbUrl = protocol + dbName + ";create=true";
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection connection = DriverManager.getConnection(dbUrl, userInfo);
			Statement statement = connection.createStatement();

			String tableDescription = "CREATE TABLE groups (groupID INT, name VARCHAR(20))";
			statement.execute(tableDescription);
			tableDescription = "CREATE TABLE clubs (clubID INT, name VARCHAR(30), coach VARCHAR(30), groupID INT)";
			statement.execute(tableDescription);
			tableDescription = "CREATE TABLE players (playerID INT, number INT, name VARCHAR(30), position VARCHAR(30), clubID INT)";
			statement.execute(tableDescription);
			tableDescription = "CREATE TABLE matches (matchID INT, groupID INT, homeClubID INT, awayClubID INT, homeGoals INT, awayGoals INT, homePoints INT, awayPoints INT)";
			statement.execute(tableDescription);
			tableDescription = "CREATE TABLE goals (goalID INT, time VARCHAR(10), playerID INT, isPenalty VARCHAR(10), isOwnGoal VARCHAR(10), matchID INT)";
			statement.execute(tableDescription);

			String template ="INSERT INTO groups VALUES(?, ?)";
			PreparedStatement inserter = connection.prepareStatement(template);
			for(Group g: groups) {
				inserter.setInt(1, g.getGroupID());
				inserter.setString(2, g.getName());        
				inserter.executeUpdate();
			}
			inserter.close();

			template ="INSERT INTO clubs VALUES(?, ?, ?, ?)";
			inserter = connection.prepareStatement(template);
			for(Club c: clubs) {
				inserter.setInt(1, c.getClubID());
				inserter.setString(2, c.getName());
				inserter.setString(3, c.getCoach());
				inserter.setInt(4, c.getGroupID());   
				inserter.executeUpdate();
			}
			inserter.close();

			template ="INSERT INTO players VALUES(?, ?, ?, ?, ?)";
			inserter = connection.prepareStatement(template);
			for(Player p: players) {
				inserter.setInt(1, p.getPlayerID());
				inserter.setInt(2, p.getNumber());
				inserter.setString(3, p.getName());
				inserter.setString(4, p.getPosition());
				inserter.setInt(5, p.getClubID());   
				inserter.executeUpdate();
			}
			inserter.close();

			template ="INSERT INTO matches VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			inserter = connection.prepareStatement(template);
			for(Match m: matches) {
				inserter.setInt(1, m.getMatchID());
				inserter.setInt(2, m.getGroupID());
				inserter.setInt(3, m.getHomeClubID());
				inserter.setInt(4, m.getAwayClubID());
				inserter.setInt(5, m.getHomeGoals());
				inserter.setInt(6, m.getAwayGoals());
				inserter.setInt(7, m.getHomePoints());
				inserter.setInt(8, m.getAwayPoints());    
				inserter.executeUpdate();
			}
			inserter.close();

			template ="INSERT INTO goals VALUES(?, ?, ?, ?, ?, ?)";
			inserter = connection.prepareStatement(template);
			for(Goal g: goals) {
				inserter.setInt(1, g.getGoalID());
				inserter.setString(2, g.getTime());
				inserter.setInt(3, g.getPlayerID());
				inserter.setString(4, Boolean.toString(g.isPenalty()));
				inserter.setString(5, Boolean.toString(g.isOwnGoal()));
				inserter.setInt(6, g.getMatchID());
				inserter.executeUpdate();
			}
			inserter.close();

			connection.close();
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
	}
}
