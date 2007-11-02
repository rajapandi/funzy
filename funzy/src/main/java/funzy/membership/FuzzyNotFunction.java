package funzy.membership;

import com.google.common.base.Function;

/**
 * Implementation of a Fuzzy NOT function.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class FuzzyNotFunction<T extends Number> implements Function<T, Double> {
	private final Function<T, Double> delegate;

	public FuzzyNotFunction(Function<T, Double> function) {
		delegate = function;
	}

	/* (non-Javadoc)
	 * @see com.google.common.base.Function#apply(java.lang.Object)
	 */
	public Double apply(T value) {
		return 1 - delegate.apply(value);
	}
}
