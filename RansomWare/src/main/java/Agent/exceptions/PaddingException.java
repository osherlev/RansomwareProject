package Agent.exceptions;

public class PaddingException extends CryptoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;
public PaddingException()
{
	super();
}
public PaddingException(String msg)
{
	super(msg);
}
public PaddingException(String msg,Throwable cause)
{
	super(msg,cause);
}
}
