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

import static com.google.common.base.Objects.nonNull;
import static com.google.common.collect.Lists.newArrayList;
import static funzy.variables.memberships.PointMembership.newPoint;
import static funzy.variables.solvers.Solvers.DEFAULT;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import funzy.Pull;
import funzy.variables.memberships.Membership;
import funzy.variables.memberships.PointMembership;

/**
 * Implementation of a literal output variable in fuzzy logic.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class OutputVariable<L> extends Variable<L> implements Pull<Double> {
	private final Pull<Map<L, Double>> input;

	private OutputVariable(String name, double minimum, double maximum,
			Pull<Map<L, Double>> provider, double ceil, double floor,
			Map<L, Membership> memberships) throws IllegalRangeException {
		super(name, minimum, maximum, ceil, floor, memberships);
		input = nonNull(provider, "Provider reference is required");
	}

	public Double pull() {
		Map<L, Double> values = input.pull();
		List<PointMembership> points = newArrayList();
		for (Entry<L, Double> m : values.entrySet())
			points.add(newPoint(m.getValue(), solve(m.getKey(),m.getValue())));
		return DEFAULT.solve(points).x();
	}
	
	private Double solve(L key, double confidence) {
		return members.get(key).unfuzzy(confidence);
	}
}
