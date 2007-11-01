package funzy.membership;

import com.google.common.base.Function;

/**
 * Implementation of a Trapezoid function.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class TrapezoidFunction<T extends Number> implements Function<T, Double> {
	private final T lb, lt, rt, rb;

	public TrapezoidFunction(T leftBottom, T leftTop, T rightTop, T rightBottom) {
		lb = leftBottom;
		lt = leftTop;
		rt = rightTop;
		rb = rightBottom;
	}

	private final Double evaluate(T a, T b, T x) {
		return (x.doubleValue() - a.doubleValue())
				/ (b.doubleValue() - a.doubleValue());
	}

	/* (non-Javadoc)
	 * @see com.google.common.base.Function#apply(java.lang.Object)
	 */
	public Double apply(T value) {
		if (value.doubleValue() <= lb.doubleValue())
			return 0.0;
		if (value.doubleValue() < lt.doubleValue())
			return evaluate(lb,lt,value);
		if (value.doubleValue() <= rt.doubleValue())
			return 1.0;
		if (value.doubleValue() < rb.doubleValue())
			return 1 - evaluate(rt, rb, value);
		return 0.0;
	}
}
