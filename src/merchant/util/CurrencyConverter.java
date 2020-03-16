package merchant.util;

import merchant.errorHandler.ErrorCodes;
import merchant.errorHandler.ErrorMessage;

 /**
  * @author ajatshatrusingh
  * @created on: 15 March 2020
  *
  * To Compute the currency based on defined rules
  */
public class CurrencyConverter {
	
	public static final ErrorMessage eMessage = new ErrorMessage();
	
	enum Roman{
		
		I(1) , V(5), X(10), L(50), C(100), D(500), M(1000);
		
		private int value;
		
		Roman(int value)
		{
			this.value = value;
		}
		
		public int getValue()
		{
			return this.value;
		}
	}
	
	
	
	private static int getValueFromRomanChar(char romanChar)
	{
		int value = -1;
		
		switch(romanChar)
		{
			case 'I' : value = Roman.I.getValue();
						break;
			case 'V' : value = Roman.V.getValue();
						break;
			case 'X' : value = Roman.X.getValue();
						break;
			case 'L' : value = Roman.L.getValue();
						break;
			case 'C' : value = Roman.C.getValue();
						break;
			case 'D' : value = Roman.D.getValue();
						break;
			case 'M' : value = Roman.M.getValue();
						break;
		}
		
		return value;
	}
	
	public static String romanToNumber(String roman)
	{
		String result=StringLiterals.EMPTY_STRING;
		
		switch(validateRomanNumber(roman))
		{
			case 1 :  result = convert(roman);
					  break;
				
			default : result = CurrencyConverter.eMessage.getMessage(ErrorCodes.INVALID_ROMAN_CHARACTER);
		}
		
		return result;
	}
	
	
	/**
	 * Validates a given roman number
	 * Return 1 when roman number is in correct format or 0 otherwise
	 */
	private static int validateRomanNumber(String roman)
	{
		int result = 0;

		if(roman.matches(StringLiterals.PATTERN_ROMAN_NO))
		{
			result = 1;
		}
		
		return result;
	}
	
	
	/**
	 * Converts the valid roman number to arabic number
	 */
	private static String convert(String roman)
	{
		int decimal = 0;
        int lastNumber = 0;
        
		for(int i=roman.length()-1;i>=0;i--)
		{
			char ch = roman.charAt(i);
			decimal = CheckRoman(getValueFromRomanChar(ch), lastNumber, decimal);
            lastNumber = getValueFromRomanChar(ch);
		}
		
		return decimal+StringLiterals.EMPTY_STRING;
		
	}
	
	
	private static int CheckRoman(int TotalDecimal, int LastRomanLetter, int LastDecimal){
		
        if (LastRomanLetter > TotalDecimal) {
            return LastDecimal - TotalDecimal;
        } else {
            return LastDecimal + TotalDecimal;
        }
        
    }
}
