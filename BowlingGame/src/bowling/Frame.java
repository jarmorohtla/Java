package bowling;

import java.util.ArrayList;

/**
 * Single frame of bowling
 * 
 * @author Jarmo Rohtla
 */
public class Frame {
	private int nr;
	private ArrayList<Roll> rolls;

	public Frame(int nr){
		this.nr = nr;
		rolls = new ArrayList<Roll>();
	}

	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	/**
	 * Calculate frame score
	 * 
	 * @return  frame score
	 */
	public int getScore() {
		int score = 0;

		for (Roll r : rolls) {
			score += r.getPinsKnockedDown();
		}

		if ((score == 10) && (nr < 10)) {			 

			if (rolls.size() == 1){//strike
				Roll r1 = rolls.get(0).getNextRoll();
				Roll r2 = r1.getNextRoll();

				if ((r1 != null) && (r2 != null)) {
					score = score + r1.getPinsKnockedDown() + r2.getPinsKnockedDown();
					return score;
				} else {
					return -1;
				}
			} else {//spare
				Roll r = rolls.get(1).getNextRoll();

				if (r != null) {
					score += r.getPinsKnockedDown();
					return score;
				} else {
					return -1;
				}
			}
		}

		return score;
	}

	public String toString() {
		String strRolls = "";

		for (Roll r : rolls) {
			if (rolls.indexOf(r) == 0){
				if (r.getPinsKnockedDown() == 10){
					strRolls += ("X");
				} else {
					strRolls += (r.getPinsKnockedDown());
				}
			}

			if (rolls.indexOf(r) == 1){
				if (r.getPinsKnockedDown() == 10){
					strRolls += (" X");
				} else if ((rolls.get(0).getPinsKnockedDown() + rolls.get(1).getPinsKnockedDown()) == 10){
					strRolls += (" /");
				} else {
					strRolls += (" " + r.getPinsKnockedDown());
				}
			}

			if (rolls.indexOf(r) == 2){
				if (r.getPinsKnockedDown() == 10){
					strRolls += (" X");
				} else {
					strRolls += (" " + r.getPinsKnockedDown());
				}
			}
		}

		return strRolls;
	}

	public ArrayList<Roll> getRolls() {
		return rolls;
	}

	public void setRolls(ArrayList<Roll> rolls) {
		this.rolls = rolls;
	}
}
