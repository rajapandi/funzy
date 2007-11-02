package funzy.membership;

import com.google.common.base.Function;

/**
 * Implementation of a Fuzzy AND function.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class FuzzyCombineFunction<T extends Number> implements
		Function<T, Double> {
	private final Function<T, Double> left, right;
	private final T border;

	public FuzzyCombineFunction(Function<T, Double> leftSide,
			Function<T, Double> rightSide, T threshold) {
		left = leftSide;
		right = rightSide;
		border = threshold;
	}

	/* (non-Javadoc)
	 * @see com.google.common.base.Function#apply(java.lang.Object)
	 */
	public Double apply(T value) {
		return (value.doubleValue() <= border.doubleValue()) ? left
				.apply(value) : right.apply(value);
	}
}
