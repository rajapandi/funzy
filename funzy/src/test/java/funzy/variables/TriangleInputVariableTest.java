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

import static funzy.literals.SimpleDegree.HIGH;
import static funzy.literals.SimpleDegree.LOW;
import static funzy.literals.SimpleDegree.MEDIUM;
import static funzy.variables.InputVariable.newInputVariable;
import static funzy.variables.NumberProvider.newNumberSupplier;
import static funzy.variables.memberships.FuzzyMembership.newFuzzyMembership;
import static funzy.variables.memberships.PointMembership.newPoint;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import funzy.literals.SimpleDegree;

/**
 * Test cases for the fuzzy functions.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class TriangleInputVariableTest {
	private InputVariable<SimpleDegree, Double, Double> variable;
	private NumberProvider<Double> provider;

	@Before
	public void setup() {
		provider = newNumberSupplier(0.0);
		variable = newInputVariable(SimpleDegree.class, 1.0, 5.0, provider);
		variable.addMembership(LOW, newFuzzyMembership(newPoint(1.0, 1.0),
				newPoint(2.0, 1.0), newPoint(3.0, 0.0), newPoint(5.0, 0.0)));
		variable.addMembership(MEDIUM, newFuzzyMembership(newPoint(1.0, 0.0),
				newPoint(2.0, 0.0), newPoint(3.0, 1.0), newPoint(4.0, 0.0),
				newPoint(5.0, 0.0)));
		variable.addMembership(HIGH, newFuzzyMembership(newPoint(1.0, 0.0),
				newPoint(3.0, 0.0), newPoint(4.0, 1.0), newPoint(5.0, 1.0)));
	}

	private final void checkMembership(double value, double low, double medium,
			double high) {
		provider.set(value);
		Map<SimpleDegree, Double> membership = variable.pull();
		assertTrue("Checking LOW(" + membership.get(LOW) + ")", membership
				.get(LOW) == low);
		assertTrue("Checking MEDIUM(" + membership.get(MEDIUM) + ")",
				membership.get(MEDIUM) == medium);
		assertTrue("Checking HIGH(" + membership.get(HIGH) + ")", membership
				.get(HIGH) == high);
	}

	@Test
	public void checkDegree1() {
		checkMembership(1, 1, 0, 0);
	}

	@Test
	public void checkDegree2() {
		checkMembership(2, 1, 0, 0);
	}

	@Test
	public void checkDegree3() {
		checkMembership(3, 0, 1, 0);
	}

	@Test
	public void checkDegree4() {
		checkMembership(4, 0, 0, 1);
	}

	@Test
	public void checkDegree5() {
		checkMembership(5, 0, 0, 1);
	}
}
