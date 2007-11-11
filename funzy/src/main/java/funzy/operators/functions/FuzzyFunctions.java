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
package funzy.operators.functions;


/**
 * Implementation of a fuzzy Functions factory.
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

	public static final FuzzyFunction<Double> newNotFuzzyFunction() {
		return new FuzzyFunctionNot();
	}

	public static final FuzzyFunction<Double> newMaxFunction() {
		return new FuzzyFunctionMax();
	}

	public static final FuzzyFunction<Double> newMinFunction() {
		return new FuzzyFunctionMin();
	}

	public static final FuzzyFunction<Double> newProductFunction() {
		return new FuzzyFunctionProduct();
	}

	public static final FuzzyFunction<Double> newPowFuzzyFunction(
			final double exponent) {
		return new FuzzyFunctionPow(exponent);
	}

	public static final FuzzyFunction<Double> newLittleFunction() {
		return newPowFuzzyFunction(LITTLE);
	}

	public static final FuzzyFunction<Double> newSlightlyFunction() {
		return newPowFuzzyFunction(SLIGHTLY);
	}

	public static final FuzzyFunction<Double> newVeryFunction() {
		return newPowFuzzyFunction(VERY);
	}

	public static final FuzzyFunction<Double> newExtremelyFunction() {
		return newPowFuzzyFunction(EXTREMELY);
	}

	public static final FuzzyFunction<Double> newVeryVeryFunction() {
		return newPowFuzzyFunction(VERY_VERY);
	}

	public static final FuzzyFunction<Double> newSomewhatFunction() {
		return newPowFuzzyFunction(SOMEWHAT);
	}
}
