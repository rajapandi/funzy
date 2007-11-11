package funzy.operators.functions;

import static com.google.common.collect.Iterables.getOnlyElement;

/**
 * Implementation of a fuzzy operator requiring exactly 1 parameter.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public abstract class FuzzyFunctionSingle<N extends Number> implements
		FuzzyFunction<N> {
	/* (non-Javadoc)
	 * @see funzy.operators.FuzzyOperator#evaluate(N[])
	 */
	public N evaluate(Iterable<N> values) {
		return evaluate(getOnlyElement(values));
	}

	protected abstract N evaluate(N value);
}
