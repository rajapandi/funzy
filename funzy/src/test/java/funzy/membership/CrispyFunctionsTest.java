package funzy.membership;

import static funzy.core.Variables.newVariable;
import static funzy.literals.SimpleDegree.HIGH;
import static funzy.literals.SimpleDegree.LOW;
import static funzy.literals.SimpleDegree.MEDIUM;
import static funzy.membership.CrispyFunctions.newFunctionEquals;
import static funzy.membership.CrispyFunctions.newFunctionGreaterOrEquals;
import static funzy.membership.CrispyFunctions.newFunctionGreaterThan;
import static funzy.membership.CrispyFunctions.newFunctionLessOrEquals;
import static funzy.membership.CrispyFunctions.newFunctionLessThan;
import static junit.framework.Assert.assertTrue;

import java.util.EnumMap;


import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Function;

import funzy.core.Variable;
import funzy.literals.SimpleDegree;

public class CrispyFunctionsTest {
	private Variable variable;

	@Before
	public void setup() {
		variable = newVariable(1, 5, SimpleDegree.class);
	}

	private final void set(Function low, Function medium, Function high) {
		variable.add(LOW, low).add(MEDIUM, medium).add(HIGH, high);
	}

	private final void checkMembership(int value, double low, double medium,
			double high) {
		EnumMap<SimpleDegree, Double> membership = variable.membership(value);
		assertTrue(membership.get(LOW) == low);
		assertTrue(membership.get(MEDIUM) == medium);
		assertTrue(membership.get(HIGH) == high);
	}

	@Test
	public void checkCrispyEquals() {
		set(newFunctionEquals(1), newFunctionEquals(3), newFunctionEquals(5));

		checkMembership(1, 1, 0, 0);
		checkMembership(2, 0, 0, 0);
		checkMembership(3, 0, 1, 0);
		checkMembership(4, 0, 0, 0);
		checkMembership(5, 0, 0, 1);
	}

	@Test
	public void checkCrispyGreaterThan() {
		set(newFunctionEquals(1), newFunctionGreaterThan(3), newFunctionEquals(5));

		checkMembership(1, 1, 0, 0);
		checkMembership(2, 0, 0, 0);
		checkMembership(3, 0, 0, 0);
		checkMembership(4, 0, 1, 0);
		checkMembership(5, 0, 1, 1);
	}

	@Test
	public void checkCrispyLessThan() {
		set(newFunctionEquals(1), newFunctionLessThan(3), newFunctionEquals(5));

		checkMembership(1, 1, 1, 0);
		checkMembership(2, 0, 1, 0);
		checkMembership(3, 0, 0, 0);
		checkMembership(4, 0, 0, 0);
		checkMembership(5, 0, 0, 1);
	}

	@Test
	public void checkCrispyGreaterOrEquals() {
		set(newFunctionEquals(1), newFunctionGreaterOrEquals(3), newFunctionEquals(5));

		checkMembership(1, 1, 0, 0);
		checkMembership(2, 0, 0, 0);
		checkMembership(3, 0, 1, 0);
		checkMembership(4, 0, 1, 0);
		checkMembership(5, 0, 1, 1);
	}

	@Test
	public void checkCrispyLessOrEquals() {
		set(newFunctionEquals(1), newFunctionLessOrEquals(3), newFunctionEquals(5));

		checkMembership(1, 1, 1, 0);
		checkMembership(2, 0, 1, 0);
		checkMembership(3, 0, 1, 0);
		checkMembership(4, 0, 0, 0);
		checkMembership(5, 0, 0, 1);
	}
}
