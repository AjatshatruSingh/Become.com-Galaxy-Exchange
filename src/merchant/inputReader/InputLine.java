package merchant.inputReader;

import merchant.util.StringLiterals;

/**
 * @author ajatshatrusingh
 * @created on: 15 March 2020
 *
 * Class contains the ENUM mapping for the entry type and validates the entry against RegEx to determine user entry
 * validity
 *
 */
public class InputLine {

	public static enum Type{
		 ASSIGNED,
		 CREDITS, 
		 QUESTION_HOW_MUCH, 
		 QUESTION_HOW_MANY, 
		 NO_MATCH
		 
	}

    private LineFilter[] lineFilter;

	public class LineFilter
	{
		private Type type;
		private String pattern;
		public LineFilter(Type type, String pattern)
		{
			this.type = type;
			this.pattern = pattern;
		}
		
		public String getPattern()
		{
			return this.pattern;
					
		}
		
		public Type getType()
		{
			return this.type;
		}
	}
	

	/**
     * Constructor to initialise the line filters.
     * Can be updated to inject more input rules
     *
	 */
	public InputLine()
	{
		// Since we have have four type of lines
		this.lineFilter = new LineFilter[4];
		this.lineFilter[0] = new LineFilter(Type.ASSIGNED, StringLiterals.PATTERN_ASSIGNED);
		this.lineFilter[1] = new LineFilter(Type.CREDITS, StringLiterals.PATTERN_CREDITS);
		this.lineFilter[2] = new LineFilter(Type.QUESTION_HOW_MUCH, StringLiterals.PATTERN_HOW_MUCH);
		this.lineFilter[3] = new LineFilter(Type.QUESTION_HOW_MANY, StringLiterals.PATTERN_HOW_MANY);
		
	}
		
		
	
	
	/**
     * Mapping the input with the entry type
	 */

	public Type getLineType(String line)
	{
		line = line.trim();
		Type result = Type.NO_MATCH;
		
		boolean matched = false;
			
		for(int i =0;i<lineFilter.length && !matched ;i++)
		{
			if( line.matches(lineFilter[i].getPattern()) )
			{
				matched = true;
				result = lineFilter[i].getType();
			}
			
		}
		
		return result;
		
	}
	
}
