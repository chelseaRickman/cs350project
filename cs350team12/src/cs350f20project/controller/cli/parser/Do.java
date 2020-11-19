package cs350f20project.controller.cli.parser;

import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.behavioral.CommandBehavioralBrake;

public class Do extends ParserBase{
	public Do(Tokenizer tokens) {
		super(tokens);
	}


	public A_Command parse(){
		String token = tokens.getNext();
		if(token == null)
			return tokens.InvalidToken();
		if(token.equalsIgnoreCase("BRAKE"))
			return brake(tokens);
		if(token.equalsIgnoreCase("SET")) {
			Set s = new Set(tokens);
			return s.parse();
		}
		return checkArgs(token);
	}
	
	
	public A_Command brake(Tokenizer tokens) {
		String token = tokens.getNext();
		return new CommandBehavioralBrake(token);
		
	}
}
