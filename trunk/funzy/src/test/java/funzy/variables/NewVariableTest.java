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

import static funzy.variables.Variable.variable;

import org.junit.Test;

import funzy.literals.SimpleDegree;

/**
 * Test cases for the literal variables.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class NewVariableTest {
	@Test
	public void CheckNewEnumIntVariable() {
	    variable(SimpleDegree.class,0.0,100.0);
	}
	
	@Test(expected=IllegalRangeException.class)
	public void newEnumIntVariable() {
	    variable(SimpleDegree.class,100.0,0.0);
	}

	@Test
	public void newIntVariable() {
	    variable("temperature",-10,10);
	}
	
	@Test
	public void newDoubleVariable() {
	    variable("length",0.0,100.0);
	}
	
	@Test(expected=IllegalRangeException.class)
	public void newDoubleVariableRangeFailure() {
	    variable("Incorrect range",100.0,0.0);
	}
}