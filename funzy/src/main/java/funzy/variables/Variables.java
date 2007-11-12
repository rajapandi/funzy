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

import java.util.EnumMap;
import java.util.HashMap;

import funzy.variables.memberships.FuzzyMembership;

/**
 * Implementation of a fuzzy variable factory.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public final class Variables {
	private Variables() {
	}

	public static final <N extends Number, E extends Enum<E>> InputVariable newInputVariable(
			final String name, final double min, final double max, final Class<E> literals) {
		return new InputVariable<E>(name, min, max,
				new EnumMap<E, FuzzyMembership>(literals));
	}

	public static final <E extends Enum<E>> InputVariable newInputVariable(
			final double min, final double max, final Class<E> literals) {
		return new InputVariable<E>(literals.getSimpleName(), min, max,
				new EnumMap<E, FuzzyMembership>(literals));
	}

	public static final <N extends Number, E> InputVariable newInputVariable(
			final String name, final double min, final double max) {
		return new InputVariable<E>(name, min, max,
				new HashMap<E, FuzzyMembership>());
	}
}
