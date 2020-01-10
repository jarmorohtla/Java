package bowling;

/**
 * Single roll of bowling
 * 
 * @author Jarmo Rohtla
 */
public class Roll {
	private int pinsKnockedDown;
	private Roll nextRoll;

	public Roll(int pinsKnockedDown){
		this.pinsKnockedDown = pinsKnockedDown;
	}

	public int getPinsKnockedDown() {
		return pinsKnockedDown;
	}

	public void setPinsKnockedDown(int pinsKnockedDown) {
		this.pinsKnockedDown = pinsKnockedDown;
	}

	public Roll getNextRoll() {
		return nextRoll;
	}

	public void setNextRoll(Roll nextRoll) {
		this.nextRoll = nextRoll;
	}
}
