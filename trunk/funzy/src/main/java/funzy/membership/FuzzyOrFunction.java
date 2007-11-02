package funzy.membership;

import static java.lang.Math.max;

import com.google.common.base.Function;

/**
 * Implementation of a Fuzzy OR function.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class FuzzyOrFunction<T extends Number> implements Function<T, Double> {
	private final Function<T, Double> left, right;

	public FuzzyOrFunction(Function<T, Double> leftSide, Function<T, Double> rightSide) {
		left = leftSide;
		right = rightSide;
	}

	/* (non-Javadoc)
	 * @see com.google.common.base.Function#apply(java.lang.Object)
	 */
	public Double apply(T value) {
		return max(left.apply(value), right.apply(value));
	}
}
