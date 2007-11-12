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

import static funzy.variables.memberships.Point.newPoint;

/**
 * Implementation a membership line.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class Line {
	private final Point a, b, delta;

	public Line(Point p1, Point p2) {
		a = p1;
		b = p2;
		delta = newPoint(1,(p2.y()-p1.y())/(p2.x()-p1.x()));
	}

	public final Point a() {
		return a;
	}

	public final Point b() {
		return b;
	}

	public final Point delta() {
		return delta;
	}
	
	public final boolean inXRange(final double value) {
		return value >= a.x() && value <= b.x();
	}
	
	public static final Line newLine(final Point p1, final Point p2) {
		return new Line(p1, p2);
	}
}