package cs350f20project.controller.cli.parser;

import java.util.ArrayList;

import cs350f20project.controller.command.A_Command;

public class Set {
	public A_Command parse(Tokenizer tokens) {
		String token = tokens.getNext();
		if(token.equalsIgnoreCase("REFERENCE"))
			return reference(tokens);
		return tokens.InvalidToken();
	}
	
	public A_Command reference(Tokenizer tokens) {
		String token = tokens.getNext();
		if(token.equalsIgnoreCase("ENGINE")) {
			Engine e = new Engine();
			e.setReference(tokens);
		}
			
		return null;
	}
}
