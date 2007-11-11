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
package funzy.operators;

import static com.google.common.collect.Lists.immutableList;
import static funzy.operators.functions.FuzzyFunctions.newExtremelyFunction;
import static funzy.operators.functions.FuzzyFunctions.newLittleFunction;
import static funzy.operators.functions.FuzzyFunctions.newMaxFunction;
import static funzy.operators.functions.FuzzyFunctions.newMinFunction;
import static funzy.operators.functions.FuzzyFunctions.newNotFunction;
import static funzy.operators.functions.FuzzyFunctions.newProductFunction;
import static funzy.operators.functions.FuzzyFunctions.newSlightlyFunction;
import static funzy.operators.functions.FuzzyFunctions.newSomewhatFunction;
import static funzy.operators.functions.FuzzyFunctions.newVeryFunction;
import static funzy.operators.functions.FuzzyFunctions.newVeryVeryFunction;
import static java.lang.Math.pow;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import funzy.operators.functions.FuzzyFunction;

/**
 * Test of available fuzzy functions.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class FuzzyFunctionTest {

	private static void assertResult(Double expected,
			FuzzyFunction<Double> function, Double... param) {
		assertEquals(expected, function.evaluate(immutableList(param)));
	}

	@Test
	public void functionNot() {
		assertResult(.9, newNotFunction(), .1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void functionMultipleNot() {
		assertResult(.9, newNotFunction(), .1, .2);
	}

	@Test
	public void functionLittle() {
		assertResult(pow(.5, 1.3), newLittleFunction(), .5);
	}

	@Test
	public void functionSlightly() {
		assertResult(pow(.5, 1.7), newSlightlyFunction(), .5);
	}

	@Test
	public void functionVery() {
		assertResult(pow(.5, 2), newVeryFunction(), .5);
	}

	@Test
	public void functionExtremely() {
		assertResult(pow(.5, 3), newExtremelyFunction(), .5);
	}

	@Test
	public void functionVeryVery() {
		assertResult(pow(.5, 4), newVeryVeryFunction(), .5);
	}

	@Test
	public void functionSomewhat() {
		assertResult(pow(.5, .5), newSomewhatFunction(), .5);
	}

	@Test
	public void functionMin() {
		assertResult(.1, newMinFunction(), .5, .4, .3, .2, .1);
	}

	@Test
	public void functionMax() {
		assertResult(.5, newMaxFunction(), .5, .4, .3, .2, .1);
	}

	@Test
	public void functionProduct() {
		assertResult(.2, newProductFunction(), .5, .4);
	}
}
