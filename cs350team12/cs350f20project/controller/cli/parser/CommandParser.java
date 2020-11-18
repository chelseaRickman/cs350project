package cs350f20project.controller.cli.parser;

import java.util.ArrayList;

import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.behavioral.CommandBehavioralBrake;
import cs350f20project.controller.command.meta.CommandMetaDoExit;

public class CommandParser {
	
	private MyParserHelper parserHelper;
	private String commandText;
	private Tokenizer tokens;
	
	public CommandParser(MyParserHelper parserHelper, String commandText) {
		this.parserHelper = parserHelper;
		this.commandText = commandText;
		tokens = new Tokenizer(commandText);
	}

	// So this is where the 41 if statements/rules will go
	// And we can create a new class for each rule so it cleans this up a bit
	public void parse() {
		String token = tokens.getNext();
		if(token.equalsIgnoreCase("@exit")) exit();
		if(token.equalsIgnoreCase("DO")) doCommand();
	}
	
	public void doCommand() {
		Do d = new Do();
		this.parserHelper.getActionProcessor().schedule(d.parse(tokens));
	}
	
	
	public void exit() {
		A_Command command = new CommandMetaDoExit();
		this.parserHelper.getActionProcessor().schedule(command);
	}
	

}
