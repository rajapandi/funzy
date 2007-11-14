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
package funzy.variables.memberships;

import static funzy.variables.memberships.FuzzyMembership.newFuzzyMembership;
import static funzy.variables.memberships.PointMembership.newPoint;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the trapezoid membership.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class TrapezoidMembershipTest {
	private FuzzyMembership trapezoid;

	@Before
	public void setup() {
		trapezoid = newFuzzyMembership(newPoint(1.0, 0.0), newPoint(2.0, 1.0),
				newPoint(4.0, 1.0), newPoint(5.0, 0.0));
	}

	@Test
	public void solveX1() {
		assertEquals(0.0, trapezoid.solveY(1.0));
	}

	@Test
	public void solveX2() {
		assertEquals(1.0, trapezoid.solveY(2.0));
	}

	@Test
	public void solveX3() {
		assertEquals(1.0, trapezoid.solveY(3.0));
	}

	@Test
	public void solveX4() {
		assertEquals(1.0, trapezoid.solveY(4.0));
	}

	@Test
	public void solveX5() {
		assertEquals(0.0, trapezoid.solveY(5.0));
	}
}