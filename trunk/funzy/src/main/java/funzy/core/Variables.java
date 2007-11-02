/**
 * 
 */
package funzy.core;

import java.util.EnumMap;
import java.util.HashMap;

import com.google.common.base.Function;

/**
 * Implementation of a fuzzy variable factory.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public final class Variables {
	private Variables() {
	}

	public static final <T extends Comparable<T>, E extends Enum<E>> Variable newVariable(
			final T min, final T max, final Class<E> literals) {
		return new Variable<T, E>(min, max,
				new EnumMap<E, Function<T, Double>>(literals));
	}

	public static final <T extends Comparable<T>, E extends Enum<E>> Variable newVariable(
			final T min, final T max) {
		return new Variable<T, E>(min, max,
				new HashMap<E, Function<T, Double>>());
	}
}
