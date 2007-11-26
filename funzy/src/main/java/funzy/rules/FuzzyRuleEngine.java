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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import funzy.Pull;
import funzy.variables.InputVariable;
import funzy.variables.Variable;
import funzy.variables.conflicts.ConflictHandler;
import funzy.variables.conflicts.ConflictHandlerException;

/**
 * Implementation of the fuzzy rule engine.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class FuzzyRuleEngine<L> implements Pull<Map<String, Double>> {
    private final Map<String, Map<L, Double>> values = newHashMap();
    private final List<FuzzyRule<L>> rules = newLinkedList();
    private final List<InputVariable<L>> inputs = newLinkedList();
    private final Map<String, Variable<L>> outputs = newHashMap();
    public static ConflictHandler CONFLICT = new ConflictHandlerException();

    public void addRule(FuzzyRule<L> rule) {
        rules.add(rule);
    }

    public void addInputVariable(InputVariable<L> input) {
        inputs.add(input);
    }

    public void addOutputVariable(Variable<L> output) {
        outputs.put(output.name(), output);
    }

    public void add(Map<String, Map<L, Double>> res, FuzzyRuleAssigner<L> assign) {
        Map<L, Double> variable = res.get(assign.variable());
        if (variable == null)
            variable = res.put(assign.variable(), new HashMap<L, Double>());
        Double val = variable.get(assign.literal());
        variable.put(assign.literal(), (val == null) ? assign.confidence()
                : outputs.get(assign.variable()).conflictHandler().handle(val,
                        assign.confidence()));
    }

    public Map<String, Double> pull() {
        values.clear();
        for (InputVariable<L> input : inputs)
            values.put(input.name(), input.pull());
        Map<String, Map<L, Double>> fuzzy = newHashMap();
        for (FuzzyRule<L> rule : rules)
            for (FuzzyRuleAssigner<L> assign : rule.evaluate())
                add(fuzzy, assign);
        Map<String, Double> unfuzzy = newHashMap();
        for (Entry<String, Map<L, Double>> entry : fuzzy.entrySet())
            unfuzzy.put(entry.getKey(), outputs.get(entry.getKey()).unfuzzy(
                    entry.getValue()));
        return unfuzzy;
    }
}
