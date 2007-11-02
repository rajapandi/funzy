package funzy.core;

import static com.google.common.collect.Maps.newHashMap;
import static funzy.core.Configuration.LOG;
import static java.util.logging.Level.FINEST;
import static java.util.logging.Logger.getLogger;

import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import com.google.common.base.Function;

/**
 * Implementation of a literal variable in fuzzy logic.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class Variable<T extends Comparable<T>, K> {
	private final static Logger log = getLogger("fuzzy.variable");
	private final T min, max;
	private final Map<K, Function<T, Double>> functions;

	public Variable(T minimum, T maximum, Map<K, Function<T, Double>> func)
			throws IllegalRangeException {
		checkRange(minimum, maximum, "Wrong input value");
		min = minimum;
		max = maximum;
		functions = func;
	}

	public Variable<T, K> add(K literal, Function<T, Double> function) {
		functions.put(literal, function);
		return this;
	}

	public Map<K, Double> membership(T value) {
		checkRange(value, min, max, "Input value should be within [" + min
				+ "," + max + "]");
		Map<K, Double> memberships = newHashMap();
		for (Entry<K, Function<T, Double>> f : functions.entrySet())
			memberships.put(f.getKey(), checkRange(f.getValue().apply(value),
					0.0, 1.0, "Membership value should be within [0,1]"));
		return memberships;
	}

	private static final <T extends Comparable<T>> T checkRange(T value, T min,
			T max, String message) {
		if (LOG && log.isLoggable(FINEST))
			log.finest("Checking range: " + min + "<=" + value + "<=" + max
					+ "...");
		assert value.compareTo(min) >= 0 && value.compareTo(max) <= 0 : message;
		return value;
	}

	private static final <T extends Comparable<T>> void checkRange(T min,
			T max, String message) throws IllegalRangeException {
		if (LOG && log.isLoggable(FINEST))
			log.finest("Checking range: [" + min + "," + max + "]...");
		if (min.compareTo(max) > 0)
			throw new IllegalRangeException(message, min, max);
	}
}
