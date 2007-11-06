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
package funzy.membership;

import static com.google.common.collect.Lists.newArrayList;
import static funzy.membership.Line.newLine;

import java.util.List;

import com.google.common.base.Supplier;

/**
 * Implementation of a Fuzzy membership function. A fuzzy membership is an
 * ordered sequence of lines whose Y values are within range [0,1].
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class FuzzyMembership implements Supplier<Iterable<Line>> {
	private final List<Line> lines = newArrayList();

	public FuzzyMembership(Point... points) {
		Point pred = null;
		for (Point suc : points) {
			if (pred != null) {
				if (pred.x() > suc.x())
					throw new IllegalMembershipException(
							"Membership indexes should be ordered");
				if (suc.y() < 0 || suc.y() > 1)
					throw new IllegalMembershipException(
							"Membership value should be within range [0,1]");
				lines.add(newLine(pred, suc));
			}
			pred = suc;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.common.base.Supplier#get()
	 */
	public Iterable<Line> get() {
		return lines;
	}
}