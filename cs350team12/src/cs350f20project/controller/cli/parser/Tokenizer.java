package cs350f20project.controller.cli.parser;

import java.util.ArrayList;

import cs350f20project.controller.command.A_Command;
//The tokenizer class is the core of the parser.
public class Tokenizer {
	private ArrayList<String> tokens;
	int arg;
	private MyParserHelper parser;
	
	//seperate commandtext into an arraylist, removing any blanks, just like a real parser.
	public Tokenizer(String commandText, MyParserHelper helper) {
		arg = 0;
		tokens = new ArrayList<String>();
		String[] tokengetter = commandText.split("\\s+");
		for(String token : tokengetter) {
			if(token != " ")
				tokens.add(token.trim());
		}
	}
	
	//remove the tokenlist as we read through
	public String getNext() {
		if(tokens.size() == 0)
			return null;
		return tokens.remove(0);
	}
	
	//probably wont need this but just in case
	public String get(int index) {
		return tokens.get(index);
	}
	
	//get size of tokenizer. Length is size-1
	public int size() {
		return tokens.size();
	}
	
	public MyParserHelper getParser() {
		return parser;
	}
	
	//make it easy to call exception throws like invalid token. Return this at the bottom of every parse function.
	public A_Command invalidToken() throws RuntimeException{
			throw new RuntimeException("Error! invalid token! ");
		
	}
	
	public A_Command invalidToken(String message) throws RuntimeException{
		throw new RuntimeException(message);
	}
	
}
