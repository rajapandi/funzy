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

import static com.google.common.collect.Lists.immutableList;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import funzy.MapOfMap;
import funzy.rules.conditions.FuzzyCondition;
import funzy.rules.conflicts.ConflictHandler;
import funzy.rules.conflicts.ConflictHandlerException;
import funzy.rules.functions.FuzzyFunction;

/**
 * Implementation of a fuzzy rule.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class FuzzyRule<K, V> {
    private final FuzzyCondition<K, V> iff;
    private final List<FuzzyRuleThen> then;
    private ConflictHandler conflict = new ConflictHandlerException();

    private FuzzyRule(FuzzyCondition<K, V> cond) {
        iff = cond;
        then = newArrayList();
    }

    public FuzzyRule<K, V> conflictHandler(ConflictHandler handler) {
        conflict = handler;
        return this;
    }

    public FuzzyRule<K, V> then(K variable, V literal,
            FuzzyFunction... functions) {
        then.add(new FuzzyRuleThen(variable, literal, functions));
        return this;
    }

    public void evaluate(MapOfMap<K, V, Double> input,
            MapOfMap<K, V, Double> output) {
        double confidence = iff.evaluate(input);
        if (confidence > 0)
            for (FuzzyRuleThen t : then)
                t.apply(confidence, output);
    }

    private class FuzzyRuleThen {
        private final K var;
        private final V lit;
        private final FuzzyFunction[] func;

        public FuzzyRuleThen(K variable, V literal, FuzzyFunction... functions) {
            var = variable;
            lit = literal;
            func = functions;
        }

        public void apply(double value, MapOfMap<K, V, Double> output) {
            double res = value;
            for (FuzzyFunction f : func)
                res = f.evaluate(immutableList(res));
            output.put(var, lit, output.get(var, lit) == null ? res : conflict
                    .handle(output.get(var, lit), res));
        }
    }

    public static final <K, V> FuzzyRule<K, V> iff(
            FuzzyCondition<K, V> condition) {
        return new FuzzyRule(condition);
    }
}