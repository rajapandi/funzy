package funzy.operators;

/**
 * Implementation of a fuzzy operator requiring exactly 1 parameter.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public abstract class MultipleOperator<N extends Number> implements
		FuzzyOperator<N> {
	/* (non-Javadoc)
	 * @see funzy.operators.FuzzyOperator#evaluate(N[])
	 */
	public N evaluate(N... values) {
		if (values.length < 2)
			throw new InvalidParametersException(
					"Operator requires more than 2 parameters");
		return compute(values);
	}

	protected N compute(N... values) {
		N res = null;
		for (N value : values)
			if (res == null)
				res = value;
			else
				res = compute(res, value);
		return res;
	}

	protected abstract N compute(N value1, N value2);
}
