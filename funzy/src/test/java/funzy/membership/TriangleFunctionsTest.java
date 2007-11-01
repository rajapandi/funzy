package funzy.membership;

import static funzy.core.Variables.newVariable;
import static funzy.literals.SimpleDegree.HIGH;
import static funzy.literals.SimpleDegree.LOW;
import static funzy.literals.SimpleDegree.MEDIUM;
import static junit.framework.Assert.assertTrue;

import java.util.EnumMap;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Function;

import funzy.core.Variable;
import funzy.literals.SimpleDegree;

public class TriangleFunctionsTest {
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
		assertTrue("Checking LOW(" + membership.get(LOW) + ")", membership
				.get(LOW) == low);
		assertTrue("Checking MEDIUM(" + membership.get(MEDIUM) + ")",
				membership.get(MEDIUM) == medium);
		assertTrue("Checking HIGH(" + membership.get(HIGH) + ")", membership
				.get(HIGH) == high);
	}

	@Test
	public void checkTriangleExcluded() {
		set(new TriangleFunction(0, 1, 2), new TriangleFunction(1, 2, 3),
				new TriangleFunction(3, 4, 5));
		checkMembership(0, 0, 0, 0);
		checkMembership(1, 1, 0, 0);
		checkMembership(2, 0, 1, 0);
		checkMembership(3, 0, 0, 0);
		checkMembership(4, 0, 0, 1);
		checkMembership(5, 0, 0, 0);
	}

	@Test
	public void checkTriangleShared() {
		set(new TriangleFunction(0, 1, 3), new TriangleFunction(0, 2, 3),
				new TriangleFunction(2, 4, 5));
		checkMembership(0, 0, 0, 0);
		checkMembership(1, 1, 0.5, 0);
		checkMembership(2, 0.5, 1, 0);
		checkMembership(3, 0, 0, 0.5);
		checkMembership(4, 0, 0, 1);
		checkMembership(5, 0, 0, 0);
	}
}
