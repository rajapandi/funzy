// The MIT License - Copyright (c) 2007 Universitetet i Oslo

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE. 
package funzy.variables;

import static funzy.Configuration.LOG;
import static java.util.logging.Level.WARNING;
import static java.util.logging.Logger.getLogger;

import java.util.Map;
import java.util.logging.Logger;

import funzy.membership.FuzzyMembership;
import funzy.membership.IllegalMembershipException;
import funzy.membership.Line;

/**
 * Implementation of a literal variable in fuzzy logic.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class Variable<K> {
	private final Logger log = getLogger("funzy.variable");
	private final String name;
	private final double min, max;
	protected final Map<K, FuzzyMembership> membership;

	public Variable(String identifier, double minimum, double maximum,
			Map<K, FuzzyMembership> memberships) {
		checkRange(minimum, maximum, "Incorrect variable range definition");
		name = identifier;
		min = minimum;
		max = maximum;
		membership = memberships;
	}

	public final double min() {
		return min;
	}

	public final double max() {
		return max;
	}

	public final String name() {
		return name;
	}

	public Variable<K> addMembership(K key, FuzzyMembership value) {
		if (LOG && log.isLoggable(WARNING) && membership.get(key) != null)
			log.warning("A membership " + key
					+ " is already defined for variable " + name);
		for (Line l : value.get())
			if (l.a().x() < min || l.b().x() > max)
				throw new IllegalMembershipException("Membership for " + name
						+ " should be within [" + min + "," + max + "]");
		membership.put(key, value);
		return this;
	}

	public Map<K, FuzzyMembership> memberships() {
		return membership;
	}

	private static final <T extends Number> void checkRange(T min, T max,
			String message) throws IllegalRangeException {
		if (min.doubleValue() > max.doubleValue())
			throw new IllegalRangeException(message, min, max);
	}
}