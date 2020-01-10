package ucl;

import java.io.*;

import java.sql.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/groups")
public class Groups extends HttpServlet {
	private final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	protected final String dbName = "myDatabase";
	protected final String url = "jdbc:derby:" + dbName;
	protected final String tableName = "groups";
	protected final String username = "someuser";
	protected final String password = "somepassword";

	@Override
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
	throws ServletException, IOException {

		EmbeddedDbCreator tester = new EmbeddedDbCreator();
		tester.createDatabase();

		String groupID = request.getParameter("groupID");


		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String docType ="<!DOCTYPE HTML>";
		String title = "Groups";
		out.print(docType +
				"\n<HTML>\n" +
				"<HEAD>\n<TITLE>" + title + "</TITLE>\n" +
				"<LINK rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\" />\n" +		
				"</HEAD>\n" +
				"<BODY>\n");
		showGroup(out, groupID);
		out.println("</BODY>\n</HTML>");
	}

	protected void showGroup(PrintWriter out, String groupID) {
		try {

			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			String query;
			ResultSet resultSet; 

			if (groupID==null){
				query = "SELECT * FROM groups";
				resultSet = statement.executeQuery(query);

				out.println("<TABLE>");

				while(resultSet.next()) {
					out.print("<TR>");
					out.printf("<TD><a href=\"groups?groupID=%d\">%s</a></TD>", resultSet.getInt("groupID"), resultSet.getString("name"));
					out.println("</TR>");
				}
				out.println("</TABLE>");
			}
			else{

				List<Club> clubs = new ArrayList<Club>();

				query = "SELECT name FROM groups WHERE groupID="+groupID;
				resultSet = statement.executeQuery(query);
				resultSet.next();
				out.println("<H1>"+resultSet.getString("name")+"</H1>");

				out.println("<DIV id=\"group\">");

				query = "SELECT players.name, COUNT(*) AS nrOfGoals" +
				" FROM goals" +
				" JOIN players ON goals.playerID=players.playerID" +
				" JOIN clubs ON players.clubID=clubs.clubID" +
				" WHERE groupID="+groupID+
				" AND isOwnGoal='false'" +
				" GROUP BY players.name" +
				" HAVING COUNT(*)>1" +
				" ORDER BY nrOfGoals DESC";

				resultSet = statement.executeQuery(query);
				printGoalscorers(resultSet, out);

				query = "SELECT clubID, name, coach FROM clubs WHERE groupID="+groupID;
				resultSet = statement.executeQuery(query);

				while(resultSet.next()) {
					clubs.add(new Club(resultSet.getInt("clubID"), resultSet.getString("name"), resultSet.getString("coach"), Integer.parseInt(groupID )));
				}

				for(Club c: clubs) {
					query = "SELECT SUM(homePoints) AS homeSum" +
					" FROM matches " +
					" WHERE homeClubID="+c.getClubID();
					resultSet = statement.executeQuery(query);
					resultSet.next();
					int homeSum = resultSet.getInt("homeSum");

					query = "SELECT SUM(awayPoints) AS awaySum" +
					" FROM matches " +
					" WHERE awayClubID="+c.getClubID();
					resultSet = statement.executeQuery(query);
					resultSet.next();
					int awaySum = resultSet.getInt("awaySum");

					c.setPoints(homeSum+awaySum);


					query = "SELECT COUNT(*) AS homeWins" +
					" FROM matches " +
					" WHERE homeClubID="+c.getClubID()+"" +
					" AND homePoints=3";
					resultSet = statement.executeQuery(query);
					resultSet.next();
					int homeWins = resultSet.getInt("homeWins");

					query = "SELECT COUNT(*) AS awayWins" +
					" FROM matches " +
					" WHERE awayClubID="+c.getClubID()+"" +
					" AND awayPoints=3";
					resultSet = statement.executeQuery(query);
					resultSet.next();
					int awayWins = resultSet.getInt("awayWins");

					c.setWins(homeWins+awayWins);


					query = "SELECT COUNT(*) AS homeDraws" +
					" FROM matches " +
					" WHERE homeClubID="+c.getClubID()+"" +
					" AND homePoints=1";
					resultSet = statement.executeQuery(query);
					resultSet.next();
					int homeDraws = resultSet.getInt("homeDraws");

					query = "SELECT COUNT(*) AS awayDraws" +
					" FROM matches " +
					" WHERE awayClubID="+c.getClubID()+"" +
					" AND awayPoints=1";
					resultSet = statement.executeQuery(query);
					resultSet.next();
					int awayDraws = resultSet.getInt("awayDraws");

					c.setDraws(homeDraws+awayDraws);


					query = "SELECT COUNT(*) AS homeLosses" +
					" FROM matches " +
					" WHERE homeClubID="+c.getClubID()+"" +
					" AND homePoints=0";
					resultSet = statement.executeQuery(query);
					resultSet.next();
					int homeLosses = resultSet.getInt("homeLosses");

					query = "SELECT COUNT(*) AS awayLosses" +
					" FROM matches " +
					" WHERE awayClubID="+c.getClubID()+"" +
					" AND awayPoints=0";
					resultSet = statement.executeQuery(query);
					resultSet.next();
					int awayLosses = resultSet.getInt("awayLosses");

					c.setLosses(homeLosses+awayLosses);


					query = "SELECT SUM(homeGoals) AS homeGoalsSum" +
					" FROM matches " +
					" WHERE homeClubID="+c.getClubID();
					resultSet = statement.executeQuery(query);
					resultSet.next();
					int homeGoalsFor = resultSet.getInt("homeGoalsSum");

					query = "SELECT SUM(awayGoals) AS awayGoalsSum" +
					" FROM matches " +
					" WHERE awayClubID="+c.getClubID();
					resultSet = statement.executeQuery(query);
					resultSet.next();
					int awayGoalsFor = resultSet.getInt("awayGoalsSum");

					c.setGoalsFor(homeGoalsFor+awayGoalsFor);


					query = "SELECT SUM(awayGoals) AS awayGoalsSum" +
					" FROM matches " +
					" WHERE homeClubID="+c.getClubID();
					resultSet = statement.executeQuery(query);
					resultSet.next();
					int homeGoalsAgainst = resultSet.getInt("awayGoalsSum");

					query = "SELECT SUM(homeGoals) AS homeGoalsSum" +
					" FROM matches " +
					" WHERE awayClubID="+c.getClubID();
					resultSet = statement.executeQuery(query);
					resultSet.next();
					int awayGoalsAgainst = resultSet.getInt("homeGoalsSum");

					c.setGoalsAgainst(homeGoalsAgainst+awayGoalsAgainst);
				}

				Collections.sort(clubs, new ClubComparator());

				out.println("<TABLE id=\"standings\">");
				for(Club c: clubs) {
					out.print("<TR class=\"club\">");
					out.printf("<TD class=\"clubname\"><B>%s</B></TD>", c.getName());
					out.printf("<TD>%d</TD>", c.getWins());
					out.printf("<TD>%d</TD>", c.getDraws());
					out.printf("<TD>%d</TD>", c.getLosses());
					out.printf("<TD>%d-%d</TD>", c.getGoalsFor(), c.getGoalsAgainst());
					out.printf("<TD><B>%d</B></TD>", c.getPoints());
					out.println("</TR>");
				}
				out.println("</TABLE>");


				query = "SELECT matchID, hc.name as homeName, ac.name as awayName, homeGoals, awayGoals" +
				" FROM matches m" +
				" JOIN clubs hc ON m.homeClubID=hc.clubID" +
				" JOIN clubs ac ON m.awayClubID=ac.clubID" +
				" WHERE m.groupID="+groupID;
				resultSet = statement.executeQuery(query);
				printMatches(resultSet, out);				

				out.println("</DIV>");
			}
			connection.close();

		} catch(Exception e) {
			System.err.println("Error: " + e);
		}
	}


	protected Connection getConnection() 
	throws Exception {
		Properties userInfo = new Properties();
		userInfo.put("user", username);
		userInfo.put("password", password);
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, userInfo);
		return(connection);
	}


	protected void printMatches(ResultSet resultSet,PrintWriter out) 
	throws SQLException {
		out.println("<TABLE>");

		while(resultSet.next()) {
			out.print("<TR>");
			out.printf("<TD class=\"homeclub\">%s</TD>", resultSet.getString("homeName"));
			out.printf("<TD><a href=\"match?matchID=%d\">%d-%d</a></TD>", resultSet.getInt("matchID"), resultSet.getInt("homeGoals"), resultSet.getInt("awayGoals"));
			out.printf("<TD>%s</TD>", resultSet.getString("awayName"));
			out.println("</TR>");			
		}
		out.println("</TABLE>");
	}


	protected void printGoalscorers(ResultSet resultSet,PrintWriter out) 
	throws SQLException {
		out.println("<TABLE id=\"groupgoalscorers\" >");
		out.println("<CAPTION><b>Top goalscorers</b></CAPTION>");

		while(resultSet.next()) {
			out.print("<TR>");
			out.printf("<TD>%s</TD>", resultSet.getString("name"));
			out.printf("<TD>%d</TD>", resultSet.getInt("nrOfGoals"));
			out.println("</TR>");			
		}
		out.println("</TABLE>");
	}
}

