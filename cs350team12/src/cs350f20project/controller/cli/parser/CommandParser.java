package cs350f20project.controller.cli.parser;

import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.meta.CommandMetaDoExit;

public class CommandParser {
	
	private MyParserHelper parserHelper;
	private Tokenizer tokens;
	
	public CommandParser(MyParserHelper parserHelper, String commandText) {
		this.parserHelper = parserHelper;
		tokens = new Tokenizer(commandText);
	}

	// So this is where the 41 if statements/rules will go
	// And we can create a new class for each rule so it cleans this up a bit
	public void parse() {
		String token = tokens.getNext();
		if(token.equalsIgnoreCase("@exit")) exit();
		if(token.equalsIgnoreCase("DO")) doCommand();
	}
	
	//parses the tokens through an instance of the class DO
	public void doCommand() {
		Do d = new Do(tokens);
		this.parserHelper.getActionProcessor().schedule(d.parse());
	}
	
	
	
	//Exit function
	public void exit() {
		A_Command command = new CommandMetaDoExit();
		this.parserHelper.getActionProcessor().schedule(command);
	}
	

}
