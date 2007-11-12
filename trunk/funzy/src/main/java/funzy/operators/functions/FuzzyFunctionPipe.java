package funzy.operators.functions;

import static com.google.common.collect.Iterables.getOnlyElement;
import static com.google.common.collect.Lists.immutableList;

/**
 * Implementation of a fuzzy operator requiring exactly 1 parameter.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class FuzzyFunctionPipe<N extends Number> implements
		FuzzyFunction<N> {
	private FuzzyFunction<N>[] delegates;

	public FuzzyFunctionPipe(FuzzyFunction<N>... functions) {
		delegates = functions;
	}

	/* (non-Javadoc)
	 * @see funzy.operators.FuzzyOperator#evaluate(N[])
	 */
	public N evaluate(Iterable<N> values) {
		Iterable<N> res = values;
		for (int i = delegates.length - 1; i >= 0; i--)
			res = immutableList(delegates[i].evaluate(res));
		return getOnlyElement(res);
	}
}
