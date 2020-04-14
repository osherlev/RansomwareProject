package Agent.exceptions;

public class JsonException extends RansomwareException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 11L;
	public JsonException()
	{
		super();
	}
	public JsonException(String msg)
	{
		super(msg);
	}
	public JsonException(String msg,Throwable cause)
	{
		super(msg,cause);
	}
}
