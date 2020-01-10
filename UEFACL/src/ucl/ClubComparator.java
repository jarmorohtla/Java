package ucl;

import java.util.Comparator;

public class ClubComparator implements Comparator<Club> {

	@Override
	public int compare(Club c1, Club c2) {
		return c2.getPoints() - c1.getPoints();
	}
}
