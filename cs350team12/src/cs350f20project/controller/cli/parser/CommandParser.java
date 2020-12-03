package cs350f20project.controller.cli.parser;


import java.util.ArrayList;

import cs350f20project.controller.cli.TrackLocator;
import cs350f20project.controller.command.A_Command;
import cs350f20project.controller.command.meta.CommandMetaDoExit;
import cs350f20project.controller.command.meta.CommandMetaViewDestroy;
import cs350f20project.controller.command.structural.CommandStructuralCommit;
import cs350f20project.controller.command.structural.CommandStructuralCouple;
import cs350f20project.controller.command.structural.CommandStructuralLocate;
import cs350f20project.controller.command.structural.CommandStructuralUncouple;
import cs350f20project.datatype.CoordinatesWorld;

public class CommandParser {
	
	private MyParserHelper parserHelper;
	private ArrayList<Tokenizer> tokenizers;
	
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
		
		String[] commandTexts = commandText.split(";");
		this.parserHelper = parserHelper;
		
		this.tokenizers = new ArrayList<Tokenizer>();
		for(String command: commandTexts) {
			tokenizers.add(new Tokenizer(command.trim(), parserHelper));
		}
	}

	// So this is where the 41 if statements/rules will go
	// And we can create a new class for each rule so it cleans this up a bit
	public void parse(){
		for(Tokenizer tokens: this.tokenizers) {
			String token = tokens.getNext();

			if(token == null)
				throw new RuntimeException("Error! Invalid token!");
			
			if(token.equalsIgnoreCase("CREATE"))
				createCommand(tokens);
			else if(token.equalsIgnoreCase("DO"))
				doCommand(tokens);
			else if(token.equalsIgnoreCase("@EXIT"))
				exit();
			else if(token.equalsIgnoreCase("@RUN"))
				run();
			else if(token.equalsIgnoreCase("COMMIT"))
				commit();
			else if(token.equalsIgnoreCase("USE"))
				use(tokens);
			else if(token.equalsIgnoreCase("CLOSE"))
				closeView(tokens);
			else if(token.equalsIgnoreCase("OPEN"))
				openView();
			else if(token.equalsIgnoreCase("COUPLE"))
				coupleOrUncoupleStock(tokens, true);
			else if(token.equalsIgnoreCase("LOCATE"))
				locateStock(tokens);
			else if(token.equalsIgnoreCase("UNCOUPLE"))
				coupleOrUncoupleStock(tokens, false);
			else {
				throw new RuntimeException("Error! Invalid token!");
			}
		}
	}
	
	//parses the tokens through an instance of the class CREATE
	public void createCommand(Tokenizer tokens) {
		Create create = new Create(tokens);
		this.parserHelper.getActionProcessor().schedule(create.parse());
	}
	
	//parses the tokens through an instance of the class DO
	public void doCommand(Tokenizer tokens) {
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
	
	public void use(Tokenizer tokens) {
		//66 USE id AS REFERENCE coordinates_world
		//when entering this method tokens.getNext() should be the id
		
		//id
		String id = tokens.getNext();
		if(!Checks.checkID(id, false)) {
			tokens.invalidToken();
		}
		
		//AS REFERENCE
		String keywords = tokens.getNext() + tokens.getNext();
		if(!keywords.equalsIgnoreCase("ASREFERENCE")) {
			tokens.invalidToken();
		}
		
		//coordinates_world
		CoordinatesWorld coordinatesWorld = Checks.parseCoordinatesWorld(tokens.getNext(), tokens.getParser());
		
		this.parserHelper.addReference("$" + id, coordinatesWorld);	
	}
	
	public void closeView(Tokenizer tokens) {
		// 55 CLOSE VIEW id
		
		//VIEW
		if(!tokens.getNext().equalsIgnoreCase("VIEW")) 
			throw new RuntimeException("Error! Invalid token!");
		
		//id
		String id = tokens.getNext(); 
		if(!Checks.checkID(id, false)) {
			tokens.invalidToken();
		}
		
		this.parserHelper.getActionProcessor().schedule(new CommandMetaViewDestroy(id));	
	}
	
	public void openView() {
		// 56 OPEN VIEW id1 ORIGIN ( coordinates_world | ( '$' id2 ) ) WORLD WIDTH integer1 SCREEN WIDTH integer2 HEIGHT integer3
		// check to make sure the next token is in fact "VIEW" otherwise invalid token
		
	}
	
	public void coupleOrUncoupleStock(Tokenizer tokens, boolean isCoupleElseUncouple) {
		// Implements one of the two commands depending on the passed in boolean value: true is COUPLE, false is UNCOUPLE
		// 61 COUPLE STOCK id1 AND id2
		// 65 UNCOUPLE STOCK id1 AND id2
		
		//STOCK
		if(!tokens.getNext().equalsIgnoreCase("STOCK")) { 
			throw new RuntimeException("Error! Invalid token!");
		}
		
		//id1
		String stockId1 = tokens.getNext();
		if(!Checks.checkID(stockId1, false)) {
			tokens.invalidToken();
		}
		
		//AND
		if(!tokens.getNext().equalsIgnoreCase("AND")) { 
			throw new RuntimeException("Error! Invalid token!");
		}
		
		//id2
		String stockId2 = tokens.getNext(); 
		if(!Checks.checkID(stockId2, false)) {
			tokens.invalidToken();
		}
		
		A_Command command;
		if(isCoupleElseUncouple) {
			command = new CommandStructuralCouple(stockId1, stockId2);
		}
		else {
			command = new CommandStructuralUncouple(stockId1, stockId2);
		}
		
		this.parserHelper.getActionProcessor().schedule(command);
	}
	
	public void locateStock(Tokenizer tokens) {
		// 62 LOCATE STOCK id1 ON TRACK id2 DISTANCE number FROM ( START | END ) => CommandStructuralLocate
		
		//STOCK
		if(!tokens.getNext().equalsIgnoreCase("STOCK")) {
			tokens.invalidToken();
		}
		
		//id1
		String stockId = tokens.getNext();
		if(!Checks.checkID(stockId, false)) {
			tokens.invalidToken();
		}
		
		//ON TRACK
		String keywords = tokens.getNext() + tokens.getNext();
		if(!keywords.equalsIgnoreCase("ONTRACK")) {
			tokens.invalidToken();
		}
		
		//id2
		String trackId = tokens.getNext();
		if(!Checks.checkID(trackId, false)) {
			tokens.invalidToken();
		}
		
		//DISTANCE
		if(!tokens.getNext().equalsIgnoreCase("DISTANCE")) {
			tokens.invalidToken();
		}
		
		//number
		String distanceString = tokens.getNext();
		if(!Checks.checkStringIsDouble(distanceString)) {
			tokens.invalidToken();
		}
		Double distance = Double.parseDouble(distanceString);
		
		//FROM
		if(!tokens.getNext().equalsIgnoreCase("FROM")) {
			tokens.invalidToken();
		}
		
		//(START | END)
		boolean isFromStart = false;
		String fromStartOrEnd = tokens.getNext();
		if(!Checks.checkStringIsOneOfTheseValues(fromStartOrEnd, new String[] {"START", "END"})) {
			tokens.invalidToken();
		}
		
		if(fromStartOrEnd.equalsIgnoreCase("START")) {
			isFromStart = true;
		}
		
		this.parserHelper.getActionProcessor().schedule(new CommandStructuralLocate(stockId, new TrackLocator(trackId, distance, isFromStart)));
	}
}
