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

import static com.google.common.collect.Lists.newArrayList;
import static funzy.variables.memberships.IllegalMembershipException.checkMembership;
import static funzy.variables.memberships.LineMembership.newLine;
import static java.lang.Double.NaN;

import java.util.List;

/**
 * Implementation of a Fuzzy membership function. A fuzzy membership is an
 * ordered sequence of lines whose Y values are within range [0,1].
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class FuzzyMembership implements Membership {

	private final List<LineMembership> lines = newArrayList();
	private final Double unknown;

	private FuzzyMembership(double defaultValue, PointMembership... points) {
		unknown = defaultValue;
		PointMembership pred = null;
		for (PointMembership suc : points) {
			if (pred != null) {
				if (pred.x() > suc.x())
					throw new IllegalMembershipException(
							"Membership indexes should be ordered");
				checkMembership(0, 1, suc.y());
				lines.add(newLine(pred, suc));
			}
			pred = suc;
		}
	}

	public boolean inXRange(double value) {
		for (LineMembership l : lines)
			if (l.inXRange(value))
				return true;
		return false;
	}

	public double solveY(double x) {
		for (LineMembership l : lines)
			if (l.inXRange(x))
				return l.solveY(x);
		return unknown;
	}

	public boolean inYRange(double value) {
		for (LineMembership l : lines)
			if (l.inYRange(value))
				return true;
		return false;
	}

	public double solveX(double y) {
		for (LineMembership l : lines)
			if (l.inYRange(y))
				return l.solveX(y);
		return unknown;
	}

	public static final FuzzyMembership newFuzzyMembership(double unknown,
			PointMembership... points) {
		return new FuzzyMembership(unknown, points);
	}

	public static final FuzzyMembership newFuzzyMembership(
			PointMembership... points) {
		return newFuzzyMembership(NaN, points);
	}
}