package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import bowling.*;


public class BowlingGameTest {

	static Game g = new Game("Jarmo");

	static Frame f1 = new Frame(1);
	static Frame f2 = new Frame(2);
	static Frame f3 = new Frame(3);

	static Roll r1 = new Roll(10);
	static Roll r2 = new Roll(7);
	static Roll r3 = new Roll(3);
	static Roll r4 = new Roll(7);
	static Roll r5 = new Roll(2);


	@BeforeClass
	public static void createTestGame() {
		g.getFrames().add(f1);
		g.getFrames().add(f2);
		g.getFrames().add(f3);

		f1.getRolls().add(r1);
		f2.getRolls().add(r2);
		f2.getRolls().add(r3);
		f3.getRolls().add(r4);
		f3.getRolls().add(r5);

		r1.setNextRoll(r2);
		r2.setNextRoll(r3);
		r3.setNextRoll(r4);
		r4.setNextRoll(r5);
	}


	@Test
	public void testFrameScore() {
		assertEquals(20, f1.getScore());
		assertEquals(17, f2.getScore());
		assertEquals(9, f3.getScore());
	}


	@Test
	public void testTotalScore() {
		assertEquals(46, g.getTotalScore());
	}

}
