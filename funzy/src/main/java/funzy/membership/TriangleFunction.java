package funzy.membership;

/**
 * Implementation of a Triangle function.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class TriangleFunction <T extends Number> extends TrapezoidFunction<T> {

	public TriangleFunction(T leftBottom, T top, T rightBottom) {
		super(leftBottom, top, top, rightBottom);
	}
}
