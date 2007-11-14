package funzy.variables;

import funzy.Pull;

public class NumberProvider <N extends Number> implements Pull<N> {
	private N value;

	private NumberProvider(N input) {
		value = input;
	}
	
	public void set(N input) {
		value = input;
	}
	
	public N pull() {
		return value;
	}

	public static final<N extends Number>  NumberProvider<N> newNumberSupplier(final N input) {
		return new NumberProvider<N>(input);
	}
}
