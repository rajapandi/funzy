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
import funzy.rules.operators.FuzzyOperator;

/**
 * Implementation of a fuzzy rule.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class FuzzyRule<E> {
    private final FuzzyOperator condition;
    private final FuzzyRuleAssigner<E>[] assign;

    private FuzzyRule(FuzzyOperator cond, FuzzyRuleAssigner<E>... assigners) {
        condition = cond;
        assign = assigners;
    }

    public Iterable<FuzzyRuleAssigner<E>> evaluate() {
        double confidence = condition.get();
        for (FuzzyRuleAssigner<E> ass : assign)
            ass.assign(confidence);
        return immutableList(assign);
    }

    public static final <E> FuzzyRule<E> newRule(FuzzyOperator condition,
            FuzzyRuleAssigner<E>... assigners) {
        return new FuzzyRule<E>(condition, assigners);
    }
}