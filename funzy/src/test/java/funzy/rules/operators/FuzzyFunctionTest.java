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
package funzy.rules.operators;

import static com.google.common.collect.Lists.immutableList;
import static funzy.rules.functions.FuzzyInterpreters.add;
import static funzy.rules.functions.FuzzyInterpreters.extremely;
import static funzy.rules.functions.FuzzyInterpreters.little;
import static funzy.rules.functions.FuzzyInterpreters.max;
import static funzy.rules.functions.FuzzyInterpreters.min;
import static funzy.rules.functions.FuzzyInterpreters.nand;
import static funzy.rules.functions.FuzzyInterpreters.nop;
import static funzy.rules.functions.FuzzyInterpreters.nor;
import static funzy.rules.functions.FuzzyInterpreters.not;
import static funzy.rules.functions.FuzzyInterpreters.nxr;
import static funzy.rules.functions.FuzzyInterpreters.prod;
import static funzy.rules.functions.FuzzyInterpreters.slightly;
import static funzy.rules.functions.FuzzyInterpreters.somewhat;
import static funzy.rules.functions.FuzzyInterpreters.very;
import static funzy.rules.functions.FuzzyInterpreters.veryvery;
import static funzy.rules.functions.FuzzyInterpreters.xor;
import static java.lang.Math.pow;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import funzy.rules.functions.FuzzyFunction;

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
	public void functionNop() {
		assertResult(.1, nop(), .1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void functionNopFailure() {
		assertResult(.1, nop(), .1, .2);
	}
	
	@Test
	public void functionNot() {
		assertResult(.9, not(), .1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void functionMultipleNot() {
		assertResult(.9, not(), .1, .2);
	}

	@Test
	public void functionLittle() {
		assertResult(pow(.5, 1.3), little(), .5);
	}

	@Test
	public void functionSlightly() {
		assertResult(pow(.5, 1.7), slightly(), .5);
	}

	@Test
	public void functionVery() {
		assertResult(pow(.5, 2), very(), .5);
	}

	@Test
	public void functionExtremely() {
		assertResult(pow(.5, 3), extremely(), .5);
	}

	@Test
	public void functionVeryVery() {
		assertResult(pow(.5, 4), veryvery(), .5);
	}

	@Test
	public void functionSomewhat() {
		assertResult(pow(.5, .5), somewhat(), .5);
	}

	@Test
	public void functionMin() {
		assertResult(.1, min(), .5, .4, .3, .2, .1);
	}

	@Test
	public void functionMax() {
		assertResult(.5, max(), .5, .4, .3, .2, .1);
	}

	@Test
	public void functionProd() {
		assertResult(.2, prod(), .5, .4);
	}

	@Test
	public void functionAdd() {
		assertResult(.9, add(), .5, .4);
	}
	
	@Test
	public void functionXor() {
		//TODO: Check the semantics of XOR
		assertResult(.5, xor(), .5, .4);
	}

	@Test
	public void functionNxr() {
		assertResult(.5, nxr(), .5, .4);
	}

	@Test
	public void functionNor() {
		assertResult(.5, nor(), .5, .4);
	}

	@Test
	public void functionNand() {
		assertResult(.6, nand(), .5, .4);
	}
}