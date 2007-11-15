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
 * Implementation a membership point.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class PointMembership implements Membership {
	private final double unknown;
	private final double x;
	private final double y;

	private PointMembership(double defaultValue, double abs, double ord) {
		unknown = defaultValue;
		x = abs;
		y = ord;
	}
	
	public double x() {
		return x;
	}

	public double y() {
		return y;
	}

	public boolean inXRange(double value) {
		return x == value;
	}

	public boolean inYRange(double value) {
		return y == value;
	}

	public double solveX(double value) {
		if (y == value)
			return x;
		return unknown;
	}

	public double solveY(double value) {
		if (x == value)
			return y;
		return unknown;
	}

	public boolean equals(Object obj) {
		PointMembership p = (PointMembership) obj;
		return x == p.x && y == p.y;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}

	public static final PointMembership newPoint(double x, double y) {
		return newPoint(NaN, x, y);
	}

	public static final PointMembership newPoint(double unknown, double x, double y) {
		return new PointMembership(unknown, x, y);
	}
}