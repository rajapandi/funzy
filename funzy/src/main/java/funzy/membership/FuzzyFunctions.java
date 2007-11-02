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
package funzy.membership;

import com.google.common.base.Function;

/**
 * Implementation of a fuzzy function factory.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public final class FuzzyFunctions {
	private static final Double LITTLE = 1.3;
	private static final Double SLIGHTLY = 1.7;
	private static final Double VERY = 2.0;
	private static final Double EXTREMELY = 3.0;
	private static final Double VERY_VERY = 4.0;
	private static final Double SOMEWHAT = 0.5;

	private FuzzyFunctions() {
	}

	public static final <N extends Number> Function<N, Double> newNotFuzzyFunction(
			final Function<N, Double> function) {
		return new FuzzyNotFunction(function);
	}

	public static final <N extends Number> Function<N, Double> newFuzzyOrFunction(
			final Function<N, Double> leftFunction,
			final Function<N, Double> rightFunction) {
		return new FuzzyOrFunction(leftFunction, rightFunction);
	}

	public static final <N extends Number> Function<N, Double> newFuzzyAndFunction(
			final Function<N, Double> leftFunction,
			final Function<N, Double> rightFunction) {
		return new FuzzyAndFunction(leftFunction, rightFunction);
	}

	public static final <N extends Number> Function<N, Double> newPowFuzzyFunction(
			final Function<N, Double> function, final Double exponent) {
		return new FuzzyPowFunction(function, exponent);
	}

	public static final <N extends Number> Function<N, Double> newLittleFuzzyFunction(
			final Function<N, Double> function) {
		return newPowFuzzyFunction(function, LITTLE);
	}

	public static final <N extends Number> Function<N, Double> newSlightlyFuzzyFunction(
			final Function<N, Double> function) {
		return newPowFuzzyFunction(function, SLIGHTLY);
	}

	public static final <N extends Number> Function<N, Double> newVeryFuzzyFunction(
			final Function<N, Double> function) {
		return newPowFuzzyFunction(function, VERY);
	}

	public static final <N extends Number> Function<N, Double> newExtremelyFuzzyFunction(
			final Function<N, Double> function) {
		return newPowFuzzyFunction(function, EXTREMELY);
	}

	public static final <N extends Number> Function<N, Double> newVeryVeryFuzzyFunction(
			final Function<N, Double> function) {
		return newPowFuzzyFunction(function, VERY_VERY);
	}

	public static final <N extends Number> Function<N, Double> newSomewhatFuzzyFunction(
			final Function<N, Double> function) {
		return newPowFuzzyFunction(function, SOMEWHAT);
	}

	
	public static final <N extends Number> Function<N, Double> newFuzzyFunction(
			final N leftBottom, final N rightTop) {
		return new FuzzyFunction(leftBottom, rightTop);
	}

	public static final <N extends Number> Function<N, Double> newInverseFuzzyFunction(
			final N leftBottom, final N rightTop) {
		return newNotFuzzyFunction(newFuzzyFunction(leftBottom, rightTop));
	}

	public static final <N extends Number> Function<N, Double> newTriangleFuzzyFunction(
			final N leftBottom, final N top, final N rightBottom) {
		return newTrapezoidFuzzyFunction(leftBottom, top, top, rightBottom);
	}

	public static final <N extends Number> Function<N, Double> newTrapezoidFuzzyFunction(
			final N leftBottom, final N letTop, final N rightTop,
			final N rightBottom) {
		return new FuzzyCombineFunction(newFuzzyFunction(leftBottom, letTop),
				newInverseFuzzyFunction(rightTop, rightBottom), rightTop);
	}
}
