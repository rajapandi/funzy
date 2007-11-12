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

import static com.google.common.collect.Maps.newHashMap;
import static funzy.Configuration.LOG;
import static funzy.variables.fuzzy.Fuzzyfiers.newFuzzyFunction;
import static java.util.logging.Level.FINEST;
import static java.util.logging.Logger.getLogger;

import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import funzy.variables.fuzzy.Fuzzyfier;
import funzy.variables.memberships.FuzzyMembership;

/**
 * Implementation of a literal input variable in fuzzy logic.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class InputVariable<K> extends Variable<K> {
	private final static Logger log = getLogger("fuzzy.variable.input");
	private final static Fuzzyfier function = newFuzzyFunction();

	public InputVariable(String name, double minimum, double maximum,
			Map<K, FuzzyMembership> func) throws IllegalRangeException {
		super(name, minimum, maximum, func);
	}

	public Map<K, Double> fuzzyfy(double value) {
		checkRange(value, min(), max(), "Input value should be within ["
				+ min() + "," + max() + "]");
		Map<K, Double> memberships = newHashMap();
		for (Entry<K, FuzzyMembership> m : membership.entrySet())
			memberships.put(m.getKey(), checkRange(function.fuzzy(value, m
					.getValue()), 0.0, 1.0,
					"Membership value should be within [0,1]"));
		return memberships;
	}

	private static final double checkRange(double value, double min,
			double max, String message) {
		if (LOG && log.isLoggable(FINEST))
			log.finest("Checking range: " + min + "<=" + value + "<=" + max
					+ "...");
		assert value >= min && value >= max : message;
		return value;
	}
}
