package funzy.membership;

import static com.google.common.base.Predicates.or;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

/**
 * Implementation of a crispy function factory.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public final class CrispyFunctions {
	private CrispyFunctions() {
	}

	public static final <T extends Comparable<T>> Function<T, Double> newFunction(
			Predicate<T> pred) {
		return new CrispyFunction(pred);
	}

	public static final <T extends Comparable<T>> Function<T, Double> newFunctionEquals(
			T value) {
		return newFunction(new CrispyPredicateEquals(value));
	}

	public static final <T extends Comparable<T>> Function<T, Double> newFunctionGreaterThan(
			T value) {
		return newFunction(new CrispyPredicateGreaterThan(value));
	}

	public static final <T extends Comparable<T>> Function<T, Double> newFunctionLessThan(
			T value) {
		return newFunction(new CrispyPredicateLessThan(value));
	}
	
	public static final <T extends Comparable<T>> Function<T, Double> newFunctionGreaterOrEquals(
			T value) {
		return newFunction(or(new CrispyPredicateGreaterThan(value),new CrispyPredicateEquals(value)));
	}

	public static final <T extends Comparable<T>> Function<T, Double> newFunctionLessOrEquals(
			T value) {
		return newFunction(or(new CrispyPredicateLessThan(value),new CrispyPredicateEquals(value)));
	}
}
