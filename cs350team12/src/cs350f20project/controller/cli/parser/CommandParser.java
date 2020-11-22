package cs350f20project.controller.cli.parser;


import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.meta.CommandMetaDoExit;
import cs350f20project.controller.command.structural.CommandStructuralCommit;

public class CommandParser {
	
	private MyParserHelper parserHelper;
	private Tokenizer tokens;
	private String[] texts;
	
	public CommandParser(MyParserHelper parserHelper, String commandText) {
		texts = commandText.split(";");
		this.parserHelper = parserHelper;
		
	}

	// So this is where the 41 if statements/rules will go
	// And we can create a new class for each rule so it cleans this up a bit
	public void parse() {
		for(int i = 0; i < texts.length; ++i) {
			tokens = new Tokenizer(texts[i]);
			String token = tokens.getNext();
			if(token.equalsIgnoreCase("@exit")) exit();
			if(token.equalsIgnoreCase("CREATE")) createCommand();
			if(token.equalsIgnoreCase("DO")) doCommand();
			if(token.equalsIgnoreCase("COMMIT")) this.parserHelper.getActionProcessor().schedule(new CommandStructuralCommit());
		}
	}
	
	//parses the tokens through an instance of the class CREATE
	public void createCommand() {
		Create create = new Create(tokens);
		this.parserHelper.getActionProcessor().schedule(create.parse());
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
