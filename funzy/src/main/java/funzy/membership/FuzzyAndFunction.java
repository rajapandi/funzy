package funzy.membership;

import static java.lang.Math.min;

import com.google.common.base.Function;

/**
 * Implementation of a Fuzzy AND function.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class FuzzyAndFunction<T extends Number> implements Function<T, Double> {
	private final Function<T, Double> left, right;

	public FuzzyAndFunction(Function<T, Double> leftSide, Function<T, Double> rightSide) {
		left = leftSide;
		right = rightSide;
	}

	/* (non-Javadoc)
	 * @see com.google.common.base.Function#apply(java.lang.Object)
	 */
	public Double apply(T value) {
		return min(left.apply(value), right.apply(value));
	}
}
