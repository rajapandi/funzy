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

import static com.google.common.base.Objects.nonNull;
import static com.google.common.collect.Maps.newHashMap;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import funzy.Pull;
import funzy.variables.memberships.Membership;

/**
 * Implementation of a literal input variable in fuzzy logic.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class InputVariable<L> extends Variable<L> implements
		Pull<Map<L, Double>> {

	private final Pull<Double> input;

	private InputVariable(String name, double minimum, double maximum,
			Pull<Double> provider, double ceil, double floor,
			Map<L, Membership> memberships) throws IllegalRangeException {
		super(name, minimum, maximum, ceil, floor, memberships);
		input = nonNull(provider, "Provider reference is required");
	}

	public Map<L, Double> pull() {
		final double value = input.pull();
		final Map<L, Double> memberships = newHashMap();
		for (Entry<L, Membership> m : members.entrySet())
			if (m.getValue().inXRange(value))
				memberships.put(m.getKey(), m.getValue().solveY(value));
		return memberships;
	}

	// Factory methods

	public static final <L extends Enum<L>> InputVariable newInputVariable(
			Class<L> literals, String name, double min, double max,
			Pull<Double> provider) {
		return new InputVariable<L>(name, min, max, provider, 0, 1,
				new EnumMap<L, Membership>(literals));
	}

	public static final <L extends Enum<L>> InputVariable newInputVariable(
			Class<L> literals, double min, double max, Pull<Double> provider) {
		return newInputVariable(literals, literals.getSimpleName(), min, max,
				provider);
	}

	public static final <L> InputVariable newInputVariable(
			String name, double min, double max, Pull<Double> provider) {
		return new InputVariable<L>(name, min, max, provider, 0, 1,
				new HashMap<L, Membership>());
	}
}