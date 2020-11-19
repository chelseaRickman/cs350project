package cs350f20project.controller.cli.parser;

import java.util.ArrayList;

import cs350f20project.controller.command.A_Command;
//The tokenizer class is the core of the parser.
public class Tokenizer {
	private ArrayList<String> tokens;
	private ArrayList<String> cmds;
	private ArrayList<ArrayList<String>> args;
	int arg;
	private String lasttoken;
	//seperate commandtext into an arraylist, removing any blanks, just like a real parser.
	public Tokenizer(String commandText) {
		arg = 0;
		args= new ArrayList<ArrayList<String>>();
		cmds = new ArrayList<String>();
		tokens = new ArrayList<String>();
		args = new ArrayList<ArrayList<String>>();
		args.add(new ArrayList<String>());
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
	
	//get size of tokenizer. Length is size-1
	public int size() {
		return tokens.size();
	}
	
	//get one of the argument lists.
	/*Argument lists can be confusing, but as an example:
	say we we enter "DO SET id SPEED 1"
	In this case getArgs(0) would return the list [id]
	and getArgs(1) would return the list [1]
	This helps because in the future we will have lists of multiple arguments (lists of lists)*/
	public ArrayList<String> getArgs(int arg){
		System.out.println(args.get(arg));
		return args.get(arg);
	}
	
	//A list of commands is kept so we can reference any of the commands we have been given. Currently, it is mostly used for getting the last command in getLast().
	//Don't worry about this.
	public void addCmd(String token) {
		cmds.add(token);
	}
	
	//Adds an arg to the arglist we are currently using.
	//Don't worry about this.
	public void addArg(String token) {
		args.get(arg).add(token);
	}
	
	public void nextArgList() {
		if(!getArgs(arg).isEmpty()) {
			arg+=1;
			args.add(new ArrayList<String>());
		}
	}
	
	//Don't worry about this
	public void setLast() {
		if(cmds.size()>1)
			lasttoken = cmds.get(cmds.size()-2);
		else
			lasttoken = null;
	}
	
	//gets the last command that was executed. Remember, token != command != argument. A command is one of the capitalized words, like DO and SET.
	public String getLast() {
		return lasttoken;
	}
	
	//make it easy to call exception throws like invalid token. Return this at the bottom of every parse function.
	public A_Command InvalidToken() throws RuntimeException{
			throw new RuntimeException("Error! Invalid token!");
		
	}
}
