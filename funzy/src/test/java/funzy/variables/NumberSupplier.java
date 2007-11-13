package funzy.variables;

import funzy.Pull;

public class NumberSupplier <N extends Number> implements Pull<N> {
	private N value;

	private NumberSupplier(N input) {
		value = input;
	}
	
	public void set(N input) {
		value = input;
	}
	
	public N pull() {
		return value;
	}

	public static final<N extends Number>  NumberSupplier<N> newNumberSupplier(final N input) {
		return new NumberSupplier<N>(input);
	}
}
