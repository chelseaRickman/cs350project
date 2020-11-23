package cs350f20project.controller.cli.parser;

import java.util.ArrayList;

import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.meta.CommandMetaDoExit;

public class CommandParser {
	
	private MyParserHelper parserHelper;
	private Tokenizer tokens;
	
/*
CommandParser contains all the misc commands. It passes the DO and CREATE commands to their respective classes
51 @EXIT
52 @RUN string
55 CLOSE VIEW id
56 OPEN VIEW id1 ORIGIN ( coordinates_world | ( '$' id2 ) ) WORLD WIDTH integer1 SCREEN WIDTH integer2 HEIGHT integer3
60 COMMIT
61 COUPLE STOCK id1 AND id2
62 LOCATE STOCK id1 ON TRACK id2 DISTANCE number FROM ( START | END )
65 UNCOUPLE STOCK id1 AND id2
66 USE id AS REFERENCE coordinates_world
67 Rule#2 through Rule#65
*/
	public CommandParser(MyParserHelper parserHelper, String commandText) {
		
		this.parserHelper = parserHelper;
		tokens = new Tokenizer(commandText);
	}

	// So this is where the 41 if statements/rules will go
	// And we can create a new class for each rule so it cleans this up a bit
	// Still need to account for multiple commands separated by semi-colon
	public void parse() {
		String token = tokens.getNext();
		if(token == null)
			throw new RuntimeException("Error! Invalid token!");
		
		if(token.equalsIgnoreCase("CREATE"))
			createCommand();
		else if(token.equalsIgnoreCase("DO"))
			doCommand();
		else if(token.equalsIgnoreCase("@EXIT"))
			exit();
		else if(token.equalsIgnoreCase("@RUN"))
			run();
		else if(token.equalsIgnoreCase("COMMIT"))
			commit();
		else if(token.equalsIgnoreCase("USE"))
			use();
		else if(token.equalsIgnoreCase("CLOSE"))
			closeView();
		else if(token.equalsIgnoreCase("OPEN"))
			openView();
		else if(token.equalsIgnoreCase("COUPLE"))
			coupleStock();
		else if(token.equalsIgnoreCase("LOCATE"))
			locateStock();
		else if(token.equalsIgnoreCase("UNCOUPLE"))
			uncoupleStock();
		else {
			throw new RuntimeException("Error! Invalid token!");
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
		// 51 @EXIT
		A_Command command = new CommandMetaDoExit();
		this.parserHelper.getActionProcessor().schedule(command);
	}
	
	public void run() {
		//52 @RUN string
		
	}
	
	public void commit() {
		//60 COMMIT
	}
	
	public void use() {
		//66 USE id AS REFERENCE coordinates_world
		//when entering this method tokens.getNext() should be the id
	}
	
	public void closeView() {
		// 55 CLOSE VIEW id
		// check to make sure the next token is in fact "VIEW" otherwise invalid token
		
	}
	
	public void openView() {
		// 56 OPEN VIEW id1 ORIGIN ( coordinates_world | ( '$' id2 ) ) WORLD WIDTH integer1 SCREEN WIDTH integer2 HEIGHT integer3
		// check to make sure the next token is in fact "VIEW" otherwise invalid token
		
	}
	
	public void coupleStock() {
		// 61 COUPLE STOCK id1 AND id2
		// check token.getNext() is "STOCK" otherwise invalid token
	}
	
	public void locateStock() {
		// 62 LOCATE STOCK id1 ON TRACK id2 DISTANCE number FROM ( START | END )
		// check token.getNext() is "STOCK" otherwise invalid token
	}
	
	public void uncoupleStock() {
		// 65 UNCOUPLE STOCK id1 AND id2
		// check token.getNext() is "STOCK" otherwise invalid token
	}
	

}
