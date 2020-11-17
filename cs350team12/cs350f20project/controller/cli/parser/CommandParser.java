package cs350f20project.controller.cli.parser;

import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.meta.CommandMetaDoExit;

public class CommandParser {
	
	private MyParserHelper parserHelper;
	private String commandText;
	
	public CommandParser(MyParserHelper parserHelper, String commandText) {
		this.parserHelper = parserHelper;
		this.commandText = commandText;
	}
	
	// So this is where the 41 if statements/rules will go
	// And we can create a new class for each rule so it cleans this up a bit
	public void parse() {
		if(this.commandText.equalsIgnoreCase("@exit")) {
			A_Command command = new CommandMetaDoExit();
			this.parserHelper.getActionProcessor().schedule(command);
		}
	}

}
