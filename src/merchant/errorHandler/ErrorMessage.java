/**
 * 
 */
package merchant.errorHandler;

import merchant.util.StringLiterals;

/**
 * @author ajatshatrusingh
 * @created on: 15 March 2020
 *
 * Mapping the error codes to respective messages
 */
public class ErrorMessage {

	
	public ErrorMessage(){
		
	}
	
	/**
	 * This method prints the message for the particular error code
	 */
	public void printMessage(ErrorCodes error)
	{
		String message= getMessage(error);
		
		if(message !=null)
		System.out.println(message);
		
	}
	
	public String getMessage(ErrorCodes error)
	{
		String message= null;
		
		switch(error)
		{
			case NO_INPUT : message = StringLiterals.EMPTY_INPUT;
							break;
			case INVALID : message = StringLiterals.INVALID_INPUT;break;

			case INVALID_ROMAN_CHARACTER : message = StringLiterals.INVALID_ROMAN_CHAR;break;
			
			case INCORRECT_LINE_TYPE : message =  StringLiterals.LINE_PARSE_ERROR;break;
			
			case NO_IDEA : message = StringLiterals.UNRECOGNISED_TEXT;break;
			
			default : break;
		}
		return message;
	}
}
