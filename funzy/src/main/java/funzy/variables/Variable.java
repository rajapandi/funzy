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
public class Variable<L, X extends Number, Y extends Number> {
	
	private final Logger log = getLogger("funzy.variable");
	private final String name;
	private final X min, max;
	private final Y fl, ce;
	protected final Map<L, Membership<X,Y>> members;

	protected Variable(String identifier, X minimum, X maximum, Y ceil, Y floor,
			Map<L, Membership<X,Y>> memberships) {
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
		return min.doubleValue();
	}

	public final double max() {
		return max.doubleValue();
	}

	public final double floor() {
		return fl.doubleValue();
	}

	public final double ceil() {
		return ce.doubleValue();
	}
	
	public final String name() {
		return name;
	}

	public Variable<L, X, Y> addMembership(L key, Membership<X,Y> value) {
		if (LOG && log.isLoggable(WARNING) && members.get(key) != null)
			log.warning("A membership " + key
					+ " is already defined for variable " + name);
		members.put(key, value);
		return this;
	}
}