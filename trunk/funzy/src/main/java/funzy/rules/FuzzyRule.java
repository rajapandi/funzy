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

import funzy.MapOfMap;
import funzy.rules.operators.FuzzyCondition;

/**
 * Implementation of a fuzzy rule.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class FuzzyRule<K, V> {
    private final FuzzyCondition<K, V> condition;
    private final FuzzyRuleIs<K, V>[] assign;

    private FuzzyRule(FuzzyCondition<K, V> cond, FuzzyRuleIs<K, V>... assigners) {
        condition = cond;
        assign = assigners;
    }

    public void evaluate(MapOfMap<K, V, Double> input, MapOfMap<K, V, Double> output) {
        double confidence = condition.evaluate(input);
        if (confidence > 0)
            for (FuzzyRuleIs<K, V> ass : assign)
                ass.assign(confidence, output);
    }

    public static final <K, V> FuzzyRule<K, V> newRule(
            FuzzyCondition<K, V> condition, FuzzyRuleIs<K, V>... assigners) {
        return new FuzzyRule(condition, assigners);
    }
}