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
package funzy.variables;

import static funzy.Configuration.LOG;
import static funzy.variables.IllegalRangeException.checkRange;
import static funzy.variables.memberships.FuzzyMembership.newFuzzyMembership;
import static funzy.variables.memberships.PointMembership.newPoint;
import static java.util.logging.Level.WARNING;
import static java.util.logging.Logger.getLogger;

import java.util.Map;
import java.util.logging.Logger;

import funzy.variables.memberships.Membership;

/**
 * Implementation of a literal variable in fuzzy logic.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class Variable<L> {

	private final Logger log = getLogger("funzy.variable");
	private final String name;
	private final double min, max, fl, ce;
	protected final Map<L, Membership> members;

	protected Variable(String identifier, double minimum, double maximum, double ceil,
			double floor, Map<L, Membership> memberships) {
		checkRange(minimum, maximum, "Incorrect range for variable "
				+ identifier);
		name = identifier;
		min = minimum;
		max = maximum;
		fl = floor;
		ce = ceil;
		members = memberships;
	}

	public final double min() {
		return min;
	}

	public final double max() {
		return max;
	}

	public final double floor() {
		return fl;
	}

	public final double ceil() {
		return ce;
	}

	public final String name() {
		return name;
	}

	public Variable<L> addMembership(L key, Membership value) {
		if (LOG && log.isLoggable(WARNING) && members.get(key) != null)
			log.warning("A membership " + key
					+ " is already defined for variable " + name);
		members.put(key, value);
		return this;
	}

	public Variable<L> addTriangleMembership(L key, double a, double b,
			double c, double unknown) {
		return addMembership(key, newFuzzyMembership(unknown, newPoint(a, fl),
				newPoint(b, ce), newPoint(c, fl)));
	}

	public Variable<L> addTrapezoidMembership(L key, double a, double b,
			double c, double d, double unknown) {
		return addMembership(key, newFuzzyMembership(unknown, newPoint(a, fl),
				newPoint(b, ce), newPoint(c, ce), newPoint(d, fl)));
	}
}