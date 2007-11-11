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

import static funzy.membership.Line.newLine;
import static funzy.membership.Point.newPoint;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import funzy.membership.Line;
import funzy.membership.Point;

/**
 * Test of a graph line.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class MembershipLineTest {
	private Point p1, p2;
	private Line line ;

	@Before
	public void setup() {
		p1= newPoint(4, 3);
		p2 = newPoint(6, 3);
		line = newLine(p1, p2);
	}
	
	@Test
	public void checkNewLine() {
		assertEquals(p1, line.a());
		assertEquals(p2, line.b());
	}
	
	@Test
	public void checkDelta() {
		assertEquals(newPoint(1,0), line.delta());
	}
	
	@Test
	public void checkPositiveDelta() {
		p2 = newPoint(6, 5);
		line = newLine(p1, p2);
		assertEquals(newPoint(1,1), line.delta());
	}
	
	@Test
	public void checkNegativeDelta() {
		p1 = newPoint(4, 5);
		line = newLine(p1, p2);
		assertEquals(newPoint(1,-1), line.delta());
	}

	@Test
	public void checkMinRange() {
		assertTrue(line.inXRange(4));
	}
	
	@Test
	public void checkInRange() {
		assertTrue(line.inXRange(5));
	}
	
	@Test
	public void checkMaxRange() {
		assertTrue(line.inXRange(6));
	}
	
	@Test
	public void checkOutOfMinRange() {
		assertFalse(line.inXRange(3));
	}

	@Test
	public void checkOutOfMaxRange() {
		assertFalse(line.inXRange(7));
	}
}