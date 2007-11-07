package funzy.operators;

/**
 * Implementation of a fuzzy operator requiring exactly 1 parameter.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public abstract class SingleOperator<N extends Number> implements
		FuzzyOperator<N> {

	/* (non-Javadoc)
	 * @see funzy.operators.FuzzyOperator#evaluate(N[])
	 */
	public N evaluate(N... values) {
		if (values.length != 1)
			throw new InvalidParametersException(
					"Operator requires exactly 1 parameter");
		return evaluate(values[0]);
	}

	protected abstract N evaluate(N value);
}
