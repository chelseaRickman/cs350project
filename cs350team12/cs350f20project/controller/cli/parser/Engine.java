package cs350f20project.controller.cli.parser;

import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.behavioral.CommandBehavioralSetReference;

public class Engine {
	public A_Command setReference(Tokenizer tokens) {
		String token = tokens.getNext();
		
		return new CommandBehavioralSetReference(token);
	}
	
}
