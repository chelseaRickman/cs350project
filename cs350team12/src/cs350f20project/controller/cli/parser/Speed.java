package cs350f20project.controller.cli.parser;
import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.behavioral.CommandBehavioralSetSpeed;

public class Speed extends ParserBase {

	public Speed(Tokenizer tokens) {
		super(tokens);
	}
	public A_Command parse() {
		String token = tokens.getNext();

		if(tokens.getLast().equalsIgnoreCase("SET"))
			return setSpeed(token);
		if(token == null)
			return tokens.InvalidToken();
		return checkArgs(token);
	}
	
	public A_Command setSpeed(String token) {
		double speed = Double.parseDouble(token);
		System.out.print(tokens.getArgs().get(0));
		return new CommandBehavioralSetSpeed(tokens.getArgs().get(0), speed);
	}
}
