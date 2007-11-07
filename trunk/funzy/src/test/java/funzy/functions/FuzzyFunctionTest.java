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
package funzy.functions;

import static funzy.functions.fuzzy.FuzzyFunctions.newFuzzyFunction;
import static funzy.membership.Memberships.newFuzzyMembership;
import static funzy.membership.Point.newPoint;
import static java.lang.Double.NaN;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import funzy.functions.fuzzy.FuzzyFunction;
import funzy.membership.FuzzyMembership;

/**
 * Test cases for the crispy functions.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class FuzzyFunctionTest {
	private FuzzyFunction function;
	private double unknown = NaN;
	private final FuzzyMembership growing = newFuzzyMembership(newPoint(1, 0),
			newPoint(5, 1));
	private final FuzzyMembership triangle = newFuzzyMembership(newPoint(1, 0),
			newPoint(3, 1), newPoint(5, 0));
	private final FuzzyMembership trapezoid = newFuzzyMembership(
			newPoint(1, 0), newPoint(2, 1), newPoint(4, 1), newPoint(5, 0));
	private final FuzzyMembership partial = newFuzzyMembership(newPoint(2, 0),
			newPoint(5, 1));

	@Before
	public void setup() {
		function = newFuzzyFunction(unknown);
	}

	@Test
	public void checkGrowingMembership() {
		assertEquals(0.0, function.fuzzy(1, growing));
		assertEquals(0.25, function.fuzzy(2, growing));
		assertEquals(0.5, function.fuzzy(3, growing));
		assertEquals(0.75, function.fuzzy(4, growing));
		assertEquals(1.0, function.fuzzy(5, growing));
	}

	@Test
	public void checkTriangleMembership() {
		assertEquals(0.0, function.fuzzy(1, triangle));
		assertEquals(0.5, function.fuzzy(2, triangle));
		assertEquals(1.0, function.fuzzy(3, triangle));
		assertEquals(0.5, function.fuzzy(4, triangle));
		assertEquals(0.0, function.fuzzy(5, triangle));
	}

	@Test
	public void checkTrapezoidMembership() {
		assertEquals(0.0, function.fuzzy(1, trapezoid));
		assertEquals(1.0, function.fuzzy(2, trapezoid));
		assertEquals(1.0, function.fuzzy(3, trapezoid));
		assertEquals(1.0, function.fuzzy(4, trapezoid));
		assertEquals(0.0, function.fuzzy(5, trapezoid));
	}

	@Test
	public void checkPartialMembership() {
		assertEquals(unknown, function.fuzzy(1, partial));
		assertEquals(0.0, function.fuzzy(2, partial));
		assertEquals(0.33, function.fuzzy(3, partial), 0.01);
		assertEquals(0.66, function.fuzzy(4, partial), 0.01);
		assertEquals(1.0, function.fuzzy(5, partial));
	}
}
