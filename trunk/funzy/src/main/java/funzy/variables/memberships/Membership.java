package funzy.variables.memberships;

public interface Membership<X extends Number, Y extends Number> {
	
	boolean inXRange(X value);

	Y solveY(X x);

	boolean inYRange(Y value);

	X solveX(Y y);
}
