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
import static funzy.variables.fuzzy.Fuzzyfiers.newFuzzyFunction;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import funzy.Pull;
import funzy.variables.fuzzy.Fuzzyfier;
import funzy.variables.memberships.FuzzyMembership;

/**
 * Implementation of a literal input variable in fuzzy logic.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class InputVariable<N extends Number, K> extends Variable<N, K>
		implements Pull<Map<K, Double>> {
	private final Fuzzyfier fuzzyfier = newFuzzyFunction(); // Default
	private final Pull<N> input;

	private InputVariable(String name, N minimum, N maximum, Pull<N> provider,
			Map<K, FuzzyMembership> func) throws IllegalRangeException {
		super(name, minimum, maximum, func);
		input = nonNull(provider, "Provider reference is required");
	}

	public Map<K, Double> pull() {
		final double value = input.pull().doubleValue();
		final Map<K, Double> memberships = newHashMap();
		for (Entry<K, FuzzyMembership> m : membership.entrySet())
			memberships.put(m.getKey(), fuzzyfier.fuzzy(value, m.getValue()));
		return memberships;
	}

	// Factory methods

	public static final <N extends Number, E extends Enum<E>> InputVariable newInputVariable(
			Class<E> literals, String name, N min, N max, Pull<N> provider) {
		return new InputVariable<N, E>(name, min, max, provider,
				new EnumMap<E, FuzzyMembership>(literals));
	}

	public static final <N extends Number, E extends Enum<E>> InputVariable newInputVariable(
			Class<E> literals, N min, N max, Pull<N> provider) {
		return new InputVariable<N, E>(literals.getSimpleName(), min, max,
				provider, new EnumMap<E, FuzzyMembership>(literals));
	}

	public static final <N extends Number, E> InputVariable newInputVariable(
			String name, N min, N max, Pull<N> provider) {
		return new InputVariable<N, E>(name, min, max, provider,
				new HashMap<E, FuzzyMembership>());
	}
}