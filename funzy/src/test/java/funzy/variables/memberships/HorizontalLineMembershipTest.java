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

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import funzy.variables.IllegalRangeException;

/**
 * Test of a graph line.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class HorizontalLineMembershipTest {
	private PointMembership p1, p2;
	private LineMembership line ;

	@Before
	public void setup() {
		p1= newPoint(1.0, .0);
		p2 = newPoint(3.0, .0);
		line = newLine(p1, p2);
	}
	
	@Test
	public void newLineFactory() {
		assertEquals(p1, line.a());
		assertEquals(p2, line.b());
	}

	@Test
	public void delta() {
		assertEquals(.0, line.delta());
	}

	@Test
	public void fuzzyMin() {
		assertEquals(.0, line.fuzzy(1.0));
	}

	@Test
	public void fuzzyMax() {
		assertEquals(.0, line.fuzzy(3.0));
	}

	@Test
	public void fuzzy() {
		assertEquals(.0, line.fuzzy(2.0));
	}

	@Test(expected = IllegalRangeException.class)
	public void fuzzyFailure() {
		line.fuzzy(4.0);
	}

	@Test
	public void unfuzzy() {
		assertEquals(1.0, line.unfuzzy(0));
	}

	@Test(expected = IllegalRangeException.class)
	public void unfuzzyFailure() {
		line.unfuzzy(2.0);
	}

    @Test
    public void truncAbove() {
        List<PointMembership> trunc = line.trunc(1);
        assertEquals(2,trunc.size());
        assertEquals(p1, trunc.get(0));
        assertEquals(p2, trunc.get(1));
    }

    @Test
    public void truncY() {
        List<PointMembership> trunc = line.trunc(0);
        assertEquals(2,trunc.size());
        assertEquals(p1, trunc.get(0));
        assertEquals(p2, trunc.get(1));
    }
    
    @Test
    public void truncUnder() {
        List<PointMembership> trunc = line.trunc(-1);
        assertEquals(0,trunc.size());
    }
}