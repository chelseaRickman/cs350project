package cs350f20project.controller.cli.parser;

import cs350f20project.controller.command.A_Command;

/*
 * This class handles all the CREATE commands
22 CREATE POWER CATENARY id1 WITH POLES idn+
23 CREATE POWER POLE id1 ON TRACK id2 DISTANCE number FROM ( START | END )
24 CREATE POWER STATION id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA coordinates_delta WITH ( SUBSTATION | SUBSTATIONS )idn+
25 CREATE POWER SUBSTATION id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA coordinates_delta WITH CATENARIES idn+
28 CREATE STOCK CAR id AS BOX CommandCreateStockCarBox
29 CREATE STOCK CAR id AS CABOOSE CommandCreateStockCarCaboose
30 CREATE STOCK CAR id AS FLATBED CommandCreateStockCarFlatbed
31 CREATE STOCK CAR id AS PASSENGER CommandCreateStockCarPassenger
32 CREATE STOCK CAR id AS TANK CommandCreateStockCarTank
33 CREATE STOCK CAR id AS TENDER CommandCreateStockCarTender
34 CREATE STOCK ENGINE id1 AS DIESEL ON TRACK id2 DISTANCE number FROM ( START | END ) FACING ( START | END ) CommandCreateStockEngineDiesel
39 CREATE TRACK BRIDGE DRAW id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2 ANGLE angle
40 CREATE TRACK BRIDGE id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2
41 CREATE TRACK CROSSING id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2
42 CREATE TRACK CROSSOVER id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2 START coordinates_delta3 END coordinates_delta4
43 CREATE TRACK CURVE id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2 ( ( DISTANCE ORIGIN number ) | ( ORIGIN coordinates_delta3 ) )
44 CREATE TRACK END id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2
45 CREATE TRACK LAYOUT id1 WITH TRACKS idn+
46 CREATE TRACK ROUNDHOUSE id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA ORIGIN coordinates_delta1 ANGLE ENTRY angle1 START angle2 END angle3 WITH integer SPURS LENGTH number1 TURNTABLE LENGTH number2
47 CREATE TRACK STRAIGHT id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2
48 CREATE TRACK SWITCH TURNOUT id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) STRAIGHT DELTA START coordinates_delta1 END coordinates_delta2 CURVE DELTA START coordinates_delta3 END coordinates_delta4 DISTANCE ORIGIN number
49 CREATE TRACK SWITCH WYE id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2 DISTANCE ORIGIN number1 DELTA START coordinates_delta3 END coordinates_delta4 DISTANCE ORIGIN number2
 */


public class Create extends ParserBase{

	public Create(Tokenizer tokens) {
		super(tokens);
	}
	
	public A_Command parse(){
		String token = tokens.getNext();
		if(token == null)
			return tokens.InvalidToken();
		if(token.equalsIgnoreCase("POWER")) {
			return createPower();
		}
		else if(token.equalsIgnoreCase("STOCK")) {
			return createStock();
		}
		else if(token.equalsIgnoreCase("TRACK")) {
			return createTrack();
		}
		
		return checkArgs(token);
	}
	
	// Determines which POWER method needs to be called
	public A_Command createPower() {
		String nextToken = tokens.getNext();
		if(nextToken == null)
			return tokens.InvalidToken();
		
		if(nextToken.equalsIgnoreCase("CATENARY"))
			return powerCatenary();
		if(nextToken.equalsIgnoreCase("POLE"))
			return powerPole();
		if(nextToken.equalsIgnoreCase("STATION"))
			return powerStation();
		if(nextToken.equalsIgnoreCase("SUBSTATION"))
			return powerSubstation();
		
		return tokens.InvalidToken();	
	}
	
	// Determines which STOCK method needs to be called
	public A_Command createStock() {
		String nextToken = tokens.getNext();
		if(nextToken == null)
			return tokens.InvalidToken();
	
		if(nextToken.equalsIgnoreCase("CAR"))
			return stockCar();
		if(nextToken.equalsIgnoreCase("ENGINE"))
			return stockEngine();
		
		return tokens.InvalidToken();
	}
	
	// Determines which TRACK method needs to be called
	public A_Command createTrack() {
		String nextToken = tokens.getNext();
		if(nextToken == null)
			return tokens.InvalidToken();
		if(nextToken.equalsIgnoreCase("BRIDGE"))
			return trackBridge();
		if(nextToken.equalsIgnoreCase("CROSSING"))
			return trackCrossing();
		if(nextToken.equalsIgnoreCase("CROSSOVER"))
			return trackCrossover();
		if(nextToken.equalsIgnoreCase("CURVE"))
			return trackCurve();
		if(nextToken.equalsIgnoreCase("END"))
			return trackEnd();
		if(nextToken.equalsIgnoreCase("LAYOUT"))
			return trackLayout();
		if(nextToken.equalsIgnoreCase("ROUNDHOUSE"))
			return trackRoundhouse();
		if(nextToken.equalsIgnoreCase("STRAIGHT"))
			return trackStraight();
		if(nextToken.equalsIgnoreCase("SWITCH"))
			return trackSwitch();
		
		return tokens.InvalidToken();
	}
	
	// CREATE POWER commands
	
	private A_Command powerCatenary() {
		// 22 CREATE POWER CATENARY id1 WITH POLES idn+
		return null;
	}
	
	private A_Command powerPole() {
		// 23 CREATE POWER POLE id1 ON TRACK id2 DISTANCE number FROM ( START | END )
		return null;
	}
	
	private A_Command powerStation() {
		// 24 CREATE POWER STATION id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA coordinates_delta WITH ( SUBSTATION | SUBSTATIONS )idn+
		return null;
	}
	
	private A_Command powerSubstation() {
		// 25 CREATE POWER SUBSTATION id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA coordinates_delta WITH CATENARIES idn+
		return null;
	}
	
	// CREATE STOCK commands
	
	private A_Command stockCar() {
		/*
		Can create private helper methods from here to handle the different cars. 
		Maybe can use Tokenizer.get(index) to look at the type of car. So at this point tokens.get(1) should return "BOX" for 28
		28 CREATE STOCK CAR id AS BOX CommandCreateStockCarBox
		29 CREATE STOCK CAR id AS CABOOSE CommandCreateStockCarCaboose
		30 CREATE STOCK CAR id AS FLATBED CommandCreateStockCarFlatbed
		31 CREATE STOCK CAR id AS PASSENGER CommandCreateStockCarPassenger
		32 CREATE STOCK CAR id AS TANK CommandCreateStockCarTank
		33 CREATE STOCK CAR id AS TENDER CommandCreateStockCarTender
		*/
		return null;
	}
	
	private A_Command stockEngine() {
		// 34 CREATE STOCK ENGINE id1 AS DIESEL ON TRACK id2 DISTANCE number FROM ( START | END ) FACING ( START | END ) CommandCreateStockEngineDiesel
		return null;
	}
	
	// CREATE TRACK commands
	
	private A_Command trackBridge() {
		/*
		 * Can create helper methods for TRACK BRIDGE and BRIDGE DRAW
		 * Can use Tokenizer.get(index) to check whether it is BRIDGE or BRIDGE DRAW
		 * 39 CREATE TRACK BRIDGE DRAW id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2 ANGLE angle
		 * 40 CREATE TRACK BRIDGE id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2
		 */
		
		return null;
	}
	
	private A_Command trackCrossing() {
		// 41 CREATE TRACK CROSSING id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2
		return null;
	}
	
	private A_Command trackCrossover() {
		// 42 CREATE TRACK CROSSOVER id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2 START coordinates_delta3 END coordinates_delta4
		return null;
	}
	
	private A_Command trackCurve() {
		// 43 CREATE TRACK CURVE id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2 ( ( DISTANCE ORIGIN number ) | ( ORIGIN coordinates_delta3 ) )
		return null;
	}
	
	private A_Command trackEnd() {
		// 44 CREATE TRACK END id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2
		return null;
	}
	
	private A_Command trackLayout() {
		// 45 CREATE TRACK LAYOUT id1 WITH TRACKS idn+
		return null;
	}
	
	private A_Command trackRoundhouse() {
		// 46 CREATE TRACK ROUNDHOUSE id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA ORIGIN coordinates_delta1 ANGLE ENTRY angle1 START angle2 END angle3 WITH integer SPURS LENGTH number1 TURNTABLE LENGTH number2
		return null;
	}
	
	private A_Command trackStraight() {
		// 47 CREATE TRACK STRAIGHT id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2
		return null;
	}
	
	private A_Command trackSwitch() {
		/*
		 * Can create helper methods for TRACK SWITCH TURNOUT and TRACK SWITCH WYE
		 * Can use Tokenizer.get(index) to check whether it is TURNOUT  or WYE
		 * 48 CREATE TRACK SWITCH TURNOUT id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) STRAIGHT DELTA START coordinates_delta1 END coordinates_delta2 CURVE DELTA START coordinates_delta3 END coordinates_delta4 DISTANCE ORIGIN number
		 * 49 CREATE TRACK SWITCH WYE id1 REFERENCE ( coordinates_world | ( '$' id2 ) ) DELTA START coordinates_delta1 END coordinates_delta2 DISTANCE ORIGIN number1 DELTA START coordinates_delta3 END coordinates_delta4 DISTANCE ORIGIN number2
		 */
		return null;
	}
}
