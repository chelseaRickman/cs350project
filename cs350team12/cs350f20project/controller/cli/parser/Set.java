package cs350f20project.controller.cli.parser;
import cs350f20project.controller.command.A_Command;

public class Set extends ParserBase{
	public Set(Tokenizer tokens) {
		super(tokens);
	}

	public A_Command parse() {
		String token = tokens.getNext();
		if(token == null)
			return tokens.InvalidToken();
		if(token.equalsIgnoreCase("REFERENCE"))
			return reference();
		if(token.equalsIgnoreCase("SPEED")) {
			Speed s = new Speed(tokens);
			setLast();
			return s.parse();
		}
		if(getArgs(token) == true) {
			return parse();
		}
		return tokens.InvalidToken();
	}
	
	public A_Command reference() {
		String token = tokens.getNext();
		if(token.equalsIgnoreCase("ENGINE")) {
			Engine e = new Engine(tokens);
			return e.setReference();
		}
			
		return null;
	}
}
