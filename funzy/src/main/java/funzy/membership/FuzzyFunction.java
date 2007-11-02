package funzy.membership;

import com.google.common.base.Function;

/**
 * Implementation of a Fuzzy function.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class FuzzyFunction<T extends Number> implements Function<T, Double> {
	private final T lb, rt;

	/**
	 * @param leftBottom
	 * @param rightTop
	 */
	public FuzzyFunction(T leftBottom, T rightTop) {
		lb = leftBottom;
		rt = rightTop;
	}

	private static final <T extends Number> Double evaluate(T a, T b, T x) {
		return (x.doubleValue() - a.doubleValue())
				/ (b.doubleValue() - a.doubleValue());
	}

	/* (non-Javadoc)
	 * @see com.google.common.base.Function#apply(java.lang.Object)
	 */
	public Double apply(T value) {
		if (value.doubleValue() <= lb.doubleValue())
			return 0.0;
		if (value.doubleValue() < rt.doubleValue())
			return evaluate(lb, rt, value);
		return 1.0;
	}
}
