package bowling;

import java.util.ArrayList;

/**
 * Single game of bowling
 * 
 * @author Jarmo Rohtla
 */
public class Game {
	private String playerName;
	private Roll currentRoll;
	private Frame currentFrame;	
	private ArrayList<Frame> frames;

	/**
	 * Start new bowling game
	 * 
	 * @param playerName  player's name
	 */
	public Game(String playerName){
		this.playerName = playerName;
		frames = new ArrayList<Frame>();

		Frame frame1 = new Frame(1);
		frames.add(frame1);
		currentFrame = frame1;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Frame getCurrentFrame() {
		return currentFrame;
	}

	public ArrayList<Frame> getFrames() {
		return frames;
	}

	public void setFrames(ArrayList<Frame> frames) {
		this.frames = frames;
	}

	/**
	 * @param pinsKnockedDown  number of pins knocked down
	 * @return  number of pins standing for next roll
	 */
	public int roll(int pinsKnockedDown) {
		Roll r = new Roll(pinsKnockedDown);
		int pinsStanding = 10;

		if (currentRoll != null){
			currentRoll.setNextRoll(r);
			currentRoll = r;
		} else {
			currentRoll = r;
		}

		currentFrame.getRolls().add(currentRoll);

		if ((currentRoll.getPinsKnockedDown() == 10) && (currentFrame.getNr() < 10)){//strike
			Frame f = new Frame(currentFrame.getNr() + 1);
			frames.add(f);
			currentFrame = f;

			return pinsStanding;
		}

		if ((currentFrame.getRolls().size() == 2) && (currentFrame.getNr() < 10)){
			Frame f = new Frame(currentFrame.getNr() + 1);
			frames.add(f);
			currentFrame = f;

			return pinsStanding;
		}

		if (currentFrame.getNr() == 10){

			if (currentFrame.getRolls().size() == 1) {			
				if (currentRoll.getPinsKnockedDown() == 10){
					return pinsStanding;
				} else {
					pinsStanding = 10 - pinsKnockedDown;
					return pinsStanding;
				}
			}

			if (currentFrame.getRolls().size() == 2) {
				int score = 0;

				for (Roll frameRoll : currentFrame.getRolls()) {
					score += frameRoll .getPinsKnockedDown();
				}

				if (score < 10){
					Frame f = new Frame(currentFrame.getNr() + 1);
					currentFrame = f;

					return pinsStanding;
				} else {
					if (score == 10){
						return pinsStanding;
					} else if (score == 20){
						return pinsStanding;
					} else {
						pinsStanding = 10 - pinsKnockedDown;
						return pinsStanding;
					}
				}
			}

			if (currentFrame.getRolls().size() == 3) {			
				Frame f = new Frame(currentFrame.getNr() + 1);
				currentFrame = f;

				return pinsStanding;	
			}

		}

		pinsStanding = 10 - pinsKnockedDown;
		return pinsStanding;
	}

	/**
	 * Calculate total score of game
	 * 
	 * @return total score of game
	 */
	public int getTotalScore() {
		int[] frameScores = getFrameScores();
		int totalScore = 0;

		for (int i = 0; i < 10 ; i++) {
			if (frameScores[i] > 0) {
				totalScore += frameScores[i];
			}
		}

		return totalScore;
	}

	/**
	 * @return string representation of rolls in all frames
	 */
	public String[] getFrameRollsForScoreSheet() {
		String[] frameRolls = new String[10];
		int i = 0;

		for (Frame f : frames) {
			frameRolls[i] = f.toString();
			i++;
		}

		return frameRolls;
	}

	/**
	 * @return  all frame scores
	 */
	public int[] getFrameScores() {
		int[] frameScores = new int[10];
		int i = 0;

		for (Frame f : frames) {
			frameScores[i] = f.getScore();
			i++;
		}

		return frameScores;
	}
}
