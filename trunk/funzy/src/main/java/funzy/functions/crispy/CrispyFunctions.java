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
package funzy.functions.crispy;

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
