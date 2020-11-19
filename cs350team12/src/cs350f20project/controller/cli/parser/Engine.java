package cs350f20project.controller.cli.parser;
import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.behavioral.CommandBehavioralSetReference;

public class Engine extends ParserBase {
	public Engine(Tokenizer tokens) {
		super(tokens);

	}
	
	public A_Command parse() {
		String token = tokens.getNext();
		if(token == null)
			return tokens.InvalidToken();
		if(tokens.getPreviousCommand().equalsIgnoreCase("REFERENCE"))
			return setReference();
		return checkArgs(token);
	}
	
	public A_Command setReference() {
		String token = tokens.getNext();
		
		return new CommandBehavioralSetReference(token);
	}
	
}
