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

import static funzy.variables.memberships.PointMembership.newPoint;

/**
 * Implementation a membership line.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class LineMembership implements Membership<Double,Double> {
	private final PointMembership<Double,Double> a, b, delta;

	public LineMembership(PointMembership<Double,Double> p1, PointMembership<Double,Double> p2) {
		a = p1;
		b = p2;
		delta = newPoint(1.0,(b.y()-a.y())/(b.x()-a.x()));
	}

	public PointMembership a() {
		return a;
	}

	public PointMembership b() {
		return b;
	}

	public PointMembership delta() {
		return delta;
	}
	
	public boolean inXRange(Double x) {
		return x >= a.x() && x <= b.x();
	}
	
	public Double solveY(Double x) {
		return (x - a.x()) * delta.y() + a.y();
	}
	
	public boolean inYRange(Double y) {
		if (a.y()<= b.y())
			return y >= a.y() && y <= b.y();
		return y >= b.y() && y <= a.y();
	}
	
	public Double solveX(Double y) {
		if (delta.y() != 0)
			return (y - a.y()) / delta.y() + a.x();
		// TODO Different implementations are possible (Left, Right or CoG)
		return null;
	}
	
	public static LineMembership newLine(PointMembership<Double,Double> a, PointMembership<Double,Double> b) {
		return new LineMembership(a, b);
	}
}