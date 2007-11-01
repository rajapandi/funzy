package funzy.variable;

import static funzy.core.Variables.newVariable;

import org.junit.Test;

import funzy.core.IllegalRangeException;
import funzy.literals.SimpleDegree;

public class VariablesTest {

	@Test
	public void CheckNewIntVariable() {
		newVariable(0,100,SimpleDegree.class);
	}
	
	@Test(expected=IllegalRangeException.class)
	public void CheckFailureNewIntVariable() {
		newVariable(100,0,SimpleDegree.class);
	}
}
