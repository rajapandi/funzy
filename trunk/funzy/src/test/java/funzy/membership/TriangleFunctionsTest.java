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

import static funzy.core.Variables.newInputVariable;
import static funzy.literals.SimpleDegree.HIGH;
import static funzy.literals.SimpleDegree.LOW;
import static funzy.literals.SimpleDegree.MEDIUM;
import static funzy.membership.FuzzyFunctions.newTriangleFuzzyFunction;
import static junit.framework.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Function;

import funzy.core.InputVariable;
import funzy.literals.SimpleDegree;

/**
 * Test cases for the triangle fuzzy functions.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class TriangleFunctionsTest {
	private InputVariable variable;

	@Before
	public void setup() {
		variable = newInputVariable(1, 5, SimpleDegree.class);
	}


	private final void set(Function low, Function medium, Function high) {
		variable.add(LOW, low).add(MEDIUM, medium).add(HIGH, high);
	}

	private final void checkMembership(int value, double low, double medium,
			double high) {
		Map<SimpleDegree, Double> membership = variable.fuzzyfy(value);
		assertTrue("Checking LOW(" + membership.get(LOW) + ")", membership
				.get(LOW) == low);
		assertTrue("Checking MEDIUM(" + membership.get(MEDIUM) + ")",
				membership.get(MEDIUM) == medium);
		assertTrue("Checking HIGH(" + membership.get(HIGH) + ")", membership
				.get(HIGH) == high);
	}

	@Test
	public void checkTriangleExcluded() {
		set(newTriangleFuzzyFunction(0, 1, 2),
				newTriangleFuzzyFunction(1, 2, 3), newTriangleFuzzyFunction(3,
						4, 5));
		checkMembership(0, 0, 0, 0);
		checkMembership(1, 1, 0, 0);
		checkMembership(2, 0, 1, 0);
		checkMembership(3, 0, 0, 0);
		checkMembership(4, 0, 0, 1);
		checkMembership(5, 0, 0, 0);
	}

	@Test
	public void checkTriangleShared() {
		set(newTriangleFuzzyFunction(0, 1, 3),
				newTriangleFuzzyFunction(0, 2, 3), newTriangleFuzzyFunction(2,
						4, 5));
		checkMembership(0, 0, 0, 0);
		checkMembership(1, 1, 0.5, 0);
		checkMembership(2, 0.5, 1, 0);
		checkMembership(3, 0, 0, 0.5);
		checkMembership(4, 0, 0, 1);
		checkMembership(5, 0, 0, 0);
	}
}
