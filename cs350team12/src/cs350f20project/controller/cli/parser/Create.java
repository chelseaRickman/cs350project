package cs350f20project.controller.cli.parser;

import cs350f20project.controller.command.A_Command;

/*
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
			Power power = new Power(tokens);
			return power.parse();
		}
		if(token.equalsIgnoreCase("STOCK")) {
			/*
			 * Make STOCK class
			 * Create create a new instance of STOCK and send our tokens there.
			 */
		}
		if(token.equalsIgnoreCase("TRACK")) {
			/*
			 * Make TRACK class
			 * Create create a new instance of TRACK and send our tokens there.
			 */
		}
		return checkArgs(token);
	}
}
