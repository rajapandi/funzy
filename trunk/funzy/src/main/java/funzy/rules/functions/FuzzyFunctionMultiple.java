package funzy.rules.functions;


/**
 * Implementation of a fuzzy operator requiring exactly 1 parameter.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public abstract class FuzzyFunctionMultiple<N extends Number> implements
		FuzzyFunction<N> {
	/* (non-Javadoc)
	 * @see funzy.operators.FuzzyOperator#evaluate(N[])
	 */
	public N evaluate(Iterable<N> values) {
		N res = null;
		for (N value : values)
			if (res == null)
				res = value;
			else
				res = evaluate(res, value);
		return res;
	}

	protected abstract N evaluate(N value1, N value2);
}
