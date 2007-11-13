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

import static funzy.literals.SimpleDegree.LOW;
import static funzy.variables.InputVariable.newInputVariable;
import static funzy.variables.NumberSupplier.newNumberSupplier;
import static funzy.variables.memberships.Memberships.newFuzzyMembership;
import static funzy.variables.memberships.Point.newPoint;

import org.junit.Before;
import org.junit.Test;

import funzy.literals.SimpleDegree;
import funzy.variables.Variable;

/**
 * Test cases for the membership functions.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class MembershipFunctionTest {
	private Variable variable;

	@Before
	public void setup() {
		variable = newInputVariable(SimpleDegree.class, 0, 10, newNumberSupplier(0));
	}

	@Test
	public void fuzzyMembership() {
		variable.addMembership(LOW,
				newFuzzyMembership(newPoint(0, 0), newPoint(3, 1), newPoint(6,
						0), newPoint(9, 1), newPoint(10, 1)));
	}

	@Test
	public void inverseFuzzyMembership() {
		variable.addMembership(LOW,
				newFuzzyMembership(newPoint(0, 1), newPoint(3, 0), newPoint(6,
						1), newPoint(9, 0), newPoint(10, 0)));
	}
	
	@Test(expected = IllegalMembershipException.class)
	public void unorderedFuzzyMembership() {
		variable.addMembership(LOW,
				newFuzzyMembership(newPoint(0, 0), newPoint(6, 1), newPoint(3,
						0), newPoint(9, 1), newPoint(10, 1)));
	}

	@Test(expected = IllegalMembershipException.class)
	public void outOfCeilRangeFuzzyMembership() {
		variable.addMembership(LOW,
				newFuzzyMembership(newPoint(0, 0), newPoint(6, 2), newPoint(3,
						0), newPoint(9, 1), newPoint(10, 1)));
	}

	@Test(expected = IllegalMembershipException.class)
	public void outOfFloorRangeFuzzyMembership2() {
		variable.addMembership(LOW,
				newFuzzyMembership(newPoint(0, 0), newPoint(6, -1), newPoint(3,
						0), newPoint(9, 1), newPoint(10, 1)));
	}

	@Test(expected = IllegalMembershipException.class)
	public void outOfMinRangeFuzzyMembership() {
		variable.addMembership(LOW,
				newFuzzyMembership(newPoint(-2, 0), newPoint(6, 2), newPoint(3,
						0), newPoint(9, 1), newPoint(10, 1)));
	}

	@Test(expected = IllegalMembershipException.class)
	public void outOfMaxRangeFuzzyMembership2() {
		variable.addMembership(LOW,
				newFuzzyMembership(newPoint(0, 0), newPoint(6, 2), newPoint(3,
						0), newPoint(9, 1), newPoint(11, 1)));
	}
}
