package funzy.variables;

import funzy.Pull;

public class NumberProvider implements Pull<Double> {
	private double value;

	private NumberProvider(double input) {
		value = input;
	}

	public void set(double input) {
		value = input;
	}

	public Double pull() {
		return value;
	}

	public static final NumberProvider newNumberSupplier(double input) {
		return new NumberProvider(input);
	}
}
