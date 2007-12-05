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

import static funzy.HashMapOfMap.newHashMapOfMap;
import static funzy.rules.FuzzyRule.iff;
import static funzy.rules.conditions.FuzzyIs.is;
import static funzy.rules.conditions.FuzzyOperator.test;
import static funzy.rules.functions.FuzzyAssigners.VERY;
import static funzy.rules.functions.FuzzyConditions.OR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import funzy.MapOfMap;

/**
 * Test of an OR fuzzy rule.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class OrFuzzyRuleTest {
    private static final String INPUT = "input";
    private static final String OUTPUT = "output";
    private static final String LOW = "low";
    private static final String MEDIUM = "medium";
    private static final String HIGH = "high";

    private FuzzyRule<String, String> rule;
    private MapOfMap<String, String, Double> input, output;

    @Before
    public void setup() {
        rule = iff(test(OR, is(INPUT, LOW), is(INPUT, MEDIUM)));
        input = newHashMapOfMap();
        output = newHashMapOfMap();
    }

    @Test
    public void ruleFits1() {
        rule.then(OUTPUT, HIGH).evaluate(
                input.put(INPUT, LOW, 1.0).put(INPUT, MEDIUM, 1.0), output);
        assertEquals(1.0, output.get(OUTPUT, HIGH));
    }

    @Test
    public void ruleFitsDot5() {
        rule.then(OUTPUT, HIGH).evaluate(
                input.put(INPUT, LOW, .5).put(INPUT, MEDIUM, 1.0), output);
        assertEquals(1.0, output.get(OUTPUT, HIGH));
    }

    @Test
    public void ruleFitsVery() {
        rule.then(OUTPUT, HIGH, VERY).evaluate(
                input.put(INPUT, LOW, .25).put(INPUT, MEDIUM, 1.0), output);
        assertEquals(1.0, output.get(OUTPUT, HIGH));
    }

    @Test
    public void ruleFitsTwo() {
        rule.then(OUTPUT, HIGH, VERY).then(OUTPUT, LOW).evaluate(
                input.put(INPUT, LOW, .25).put(INPUT, MEDIUM, 1.0), output);
        assertEquals(1.0, output.get(OUTPUT, HIGH));
        assertEquals(1.0, output.get(OUTPUT, LOW));
    }

    @Test
    public void ruleDoesNotFit() {
        rule.then(OUTPUT, HIGH).evaluate(
                input.put(INPUT, LOW, .0).put(INPUT, MEDIUM, .0), output);
        assertTrue("Rule output should be empty", output.isEmpty());
    }

    @Test(expected = RuntimeException.class)
    public void ruleVariableError() {
        rule.then(OUTPUT, HIGH).evaluate(
                input.put(OUTPUT, LOW, .0).put(INPUT, MEDIUM, 1.0), output);
    }

    @Test(expected = RuntimeException.class)
    public void ruleLiteralError() {
        rule.then(OUTPUT, HIGH).evaluate(
                input.put(INPUT, HIGH, .0).put(INPUT, MEDIUM, 1.0), output);
    }
}
