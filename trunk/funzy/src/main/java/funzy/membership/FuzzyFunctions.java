package funzy.membership;

import com.google.common.base.Function;

/**
 * Implementation of a fuzzy function factory.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public final class FuzzyFunctions {
	private FuzzyFunctions() {
	}

	public static final <N extends Number> Function<N, Double> newNotFuzzyFunction(
			final Function<N, Double> function) {
		return new FuzzyNotFunction(function);
	}

	public static final <N extends Number> Function<N, Double> newFuzzyOrFunction(
			final Function<N, Double> leftFunction,
			final Function<N, Double> rightFunction) {
		return new FuzzyOrFunction(leftFunction, rightFunction);
	}

	public static final <N extends Number> Function<N, Double> newFuzzyAndFunction(
			final Function<N, Double> leftFunction,
			final Function<N, Double> rightFunction) {
		return new FuzzyAndFunction(leftFunction, rightFunction);
	}

	public static final <N extends Number> Function<N, Double> newFuzzyFunction(
			final N leftBottom, final N rightTop) {
		return new FuzzyFunction(leftBottom, rightTop);
	}

	public static final <N extends Number> Function<N, Double> newInverseFuzzyFunction(
			final N leftBottom, final N rightTop) {
		return newNotFuzzyFunction(newFuzzyFunction(leftBottom, rightTop));
	}

	public static final <N extends Number> Function<N, Double> newTriangleFuzzyFunction(
			final N leftBottom, final N top, final N rightBottom) {
		return newTrapezoidFuzzyFunction(leftBottom, top, top, rightBottom);
	}

	public static final <N extends Number> Function<N, Double> newTrapezoidFuzzyFunction(
			final N leftBottom, final N letTop, final N rightTop,
			final N rightBottom) {
		return new FuzzyCombineFunction(newFuzzyFunction(leftBottom, letTop),
				newInverseFuzzyFunction(rightTop, rightBottom), rightTop);
	}
}
