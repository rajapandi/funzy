package funzy.membership;


public class Memberships {
	private Memberships() {
	}

	public static final CrispyMembership newCrispyMembership(Point... points) {
		return new CrispyMembership(points);
	}

	public static final CrispyMembership newCrispyMembership(double... points) {
		Point[] list = new Point[points.length];
		for (int i = 0; i < points.length; i++)
			list[i] = new Point(points[i], 1);
		return newCrispyMembership(list);
	}
	
	
	
	public static final FuzzyMembership newFuzzyMembership(Point... points) {
		return new FuzzyMembership(points);
	}
}
