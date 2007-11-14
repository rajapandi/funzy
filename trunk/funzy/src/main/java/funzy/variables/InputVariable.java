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
public class InputVariable<L, X extends Number, Y extends Number> extends
		Variable<L, X, Y> implements Pull<Map<L, Y>> {

	private final Pull<X> input;

	private InputVariable(String name, X minimum, X maximum, Pull<X> provider,
			Y ceil, Y floor, Map<L, Membership<X, Y>> memberships)
			throws IllegalRangeException {
		super(name, minimum, maximum, ceil, floor, memberships);
		input = nonNull(provider, "Provider reference is required");
	}

	public Map<L, Y> pull() {
		final X value = input.pull();
		final Map<L, Y> memberships = newHashMap();
		for (Entry<L, Membership<X, Y>> m : members.entrySet())
			if (m.getValue().inXRange(value))
				memberships.put(m.getKey(), m.getValue().solveY(value));
		return memberships;
	}

	// Factory methods

	public static final <L extends Enum<L>, X extends Number> InputVariable newInputVariable(
			Class<L> literals, String name, X min, X max, Pull<X> provider) {
		return new InputVariable<L, X, Double>(name, min, max, provider, 0.0,
				1.0, new EnumMap<L, Membership<X, Double>>(literals));
	}

	public static final <L extends Enum<L>, X extends Number> InputVariable newInputVariable(
			Class<L> literals, X min, X max, Pull<X> provider) {
		return new InputVariable<L, X, Double>(literals.getSimpleName(), min,
				max, provider, 0.0, 1.0, new EnumMap<L, Membership<X, Double>>(
						literals));
	}

	public static final <L, X extends Number> InputVariable newInputVariable(
			String name, X min, X max, Pull<X> provider) {
		return new InputVariable<L, X, Double>(name, min, max, provider, 0.0,
				1.0, new HashMap<L, Membership<X, Double>>());
	}
}