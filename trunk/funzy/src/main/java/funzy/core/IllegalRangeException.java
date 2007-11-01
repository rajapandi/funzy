package funzy.core;

/**
 * Exception thrown when the variable range is wrong.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class IllegalRangeException extends RuntimeException {

	public <T> IllegalRangeException(String message, T min, T max) {
		super(message + " range [" + min + "," + max + "]");
	}
}
