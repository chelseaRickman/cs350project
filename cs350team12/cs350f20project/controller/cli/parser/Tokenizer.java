package cs350f20project.controller.cli.parser;

import java.util.ArrayList;

import cs350f20project.controller.command.A_Command;
//The tokenizer class is the core of the parser.
public class Tokenizer {
	private ArrayList<String> tokens;
	private ArrayList<String> args;
	private ArrayList<String> cmds;
	private String lasttoken;
	//seperate commandtext into an arraylist, removing any blanks, just like a real parser.
	public Tokenizer(String commandText) {
		args= new ArrayList<String>();
		cmds = new ArrayList<String>();
		tokens = new ArrayList<String>();
		String[] tokengetter = commandText.split(" ");
		for(String token : tokengetter) {
			if(token != " ") {
				tokens.add(token);
			}
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
	
	public int size() {
		return tokens.size();
	}
	
	public ArrayList<String> getArgs(){
		return args;
	}
	
	public String getPreviousCommand(){
		return cmds.get(cmds.size()-1);
	}
	
	public void addCmd(String token) {
		cmds.add(token);
	}
	
	public void addArg(String token) {
		args.add(token);
	}
	
	public void setLast(String token) {
		lasttoken = token;
	}
	
	public String getLast() {
		return lasttoken;
	}
	
	//make it easy to call exception throws like invalid token. Return this at the bottom of every parse function.
	public A_Command InvalidToken() throws RuntimeException{
			throw new RuntimeException("Error! Invalid token!");
		
	}
}
