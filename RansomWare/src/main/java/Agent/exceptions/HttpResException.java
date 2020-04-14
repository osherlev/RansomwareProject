package Agent.exceptions;

public class HttpResException extends RansomwareException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 12L;
	public HttpResException()
	{
		super();
	}
	public HttpResException(String msg)
	{
		super(msg);
	}
	public HttpResException(String msg,Throwable cause)
	{
		super(msg,cause);
	}
}
