package merchant.util;

public class StringLiterals {

    /**
     * @author: ajatshatru singh
     * @created on: 15 March 2020
     *
     * Class consists of String Literals Constants
     */

    public static final String EMPTY_INPUT = "Empty Request. Please try again!";
    public static final String INVALID_INPUT = "Input not recognised. Please try again!";
    public static final String INVALID_ROMAN_CHAR = "Invalid character metric. Please try again!";
    public static final String LINE_PARSE_ERROR = "Error occurred in reading the Input. Please try again!";
    public static final String UNRECOGNISED_TEXT = "Unable to recognize input. Please retry in valid format";
    public static final String PATTERN_ASSIGNED = "^([A-Za-z]+) is ([I|V|X|L|C|D|M])$";
    public static final String PATTERN_CREDITS = "^([A-Za-z]+)([A-Za-z\\s]*) is ([0-9]+) ([c|C]redits)$";
    public static final String PATTERN_HOW_MUCH = "^how much is (([A-Za-z\\s])+)\\?$";
    public static final String PATTERN_HOW_MANY = "^how many [c|C]redits is (([A-Za-z\\s])+)\\?$";
    public static final String PATTERN_ROMAN_NO = "^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
    public static final String PATTERN_WHITESPACE = "\\s+";
    public static final String PATTERN_IS_KEYWORD = "\\sis\\s";
    public static final String PATTERN_QUESTION_MARK = "?";
    public static final String EMPTY_STRING = "";
    public static final String PATTERN_CREDITS2 = "(is\\s+)|([c|C]redits\\s*)";


}
