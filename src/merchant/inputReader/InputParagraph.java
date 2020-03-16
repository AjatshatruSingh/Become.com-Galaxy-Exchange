/**
 * 
 */
package merchant.inputReader;

import merchant.errorHandler.ErrorCodes;
import merchant.errorHandler.ErrorMessage;
import merchant.util.CurrencyConverter;
import merchant.util.StringLiterals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author ajatshatrusingh
 * @created on: 15 March 2020
 * This class provides methods to scan a paragraph.
 * Uses read() method to read a paragraph from the user.
 */

public class InputParagraph {

	private Scanner scan;
	private InputLine inputLine;
	private ErrorMessage eMessage;
	private HashMap<String, String> identifierValue;  //Hash map to store the value for each identifier
	private HashMap<String, String> literalValue;   //Hash Map to store the value of the calculated literal
	private ArrayList<String> output;   // Arraylist to store the output
	
	
	
	public InputParagraph()
	{
		this.scan = new Scanner(System.in);
		this.inputLine = new InputLine();
		this.eMessage = new ErrorMessage();
		this.identifierValue = new HashMap<String, String>();
		this.literalValue = new  HashMap<String, String>();
		this.output = new ArrayList<String>();
	}

		
	/**
     * Method Reads the input text from console. Assumptions for reading are as mentioned in the Readme File.
     * For this project, the entire input is processed in one go and is not a continuous process
	 */


	public ArrayList<String> read()
	{
		String inputLine;
		int count=0;
		ErrorCodes error = null;
		
		
		while(this.scan.hasNextLine() && (inputLine = this.scan.nextLine()).length()>0 )
		{
			error = validateInputType(inputLine);
			
			switch(error)
			{
				case UNIDENTIFIED :  this.output.add(this.eMessage.getMessage(error));break;

				default : this.eMessage.printMessage(error);
			}
			
			count++;
		}
		
		switch(count)
		{
			case 0: error = ErrorCodes.NO_INPUT;
					this.eMessage.printMessage(error);
					break;
					
			default : 
		}
		
		return this.output;
		
	}
	
	
	
	
	/**
	 * Identifies the type of Input process each input line
	 */
	
	private ErrorCodes validateInputType(String line)
	{
		
		ErrorCodes error = ErrorCodes.SUCCESS_OK;
		
		InputLine.Type lineType = this.inputLine.getLineType(line);
		
		switch(lineType)
		{
			case ASSIGNED : 		 processAssignmentLine(line);
							         break;
							
			case CREDITS :			 processCreditsLine(line);
						    		 break;
						    
			case QUESTION_HOW_MUCH : processHowMuchQuestion(line);
									 break;
									 
			case QUESTION_HOW_MANY : processHowManyCreditsQuestion(line);
									 break;
			
			default : error = ErrorCodes.UNIDENTIFIED; break;
		}
				
		return error;
	}
	
	
	
	
	
	/**
	 * It extracts the constant roman literal from input string and adds it constantAssignments hash map
	 */
	private void processAssignmentLine(String line)
	{
		String[] substring = line.trim().split(StringLiterals.PATTERN_WHITESPACE);
		
		try
		{
			identifierValue.put(substring[0], substring[2]);
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			this.eMessage.printMessage(ErrorCodes.INCORRECT_LINE_TYPE);
			System.out.println(e.getMessage());
		}
	}

	/**
     * Handling the How Much question string
	 */
	private void processHowMuchQuestion(String line)
	{
		try
		{
			String formattedLine = line.split(StringLiterals.PATTERN_IS_KEYWORD)[1].trim();
			formattedLine = formattedLine.replace(StringLiterals.PATTERN_QUESTION_MARK,StringLiterals.EMPTY_STRING).trim();
			String keys[] = formattedLine.split(StringLiterals.PATTERN_WHITESPACE);
			
			
			String romanResult=StringLiterals.EMPTY_STRING;
			String completeResult = null;
			boolean errorOccured = false;
			
			for(String key : keys)
			{
				String romanValue = identifierValue.get(key);
				if(romanValue==null)
				{
					completeResult = this.eMessage.getMessage(ErrorCodes.UNIDENTIFIED);
					errorOccured = true;
					break;
				}
				romanResult += romanValue;
			}
			
			if(!errorOccured)
            {
				romanResult = CurrencyConverter.romanToNumber(romanResult);
				completeResult = formattedLine+" is "+romanResult;
			}
				
			output.add(completeResult);
			
		}
		catch(Exception e)
		{
			this.eMessage.printMessage(ErrorCodes.INCORRECT_LINE_TYPE);
			System.out.println(e.getMessage());
			
		}
	}
	
	
	private void processCreditsLine(String line)
	{
		try
		{
			String formattedLine = line.replaceAll(StringLiterals.PATTERN_CREDITS2,StringLiterals.EMPTY_STRING).trim();
			
			String[] keys = formattedLine.split(StringLiterals.PATTERN_WHITESPACE);
			
			String toBeComputed = keys[keys.length-2];
			float value = Float.parseFloat(keys[keys.length-1]);
			
			String roman=StringLiterals.EMPTY_STRING;
			
			for(int i=0;i<keys.length-2;i++)
			{
				roman += identifierValue.get(keys[i]);
			}
			
			int romanNumber = Integer.parseInt(CurrencyConverter.romanToNumber(roman));
			float credit = value/romanNumber;
			
					
			literalValue.put(toBeComputed, credit+StringLiterals.EMPTY_STRING);
		}
		catch(Exception e)
		{
			
			this.eMessage.printMessage(ErrorCodes.INCORRECT_LINE_TYPE);
			System.out.println(e.getMessage());
			
		}
	}
	
	
	
	
	
	private void processHowManyCreditsQuestion(String line) {
		
		try
		{
			String formattedLine = line.split(StringLiterals.PATTERN_IS_KEYWORD)[1];
			formattedLine = formattedLine.replace(StringLiterals.PATTERN_QUESTION_MARK,StringLiterals.EMPTY_STRING).trim();
			String[] keys = formattedLine.split(StringLiterals.PATTERN_WHITESPACE);
			
			boolean found = false;
			String roman = StringLiterals.EMPTY_STRING;
			String outputResult = null;
			Stack<Float> cvalues = new Stack<Float>();
			
			for(String key : keys)
			{
				found = false;
				
				String romanValue = identifierValue.get(key);
				if(romanValue!=null)
				{
					roman += romanValue;
					found = true;
				}
				
				String computedValue = literalValue.get(key);
				if(!found && computedValue!=null)
				{
					cvalues.push(Float.parseFloat(computedValue));
					found = true;
				}
				
				if(!found)
				{
					outputResult = this.eMessage.getMessage(ErrorCodes.UNIDENTIFIED);
					break;
				}
			}
			
			if(found)
			{
				float res=1;
				for(int i =0;i<cvalues.size();i++)
				res *= cvalues.get(i);
					
				int finalres= (int) res;
				if(roman.length()>0)
				finalres = (int)(Integer.parseInt(CurrencyConverter.romanToNumber(roman))*res);
				outputResult = formattedLine +" is "+ finalres +" Credits";
			}
			
			this.output.add(outputResult);
			
		}
		catch(Exception e)
		{
			this.eMessage.printMessage(ErrorCodes.INCORRECT_LINE_TYPE);
			System.out.println(e.getMessage());
		}
		
	}
}
