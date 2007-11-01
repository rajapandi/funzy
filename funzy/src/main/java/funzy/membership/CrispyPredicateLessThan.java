package funzy.membership;

import com.google.common.base.Predicate;

/**
 * Implementation of a predicate checking interiority of the value to a given
 * threshold.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class CrispyPredicateLessThan<T extends Comparable<T>> implements
		Predicate<T> {
	private T border;

	public CrispyPredicateLessThan(T threshold) {
		border = threshold;
	}

	/* (non-Javadoc)
	 * @see com.google.common.base.Predicate#apply(java.lang.Object)
	 */
	public boolean apply(T value) {
		return border.compareTo(value) > 0;
	}
}