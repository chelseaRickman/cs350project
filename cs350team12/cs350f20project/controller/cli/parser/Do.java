package cs350f20project.controller.cli.parser;

import cs350f20project.controller.cli.parser.CommandParser;
import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.behavioral.CommandBehavioralBrake;

public class Do {
	public A_Command parse(Tokenizer tokens){
		String token = tokens.getNext();
		if(token.equalsIgnoreCase("BRAKE"))
			return brake(tokens);
		if(token.equalsIgnoreCase("SET")) {
			Set s = new Set();
			return s.parse(tokens);
		}
		return tokens.InvalidToken();
	}
	
	
	public A_Command brake(Tokenizer tokens) {
		String token = tokens.getNext();
		return new CommandBehavioralBrake(token);
		
	}
}
