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
package funzy.rules;

import static com.google.common.collect.Lists.newLinkedList;
import static com.google.common.collect.Maps.newHashMap;

import java.util.List;
import java.util.Map;

import funzy.Pull;
import funzy.variables.InputVariable;

/**
 * Implementation of the fuzzy rule engine.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class RuleEngine<L> implements Pull<Map<String, Map<L, Double>>> {
	private final Map<String, Map<L, Double>> values = newHashMap();
	private final List<FuzzyRule<L, Double>> rules = newLinkedList();
	private final List<InputVariable<L, Double, Double>> inputs = newLinkedList();

	public void addRule(FuzzyRule<L, Double> rule) {
		rules.add(rule);
	}

	public void addInputVariable(InputVariable<L, Double, Double> input) {
		inputs.add(input);
	}

	public Map<String, Map<L, Double>> pull() {
		values.clear();
		for (InputVariable<L, Double, Double> in : inputs)
			values.put(in.name(), in.pull());
		for (FuzzyRule<L, Double> rule : rules)
			rule.evaluate();
		return values;
	}
}
