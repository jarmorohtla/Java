package bowling;

import java.util.Scanner;

/**
 * Main class for playing console bowling game
 * 
 * @author Jarmo Rohtla
 */
public class PlayConsoleBowling {

	public static void main(String[] args) {

		Game g = new Game("Jarmo");
		Scanner scanner = new Scanner(System.in);
		int frameNr = 0;
		int pinsStanding = 10;

		while (g.getCurrentFrame().getNr() < 11) {

			if (frameNr != g.getCurrentFrame().getNr()){
				System.out.println("FRAME " + g.getCurrentFrame().getNr());
				frameNr = g.getCurrentFrame().getNr();
			}

			System.out.print("Enter number of pins knocked down (0.." + pinsStanding + "): ");
			int pinsKnockedDown = scanner.nextInt();

			if ((pinsKnockedDown > -1) && (pinsKnockedDown <= pinsStanding)){
				pinsStanding = g.roll(pinsKnockedDown);
			} else {
				System.out.println("Illegal number of pins!");
			}
		}
		scanner.close();

		System.out.println("\nScoresheet for player: " + g.getPlayerName() + "\n");

		for(int i=0;i<g.getFrameRollsForScoreSheet().length;i++){
			System.out.print((i+1) + ":  ");
			System.out.print(g.getFrameRollsForScoreSheet()[i] + "  |  ");
			System.out.print(g.getFrameScores()[i]);
			System.out.println();
		}

		System.out.println("\nTotal score: " + g.getTotalScore());
	}
}
