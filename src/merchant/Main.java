package merchant;

import merchant.inputReader.InputParagraph;

import java.util.ArrayList;

/**
 * @author ajatshatru singh
 * @created on: 15 March 2020
 * This class is the starting point of the application.
 */
public class Main {

	/**
	 * Entry point of the application
	 * In this program we are taking input from the user as compared to the other test
	 */
	public static void main(String[] args) {

		System.out.println("Welcome to Galaxy Exchange !!! \n Please provide input below and press Enter to submit \n");

		// Initialize a new inputParagraph
		InputParagraph inputParagraph = new InputParagraph();
		
		// Read the input from console, validate and process
		ArrayList<String> output= inputParagraph.read();
		
		for(int i=0;i<output.size();i++)
		{
			System.out.println(output.get(i));
		}
		
		
	}

}
