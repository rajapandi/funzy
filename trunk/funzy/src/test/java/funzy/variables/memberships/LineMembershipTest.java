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

import static funzy.variables.memberships.LineMembership.newLine;
import static funzy.variables.memberships.PointMembership.newPoint;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import funzy.variables.memberships.LineMembership;
import funzy.variables.memberships.PointMembership;

/**
 * Test of a graph line.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class LineMembershipTest {
	private PointMembership p1, p2;
	private LineMembership line ;

	@Before
	public void setup() {
		p1= newPoint(1.0, 0.0);
		p2 = newPoint(3.0, 1.0);
		line = newLine(p1, p2);
	}
	
	@Test
	public void newLineConstructor() {
		assertEquals(p1, line.a());
		assertEquals(p2, line.b());
	}
	
	@Test
	public void delta() {
		assertEquals(0.5, line.delta());
	}
		
	@Test
	public void negativeDelta() {
		p1 = newPoint(1.0, 2.0);
		line = newLine(p1, p2);
		assertEquals(-0.5, line.delta());
	}

	@Test
	public void minRange() {
		assertTrue(line.inXRange(1.0));
	}
	
	@Test
	public void inXRange() {
		assertTrue(line.inXRange(2.0));
	}
	
	@Test
	public void maxRange() {
		assertTrue(line.inXRange(3.0));
	}
	
	@Test
	public void outOfMinRange() {
		assertFalse(line.inXRange(0.0));
	}

	@Test
	public void outOfMaxRange() {
		assertFalse(line.inXRange(4.0));
	}
	
	@Test
	public void floorRange() {
		assertTrue(line.inYRange(0.0));
	}

	@Test
	public void ceilRange() {
		assertTrue(line.inYRange(1.0));
	}
	
	@Test
	public void inYRange() {
		assertTrue(line.inYRange(0.5));
	}
	
	@Test
	public void outOfFloorRange() {
		assertFalse(line.inYRange(-1.0));
	}

	@Test
	public void outOfCeilRange() {
		assertFalse(line.inYRange(2.0));
	}
	
	@Test
	public void solveMinX() {
		assertEquals(0.0, line.solveY(1.0));
	}
	
	@Test
	public void solveMaxX() {
		assertEquals(1.0, line.solveY(3.0));
	}
	
	@Test
	public void solveInX() {
		assertEquals(0.5, line.solveY(2.0));
	}	

	@Test
	public void solveFloorY() {
		assertEquals(1.0, line.solveX(0.0));
	}
	
	@Test
	public void solveCeilY() {
		assertEquals(3.0, line.solveX(1.0));
	}
	
	@Test
	public void solveInY() {
		assertEquals(2.0, line.solveX(0.5));
	}	
}