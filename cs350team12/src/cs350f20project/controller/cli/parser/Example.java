package cs350f20project.controller.cli.parser;

/*
import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.behavioral.CommandBehavioralSetSpeed;

public class Set extends ParserBase{
	public Set(Tokenizer tokens) {
		super(tokens);
	}

	public A_Command parse() {
		{Every parse() should start with this line. It deletes the first token and makes our parser reference it.}
		String token = tokens.getNext();
		
		{This is an example of an ender. We want to check if the token is null, but if the token is null and you want to execute a function, check the tokens.getLast() for the previous command.}
		if(tokens.getLast().equalsIgnoreCase("SET"))
			return setSpeed(token);
			
		{Place this before checking the next token against equalsIgnoreCase, otherwise you can crash.}	
		if(token == null)
			return tokens.InvalidToken();
		
		{Check if equals Reference or speed. Reference ends the command here, so reference(); is in this file.}
		
		if(token.equalsIgnoreCase("REFERENCE"))
			return reference();
			
		{If it equals speed, we know to go to the next command. Since Speed.java exists as the class Speed, we create a new instance of speed and send our tokens there.}
		{moving to another parser is as simple as
		ExampleCommand e = new ExampleCommand(tokens);
		return e.parse();}
		if(token.equalsIgnoreCase("SPEED")) {
			Speed s = new Speed(tokens);
			return s.parse();
		}
		if(verifyArg(token) == true) {
			return parse();
		}
		return tokens.InvalidToken();
	}
	
	
	{Reference redirects to another parser class. It first checks what the next token is so it knows what to reference.}
	{
	public A_Command reference() {
		String token = tokens.getNext();
		if(token.equalsIgnoreCase("ENGINE")) {
			Engine e = new Engine(tokens);
			return e.setReference();
		}
			
		return null;
	}
	
	{An example of getArgs. setSpeed gets the first argument of the first argument list and makes that the id string in CommandBehavioralSetSpeed}
	{Since the command ends here, it is ok to simply use the token that we already have from the main parser run.}
	public A_Command setSpeed(String token) {
		double speed = Double.parseDouble(token);
		return new CommandBehavioralSetSpeed(tokens.getArgs(0).get(0), speed);
	}
}
*/