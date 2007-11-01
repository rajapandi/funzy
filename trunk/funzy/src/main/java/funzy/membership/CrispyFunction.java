package funzy.membership;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

/**
 * Implementation of a Crispy function.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class CrispyFunction <T> implements Function<T, Double> {
	private Predicate<T> pred;
	
	public CrispyFunction(Predicate<T> predicate) {
		pred = predicate;
	}
	
	/* (non-Javadoc)
	 * @see com.google.common.base.Function#apply(java.lang.Object)
	 */
	public Double apply(T value) {
		return pred.apply(value)?1.0:0.0;
	}
}
