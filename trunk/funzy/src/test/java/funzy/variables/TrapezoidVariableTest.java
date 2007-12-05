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

import static com.google.common.collect.Maps.immutableMap;
import static funzy.literals.SimpleDegree.HIGH;
import static funzy.literals.SimpleDegree.LOW;
import static funzy.literals.SimpleDegree.MEDIUM;
import static funzy.variables.Variable.variable;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import funzy.literals.SimpleDegree;

/**
 * Test cases for the fuzzy functions.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class TrapezoidVariableTest {
    private Variable<SimpleDegree> variable;
    private double middle;

    @Before
    public void setup() {
        variable = variable(SimpleDegree.class, 1, 5)
                .trapezoid(LOW, 1, 1, 2, 4)
                .trapezoid(MEDIUM, 1, 2, 4, 5)
                .trapezoid(HIGH, 2, 4, 5, 5);
        middle = (variable.floor() + variable.ceil()) / 2;
    }

    @Test
    public void fuzzyDegreeLow1() {
        assertEquals(variable.ceil(), variable.fuzzy(1).get(LOW));
    }

    @Test
    public void fuzzyDegreeMedium1() {
        assertEquals(variable.floor(), variable.fuzzy(1).get(MEDIUM));
    }

    @Test
    public void fuzzyDegreeHigh1() {
        assertEquals(variable.floor(), variable.fuzzy(1).get(HIGH));
    }

    @Test
    public void fuzzyDegreeLow2() {
        assertEquals(variable.ceil(), variable.fuzzy(2).get(LOW));
    }

    @Test
    public void fuzzyDegreeMedium2() {
        assertEquals(variable.ceil(), variable.fuzzy(2).get(MEDIUM));
    }

    @Test
    public void fuzzyDegreeHigh2() {
        assertEquals(variable.floor(), variable.fuzzy(2).get(HIGH));
    }

    @Test
    public void fuzzyDegreeLow3() {
        assertEquals(middle, variable.fuzzy(3).get(LOW));
    }

    @Test
    public void fuzzyDegreeMedium3() {
        assertEquals(variable.ceil(), variable.fuzzy(3).get(MEDIUM));
    }

    @Test
    public void fuzzyDegreeHigh3() {
        assertEquals(middle, variable.fuzzy(3).get(HIGH));
    }

    @Test
    public void fuzzyDegreeLow4() {
        assertEquals(variable.floor(), variable.fuzzy(4).get(LOW));
    }

    @Test
    public void fuzzyDegreeMedium4() {
        assertEquals(variable.ceil(), variable.fuzzy(4).get(MEDIUM));
    }

    @Test
    public void fuzzyDegreeHigh4() {
        assertEquals(variable.ceil(), variable.fuzzy(4).get(HIGH));
    }

    @Test
    public void fuzzyDegreeLow5() {
        assertEquals(variable.floor(), variable.fuzzy(5).get(LOW));
    }

    @Test
    public void fuzzyDegreeMedium5() {
        assertEquals(variable.floor(), variable.fuzzy(5).get(MEDIUM));
    }

    @Test
    public void fuzzyDegreeHigh5() {
        assertEquals(variable.ceil(), variable.fuzzy(5).get(HIGH));
    }
    
    
    @Test
    public void unfuzzyDegreeLow1() {
        assertEquals(2.0, variable.unfuzzy(immutableMap(LOW,1.0)));
    }
    
    @Test
    public void unfuzzyDegreeLowDot5() {
        assertEquals(2.25, variable.unfuzzy(immutableMap(LOW,.5)));
    }

    @Test
    public void unfuzzyDegreeLow0() {
        assertEquals(1.0, variable.unfuzzy(immutableMap(LOW,.0)));
    }

    @Test
    public void unfuzzyDegreeMedium1() {
        assertEquals(3.0, variable.unfuzzy(immutableMap(MEDIUM,1.0)));
    }
    
    @Test
    public void unfuzzyDegreeMediumDot5() {
        assertEquals(3.0, variable.unfuzzy(immutableMap(MEDIUM,.5)));
    }

    @Test
    public void unfuzzyDegreeMedium0() {
        assertEquals(1.0, variable.unfuzzy(immutableMap(MEDIUM,.0)));
    }
    
    @Test
    public void unfuzzyDegreeHigh1() {
        assertEquals(4.0, variable.unfuzzy(immutableMap(HIGH,1.0)));
    }
    
    @Test
    public void unfuzzyDegreeHighDot5() {
        assertEquals(3.75, variable.unfuzzy(immutableMap(HIGH,.5)));
    }

    @Test
    public void unfuzzyDegreeHigh0() {
        assertEquals(2.0, variable.unfuzzy(immutableMap(HIGH,.0)));
    }

    @Test
    public void unfuzzyDegrees1() {
        assertEquals(3.0, variable.unfuzzy(immutableMap(LOW, 1.0, MEDIUM, 1.0,
                HIGH, 1.0)));
    }

    @Test
    public void unfuzzyDegreesDot5() {
        assertEquals(3.0, variable.unfuzzy(immutableMap(LOW, .5, MEDIUM, .5,
                HIGH, .5)));
    }

    @Test
    public void unfuzzyDegrees0() {
        assertEquals(1.0, variable.unfuzzy(immutableMap(LOW, .0, MEDIUM, .0,
                HIGH, .0)));
    }
}
