/**
 * 
 */
package funzy.core;

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
		return new Variable<T, E>(min, max, literals);
	}
}
