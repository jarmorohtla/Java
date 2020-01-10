package ucl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/match")
public class Matches extends HttpServlet {
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

		String matchID = request.getParameter("matchID");


		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String docType ="<!DOCTYPE HTML>";
		String title = "Match details";
		out.print(docType +
				"\n<HTML>\n" +
				"<HEAD>\n<TITLE>" + title + "</TITLE>\n" +
				"<LINK rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\" />\n" +
				"</HEAD>\n" +
				"<BODY>\n");
		showMatch(out, matchID);
		out.println("</BODY>\n</HTML>");
	}


	protected void showMatch(PrintWriter out, String matchID) {
		try {

			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			String query;
			ResultSet resultSet; 

			if (matchID!=null){
				query = "SELECT homeClubID, hc.name as homeName, awayClubID, ac.name as awayName," +
				" homeGoals, awayGoals, g.name as groupName" +
				" FROM matches m" +
				" JOIN clubs hc ON m.homeClubID=hc.clubID" +
				" JOIN clubs ac ON m.awayClubID=ac.clubID" +
				" JOIN groups g ON m.groupID=g.groupID"  +
				" WHERE matchID="+matchID;
				resultSet = statement.executeQuery(query);

				resultSet.next();

				int homeClubID = resultSet.getInt("homeClubID");
				int awayClubID = resultSet.getInt("awayClubID");

				out.println("<TABLE>");
				out.print("<TR>");
				out.printf("<TD></TD><TD class=\"matchgroup\">%s</TD><TD></TD>",resultSet.getString("groupName"));
				out.println("</TR>");
				out.print("<TR>");
				out.printf("<TD class=\"matchhomeclub\"><b>%s</b></TD>", resultSet.getString("homeName"));
				out.printf("<TD class=\"matchscore\"><b>%d-%d</b></TD>", resultSet.getInt("homeGoals"), resultSet.getInt("awayGoals"));
				out.printf("<TD class=\"matchawayclub\"><b>%s</b></TD>", resultSet.getString("awayName"));
				out.println("</TR>");

				query = "SELECT * FROM goals" +
				" JOIN players ON goals.playerID=players.playerID" +
				" WHERE matchID="+matchID +
				" ORDER BY time";
				resultSet = statement.executeQuery(query);

				while(resultSet.next()) {
					out.print("<TR>");
					if ((resultSet.getInt("clubID")==homeClubID)&&(!resultSet.getBoolean("isOwnGoal"))){
						out.printf("<TD class=\"homegoal\">%s %s", resultSet.getString("name"), resultSet.getString("time"));
						if (resultSet.getBoolean("isPenalty")){
							out.print(" (P)");
						}
						out.print("</TD><TD></TD><TD></TD>");
					}
					else if ((resultSet.getInt("clubID")==homeClubID)&&(resultSet.getBoolean("isOwnGoal"))){
						out.printf("<TD></TD><TD></TD><TD>%s %s (o.g.)</TD>", resultSet.getString("name"), resultSet.getString("time"));
					}
					else if ((resultSet.getInt("clubID")==awayClubID)&&(!resultSet.getBoolean("isOwnGoal"))){
						out.printf("<TD></TD><TD></TD><TD>%s %s", resultSet.getString("name"), resultSet.getString("time"));
						if (resultSet.getBoolean("isPenalty")){
							out.print(" (P)");
						}
						out.print("</TD>");
					}
					else{
						out.printf("<TD class=\"homegoal\">%s %s (o.g.)</TD><TD></TD><TD></TD>", resultSet.getString("name"), resultSet.getString("time"));

					}

					out.println("</TR>");
				}
				out.println("</TABLE>");
			}
			else{				
				out.println("No match selected");
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
}
