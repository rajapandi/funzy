package funzy.membership;

import static java.lang.Math.pow;

import com.google.common.base.Function;

/**
 * Implementation of a Fuzzy POW function.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class FuzzyPowFunction<T extends Number> implements Function<T, Double> {
	private final Function<T, Double> delegate;
	private final Double pow;

	public FuzzyPowFunction(Function<T, Double> function, Double exponent) {
		delegate = function;
		pow = exponent;
	}

	/* (non-Javadoc)
	 * @see com.google.common.base.Function#apply(java.lang.Object)
	 */
	public Double apply(T value) {
		return pow(delegate.apply(value), pow.doubleValue()) ;
	}
}
