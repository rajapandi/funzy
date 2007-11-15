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

import static java.lang.Double.NaN;

/**
 * Implementation a membership line.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class LineMembership implements Membership {
	private final PointMembership a, b;
	private final double delta, unknown;

	private LineMembership(double defaultValue, PointMembership p1, PointMembership p2) {
		unknown = defaultValue;
		a = p1;
		b = p2;
		delta = (b.y()-a.y())/(b.x()-a.x());
	}

	public PointMembership a() {
		return a;
	}

	public PointMembership b() {
		return b;
	}

	public double delta() {
		return delta;
	}
	
	public boolean inXRange(double x) {
		return x >= a.x() && x <= b.x();
	}
	
	public boolean inYRange(double y) {
		if (a.y()<= b.y())
			return y >= a.y() && y <= b.y();
		return y >= b.y() && y <= a.y();
	}
	
	public double solveY(double x) {
		if (inXRange(x))
			return (x - a.x()) * delta + a.y();
		return unknown;
	}
	
	public double solveX(double y) {
		if (delta != 0)
			return (y - a.y()) / delta + a.x();
		// TODO Different implementations are possible (Left, Right or CoG)
		return unknown;
	}
	
	public static LineMembership newLine(double unknown, PointMembership a, PointMembership b) {
		return new LineMembership(unknown,a, b);
	}
	
	public static LineMembership newLine(PointMembership a, PointMembership b) {
		return newLine(NaN, a, b);
	}
}