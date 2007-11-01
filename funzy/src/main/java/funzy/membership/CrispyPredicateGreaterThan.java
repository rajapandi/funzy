package funzy.membership;

import com.google.common.base.Predicate;

/**
 * Implementation of a predicate checking superiority of the value to a given
 * threshold.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class CrispyPredicateGreaterThan<T extends Comparable<T>> implements
		Predicate<T> {
	private T border;

	public CrispyPredicateGreaterThan(T threshold) {
		border = threshold;
	}

	/* (non-Javadoc)
	 * @see com.google.common.base.Predicate#apply(java.lang.Object)
	 */
	public boolean apply(T value) {
		return border.compareTo(value) < 0;
	}
}