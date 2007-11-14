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

/**
 * Implementation a membership point.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class PointMembership<X extends Number, Y extends Number> implements
		Membership<X, Y> {

	private final X x;
	private final Y y;

	public PointMembership(X abs, Y ord) {
		x = abs;
		y = ord;
	}

	public X x() {
		return x;
	}

	public Y y() {
		return y;
	}

	public boolean inXRange(X value) {
		return x.equals(value);
	}

	public boolean inYRange(Y value) {
		return y.equals(value);
	}

	public X solveX(Y value) {
		if (y.equals(value))
			return x;
		return null;
	}

	public Y solveY(X value) {
		if (x.equals(value))
			return y;
		return null;
	}

	public boolean equals(Object obj) {
		PointMembership p = (PointMembership) obj;
		return x.equals(p.x) && y.equals(p.y);
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}

	public static final <X extends Number, Y extends Number> PointMembership<X, Y> newPoint(
			X x, Y y) {
		return new PointMembership(x, y);
	}
}