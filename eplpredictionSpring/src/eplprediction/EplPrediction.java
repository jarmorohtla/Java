package eplprediction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class EplPrediction {

	public static void main(String[] args) throws IOException {

		//dropUsersTable();
		//createUsersTable();

		int matchday = 1;
		//countPoints(matchday);
		//printTable(matchday);

	}


	static void dropUsersTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/epldb",
							"postgres", "");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "DROP TABLE USERS;";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
		System.out.println("Table Users dropped successfully");
	}


	static void createUsersTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/epldb",
							"postgres", "");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE USERS " +
					"(NAME           TEXT    NOT NULL, " +
					" TOTAL          INT     NOT NULL, " + 
					" MATCHDAY       INT     NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
		System.out.println("Table Users created successfully");
	}


	static HashMap<String, String> createMatchesHashMap(int matchday) {
		Resource r = new ClassPathResource("matchdayBeans.xml");
		BeanFactory factory = new XmlBeanFactory(r);

		Matchday md =(Matchday)factory.getBean("md" + matchday);
		Map<String,String> matches = md.getMatches();

		matches = matches.entrySet().stream()
				.collect(Collectors.toMap(e -> e.getKey().replaceAll("[^a-zA-Z]", ""), Map.Entry::getValue));

		return (HashMap<String, String>) matches;
	}


	static String readPredictionsString(int matchday) throws IOException {
		Path fileName= Path.of("predictions_" + matchday + ".txt");
		String str = Files.readString(fileName);

		return str;
	}


	static void countPoints(int matchday) throws IOException {
		HashMap<String, String> matches = createMatchesHashMap(matchday);
		String predictionsString = readPredictionsString(matchday);

		String[] predictions = predictionsString.split("QUOTE");

		Resource r = new ClassPathResource("applicationContext.xml");
		BeanFactory factory = new XmlBeanFactory(r);
		UserDao dao=(UserDao)factory.getBean("udao");

		dao.resetUserMatchday();

		for (int i = 1; i < predictions.length; i++) {

			String name = predictions[i].substring(1, predictions[i].indexOf(";"));
			System.out.println(i + ". " + name);
			int totalPoints = getUserTotal(name);
			int matchdayPoints = 0;
			int predictedMatches = 0;

			String[] userPredictions =  predictions[i].substring(predictions[i].indexOf("]")+1).strip().split("\\r?\\n|\\r");
			for (int j = 0; j < userPredictions.length; j++) {
				String predictedMatchDescription = userPredictions[j].substring(0, userPredictions[j].lastIndexOf(" "));
				String predictedMatchPrediction = userPredictions[j].substring(userPredictions[j].lastIndexOf(" ")+1).toLowerCase();

				predictedMatchDescription = predictedMatchDescription.replaceAll("[^a-zA-Z]", "");
				predictedMatchPrediction = predictedMatchPrediction.substring(0, 1);

				if (matches.get(predictedMatchDescription).equals(predictedMatchPrediction)){
					matchdayPoints++;					
					System.out.println(matchdayPoints + ": " + predictedMatchDescription);
				}

				predictedMatches++;
			}

			totalPoints = totalPoints + matchdayPoints ;
			System.out.println("Total: " + totalPoints + ", predicted: " + predictedMatches);

			//dao.updateUser(new User(name, totalPoints, matchdayPoints));

			System.out.println();
		}
	}


	static int getUserTotal(String name) {
		Connection c = null;
		Statement stmt = null;
		int total = 0;

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/epldb",
							"postgres", "");
			c.setAutoCommit(false);

			stmt = c.createStatement();

			String query = "SELECT TOTAL FROM USERS WHERE NAME='" + name + "';";
			ResultSet rs = stmt.executeQuery( query);
			if(rs.next()){
				total  = rs.getInt("total");
			} else {
				String sql = "INSERT INTO USERS (NAME,TOTAL,MATCHDAY) VALUES ('" + name + "', 0, 0);";
				stmt.executeUpdate(sql);
				c.commit();

				System.out.println("Inserted user: " + name);

				total = 0;
			}
			stmt.close();
			rs.close();			
			c.close();

		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}

		return total;
	}


	static void printTable(int matchday) {
		Resource r = new ClassPathResource("applicationContext.xml");
		BeanFactory factory = new XmlBeanFactory(r);
		UserDao dao=(UserDao)factory.getBean("udao");

		System.out.println("\nTabel pärast " + matchday + ". vooru:\n");

		List<User> list=dao.getUsers();  

		int curTotalStart = 0;
		int curTotalEnd = 0;
		int curTotal = 0;
		String curTotalUsers = "";

		for(User u:list)   {
			String  name = u.getName();
			int totalPoints  = u.getTotal();
			int matchdayPoints  = u.getMatchday();

			if (totalPoints != curTotal) {
				if (curTotalEnd != curTotalStart) {
					System.out.println(curTotalStart + "-" + curTotalEnd + ". " + curTotalUsers);
				} else if (curTotalStart > 0){
					System.out.println(curTotalStart + ". " + curTotalUsers);
				}
				if (matchday > 1) {
					if (matchdayPoints > -1){
						curTotalUsers = name + " " + totalPoints + "p (+" + matchdayPoints + ")";
					} else {
						curTotalUsers = name + " " + totalPoints + "p (-)";
					}
				} else {
					curTotalUsers = name + " " + totalPoints + "p";
				}				

				curTotal = totalPoints;
				curTotalStart = curTotalEnd + 1;
				curTotalEnd = curTotalStart;
			} else {
				if (matchday > 1) {
					if (matchdayPoints > -1) {
						curTotalUsers = curTotalUsers + ", " + name + " " + totalPoints + "p (+" + matchdayPoints + ")";
					} else {
						curTotalUsers = curTotalUsers + ", " + name + " " + totalPoints + "p (-)";
					}
				} else {
					curTotalUsers = curTotalUsers + ", " + name + " " + totalPoints + "p";
				}

				curTotalEnd++; 
			}
		}

		if (curTotalEnd != curTotalStart) {
			System.out.println(curTotalStart + "-" + curTotalEnd + ". " + curTotalUsers);
		} else {
			System.out.println(curTotalStart + ". " + curTotalUsers);
		}
	}
}
