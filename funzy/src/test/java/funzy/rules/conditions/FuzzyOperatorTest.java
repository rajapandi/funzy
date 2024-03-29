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
package funzy.rules.conditions;

import static funzy.HashMapOfMap.newHashMapOfMap;
import static funzy.literals.SimpleDegree.HIGH;
import static funzy.literals.SimpleDegree.LOW;
import static funzy.literals.SimpleDegree.MEDIUM;
import static funzy.rules.conditions.FuzzyIs.is;
import static funzy.rules.conditions.FuzzyOperator.test;
import static funzy.rules.functions.FuzzyConditions.AND;
import static funzy.rules.functions.FuzzyConditions.NOT;
import static funzy.rules.functions.FuzzyConditions.OR;
import static funzy.rules.functions.FuzzyConditions.VERY;
import static funzy.rules.functions.FuzzyConditions.not;
import static funzy.rules.functions.FuzzyConditions.prod;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import funzy.MapOfMap;
import funzy.literals.SimpleDegree;

/**
 * Test of the available fuzzy operators.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class FuzzyOperatorTest {
    private MapOfMap<String, SimpleDegree, Double> input;
    private static final String VARIABLE = "degrees";

    @Before
    public void setup() {
        input = newHashMapOfMap();
        input.put(VARIABLE, LOW, .0).put(VARIABLE, MEDIUM, .3).put(VARIABLE,
                HIGH, .8);
    }

    @Test
    public void checkExtractor() {
        assertEquals(.0, is(VARIABLE, LOW).evaluate(input));
        assertEquals(.3, is(VARIABLE, MEDIUM).evaluate(input));
        assertEquals(.8, is(VARIABLE, HIGH).evaluate(input));
    }

    @Test(expected = NullPointerException.class)
    public void checkExtractorVariableFailure() {
        assertEquals(.0, is("error", LOW).evaluate(input));
    }

    @Test
    public void checkOperatorNot() {
        assertEquals(1.0, test(NOT, is(VARIABLE, LOW))
                .evaluate(input));
        assertEquals(.7, test(not(), is(VARIABLE, MEDIUM))
                .evaluate(input));
        assertEquals(.2, test(not(), is(VARIABLE, HIGH))
                .evaluate(input), .01);
    }

    @Test
    public void checkOperatorVery() {
        assertEquals(.09, test(VERY, is(VARIABLE, MEDIUM))
                .evaluate(input));
    }

    @Test
    public void checkOperatorAND() {
        assertEquals(.3, test(AND, is(VARIABLE, MEDIUM),
                is(VARIABLE, HIGH)).evaluate(input));
    }

    @Test
    public void checkOperatorOR() {
        assertEquals(.8, test(OR, is(VARIABLE, MEDIUM),
                is(VARIABLE, HIGH)).evaluate(input));
    }

    @Test
    public void checkOperatorProduct() {
        assertEquals(.24, test(prod(), is(VARIABLE, MEDIUM),
                is(VARIABLE, HIGH)).evaluate(input));
    }
}
