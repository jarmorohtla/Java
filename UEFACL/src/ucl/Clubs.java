package ucl;

import java.io.*;

import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/clubs")
public class Clubs extends HttpServlet {
	private final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	protected final String dbName = "myDatabase";
	protected final String url = "jdbc:derby:" + dbName;
	protected final String tableName = "clubs";
	protected final String username = "someuser";
	protected final String password = "somepassword";

	@Override
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
	throws ServletException, IOException {

		EmbeddedDbCreator tester = new EmbeddedDbCreator();
		tester.createDatabase();

		String clubID = request.getParameter("clubID");


		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String docType ="<!DOCTYPE HTML>";
		String title = "Clubs";
		out.print(docType +
				"\n<HTML>\n" +
				"<HEAD>\n" +
				"<TITLE>" + title + "</TITLE>\n" +
				"<LINK rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\" />\n" +
				"</HEAD>\n" +
				"<BODY>\n");
		showClub(out, clubID);
		out.println("</BODY>\n</HTML>");
	}


	protected void showClub(PrintWriter out, String clubID) {
		try {

			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			String query;
			ResultSet resultSet; 

			if (clubID==null){
				query = "SELECT clubID, clubs.name, coach, groups.name AS groupName FROM clubs JOIN groups ON clubs.groupID=groups.groupID";
				resultSet = statement.executeQuery(query);

				out.println("<TABLE id=\"clubs\" >");
				out.println("<TR><TH>Name</TH><TH>Coach</TH><TH>Group</TH></TR>");

				while(resultSet.next()) {
					out.print("<TR>");
					out.printf("<TD><a href=\"clubs?clubID=%d\">%s</a></TD>", resultSet.getInt("clubID"), resultSet.getString("name"));
					out.printf("<TD>%s</TD>", resultSet.getString("coach"));	
					out.printf("<TD>%s</TD>", resultSet.getString("groupName"));
					out.println("</TR>");
				}
				out.println("</TABLE>");
			}
			else{
				query = "SELECT name FROM clubs WHERE clubID="+clubID;
				resultSet = statement.executeQuery(query);
				resultSet.next();
				out.println("<H1>"+resultSet.getString("name")+"</H1>");

				out.println("<DIV id=\"players\">");
				
				query = "SELECT * FROM players WHERE clubID="+clubID+" AND position='Goalkeeper'";
				resultSet = statement.executeQuery(query);
				printPlayers(resultSet, out, "Goalkeepers");				

				query = "SELECT * FROM players WHERE clubID="+clubID+" AND position='Defender'";
				resultSet = statement.executeQuery(query);
				printPlayers(resultSet, out, "Defenders");
			
				query = "SELECT * FROM players WHERE clubID="+clubID+" AND position='Midfielder'";
				resultSet = statement.executeQuery(query);
				printPlayers(resultSet, out, "Midfielders");
				
				query = "SELECT * FROM players WHERE clubID="+clubID+" AND position='Forward'";
				resultSet = statement.executeQuery(query);
				printPlayers(resultSet, out, "Forwards");
				
				query = "SELECT players.name, COUNT(*) AS nrOfGoals" +
				" FROM goals" +
				" JOIN players ON goals.playerID=players.playerID" +
				" WHERE clubID="+clubID+
				" AND isOwnGoal='false'"+
				" GROUP BY players.name" +
				" ORDER BY nrOfGoals DESC";
				resultSet = statement.executeQuery(query);
				printGoalscorers(resultSet, out);
				
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
		Connection connection =
			DriverManager.getConnection(url, userInfo);
		return(connection);
	}


	protected void printPlayers(ResultSet resultSet,PrintWriter out, String caption) 
	throws SQLException {
		out.println("<TABLE id=\""+caption+"\" >");
		out.println("<CAPTION><b>"+caption+"</b></CAPTION>");

		while(resultSet.next()) {
			out.print("<TR>");
			out.printf("<TD class=\"playernumber\">%d</TD>", resultSet.getInt("number"));
			out.printf("<TD>%s</TD>", resultSet.getString("name"));	
			out.println("</TR>");			
		}
		out.println("</TABLE>");
	}


	protected void printGoalscorers(ResultSet resultSet,PrintWriter out) 
	throws SQLException {
		out.println("<TABLE id=\"clubgoalscorers\" >");
		out.println("<CAPTION><b>Goalscorers</b></CAPTION>");

		while(resultSet.next()) {
			out.print("<TR>");
			out.printf("<TD>%s</TD>", resultSet.getString("name"));
			out.printf("<TD>%d</TD>", resultSet.getInt("nrOfGoals"));
			out.println("</TR>");			
		}
		out.println("</TABLE>");
	}
}
