package Agent.exceptions;

public class InOutException extends RansomwareException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8L;
	public InOutException()
	{
		super();
	}
	public InOutException(String msg)
	{
		super(msg);
	}
	public InOutException(String msg,Throwable cause)
	{
		super(msg,cause);
	}
}
